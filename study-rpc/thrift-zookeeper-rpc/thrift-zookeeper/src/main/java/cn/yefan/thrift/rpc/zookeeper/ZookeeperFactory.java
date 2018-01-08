package cn.yefan.thrift.rpc.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.FactoryBean;

/**
 * 获取zookeeper客户端链接
 *
 * @author yefan
 * @date 2018/01/08
 */
public class ZookeeperFactory implements FactoryBean<CuratorFramework> {

    private String zkHosts;
    /**
     * session超时
     */
    private int sessionTimeout = 30000;
    private int connectionTimeout = 30000;

    /**
     * 共享一个zk链接
     */
    private boolean singleton = true;
    private CuratorFramework zkClient;
    /**
     * 全局path前缀,常用来区分不同的应用
     */
    private String namespace;

    private final static String ROOT = "rpc";

    /**
     * 注意一定要实现属性的set方法,否则在spring bean注入的地方会拿不到值
     *
     * @param zkHosts
     */
    public void setZkHosts(String zkHosts) {
        this.zkHosts = zkHosts;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setZkClient(CuratorFramework zkClient) {
        this.zkClient = zkClient;
    }

    public CuratorFramework getObject() throws Exception {
        if (singleton) {
            if (zkClient == null) {
                zkClient = create();
                zkClient.start();
            }
            return zkClient;
        }
        return create();
    }

    private CuratorFramework create() throws Exception {
        if (namespace == null || namespace == "") {
            namespace = ROOT;
        } else {
            namespace = ROOT + "/" + namespace;
        }
        return create(zkHosts, sessionTimeout, connectionTimeout, namespace);
    }

    public static CuratorFramework create(String zkHosts, int sessionTimeout, int connectionTimeout, String namespace) {
        return CuratorFrameworkFactory.builder()
                .connectString(zkHosts)
                .sessionTimeoutMs(sessionTimeout)
                .connectionTimeoutMs(connectionTimeout)
                .canBeReadOnly(true)
                .namespace(namespace)
                .retryPolicy(new ExponentialBackoffRetry(1000, Integer.MAX_VALUE))
                .defaultData(null)
                .build();
    }

    public Class<?> getObjectType() {
        return CuratorFramework.class;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void close() {
        if (zkClient != null) {
            zkClient.close();
        }
    }

}
