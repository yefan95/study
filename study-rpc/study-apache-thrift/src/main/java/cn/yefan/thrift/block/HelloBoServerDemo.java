package cn.yefan.thrift.block;

import cn.yefan.thrift.service.impl.HelloWorldServiceImpl;
import cn.yefan.thrift.service.HelloWorldService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.util.concurrent.Executors;

/**
 * @author yefan
 * @date 2018/01/07
 */
public class HelloBoServerDemo {

    public static final int SERVER_PORT = 9111;

    public static void startServer() {
        // 服务执行控制器（只要是调度服务的具体实现该如何运行）
        TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

        // 基于阻塞式同步IO模型的Thrift服务，正式生产环境不建议用这个
        try {
            TServerSocket serverTransport = new TServerSocket(SERVER_PORT);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
            // 为这个服务器设置对应的IO网络模型、设置使用的消息格式封装、设置线程池参数
            tArgs.processor(tProcessor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.executorService(Executors.newFixedThreadPool(100));
            // 启动这个thrift服务
            TThreadPoolServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startServer();
    }

}
