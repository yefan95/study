package cn.yefan.thrift.rpc;

import cn.yefan.thrift.rpc.zookeeper.ThriftServerAddressProvider;
import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.TServiceClientFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class ThriftClientPoolFactory extends BasePoolableObjectFactory<TServiceClient> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final ThriftServerAddressProvider serverAddressProvider;
    private final TServiceClientFactory<TServiceClient> clientFactory;
    private PoolOperationCallBack callback;

    protected ThriftClientPoolFactory(ThriftServerAddressProvider addressProvider, TServiceClientFactory<TServiceClient> clientFactory) throws Exception {
        this.serverAddressProvider = addressProvider;
        this.clientFactory = clientFactory;
    }

    protected ThriftClientPoolFactory(ThriftServerAddressProvider addressProvider, TServiceClientFactory<TServiceClient> clientFactory,
                                      PoolOperationCallBack callback) throws Exception {
        this.serverAddressProvider = addressProvider;
        this.clientFactory = clientFactory;
        this.callback = callback;
    }

    interface PoolOperationCallBack {
        /**
         * 销毁client之前执行
         *
         * @param client
         */
        void destroy(TServiceClient client);

        /**
         * 创建成功是执行
         */
        void make(TServiceClient client);
    }

    @Override
    public void destroyObject(TServiceClient client) throws Exception {
        if (callback != null) {
            try {
                callback.destroy(client);
            } catch (Exception e) {
                logger.warn("destroyObject:{}", e);
            }
        }
        logger.info("destroyObject:{}", client);
        TTransport pin = client.getInputProtocol().getTransport();
        pin.close();
        TTransport pout = client.getOutputProtocol().getTransport();
        pout.close();
    }

    @Override
    public void activateObject(TServiceClient client) throws Exception {
    }

    @Override
    public void passivateObject(TServiceClient client) throws Exception {
    }

    @Override
    public boolean validateObject(TServiceClient client) {
        TTransport pin = client.getInputProtocol().getTransport();
        logger.info("validateObject input:{}", pin.isOpen());
        TTransport pout = client.getOutputProtocol().getTransport();
        logger.info("validateObject output:{}", pout.isOpen());
        return pin.isOpen() && pout.isOpen();
    }

    @Override
    public TServiceClient makeObject() throws Exception {
        InetSocketAddress address = serverAddressProvider.selector();
        if (address == null) {
            new ThriftException("No provider available for remote api");
        }
        TSocket tsocket = new TSocket(address.getHostName(), address.getPort());
        TTransport transport = new TFramedTransport(tsocket);
        TProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient client = this.clientFactory.getClient(protocol);
        transport.open();
        if (callback != null) {
            try {
                callback.make(client);
            } catch (Exception e) {
                logger.warn("makeObject:{}", e);
            }
        }
        return client;
    }

}
