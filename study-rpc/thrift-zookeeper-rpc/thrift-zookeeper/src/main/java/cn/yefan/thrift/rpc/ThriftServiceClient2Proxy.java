package cn.yefan.thrift.rpc;

import org.apache.thrift.TServiceClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 客户端代理类
 *
 * @author yefan
 * @date 2018/01/08
 */
public class ThriftServiceClient2Proxy implements InvocationHandler {

    private TServiceClient client;

    public ThriftServiceClient2Proxy(TServiceClient client) {
        this.client = client;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            return method.invoke(client, args);
        } catch (Exception e) {
            throw e;
        }
    }


}
