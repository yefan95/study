package cn.yefan.thrift.rpc;

import cn.yefan.thrift.rpc.zookeeper.ThriftServerAddressProvider;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.Closeable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 客户端代理工厂
 *
 * @author yefan
 * @date 2018/01/08
 */
public class ThriftServiceClientProxyFactory implements FactoryBean, InitializingBean, Closeable {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 最大活跃连接数
     */
    private Integer maxActive = 32;
    /**
     * ms,default 3 min,链接空闲时间, -1,关闭空闲检测
     */
    private Integer idleTime = 180000;
    private ThriftServerAddressProvider serverAddressProvider;

    private Object proxyClient;
    private Class<?> objectClass;

    private GenericObjectPool<TServiceClient> pool;

    private ThriftClientPoolFactory.PoolOperationCallBack callback = new ThriftClientPoolFactory.PoolOperationCallBack() {
        public void make(TServiceClient client) {
            logger.info("create");
        }

        public void destroy(TServiceClient client) {
            logger.info("destroy");
        }
    };

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public void setIdleTime(Integer idleTime) {
        this.idleTime = idleTime;
    }

    public void setServerAddressProvider(ThriftServerAddressProvider serverAddressProvider) {
        this.serverAddressProvider = serverAddressProvider;
    }

    public void afterPropertiesSet() throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 加载Iface接口
        objectClass = classLoader.loadClass(serverAddressProvider.getService() + "$Iface");
        // 加载Client.Factory类
        Class<TServiceClientFactory<TServiceClient>> fi = (Class<TServiceClientFactory<TServiceClient>>) classLoader.loadClass(serverAddressProvider.getService() + "$Client$Factory");
        TServiceClientFactory<TServiceClient> clientFactory = fi.newInstance();

        ThriftClientPoolFactory clientPool = new ThriftClientPoolFactory(serverAddressProvider, clientFactory, callback);
        pool = new GenericObjectPool<TServiceClient>(clientPool, makePoolConfig());

//        InvocationHandler handler = makeProxyHandler();//方式1
        //方式2
        InvocationHandler handler = makeProxyHandler2();
        proxyClient = Proxy.newProxyInstance(classLoader, new Class[]{objectClass}, handler);
    }

    private InvocationHandler makeProxyHandler() throws Exception {
        ThriftServiceClient2Proxy handler = null;
        TServiceClient client = pool.borrowObject();
        try {
            handler = new ThriftServiceClient2Proxy(client);
            pool.returnObject(client);
        } catch (Exception e) {
            pool.invalidateObject(client);
            throw new ThriftException("获取代理对象失败");
        }
        return handler;
    }


    private InvocationHandler makeProxyHandler2() throws Exception {
        ThriftServiceClientProxy handler = new ThriftServiceClientProxy(pool);
        return handler;
    }

    private GenericObjectPool.Config makePoolConfig() {
        GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
        poolConfig.maxActive = maxActive;
        poolConfig.maxIdle = 1;
        poolConfig.minIdle = 0;
        poolConfig.minEvictableIdleTimeMillis = idleTime;
        poolConfig.timeBetweenEvictionRunsMillis = idleTime * 2L;
        poolConfig.testOnBorrow = true;
        poolConfig.testOnReturn = false;
        poolConfig.testWhileIdle = false;
        return poolConfig;
    }

    public Object getObject() throws Exception {
        return proxyClient;
    }

    public Class<?> getObjectType() {
        return objectClass;
    }

    public boolean isSingleton() {
        return true;
    }

    public void close() {
        if (pool != null) {
            try {
                pool.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (serverAddressProvider != null) {
            serverAddressProvider.close();
        }
    }
}
