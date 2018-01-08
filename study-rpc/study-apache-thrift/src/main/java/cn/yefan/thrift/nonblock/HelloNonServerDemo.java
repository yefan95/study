package cn.yefan.thrift.nonblock;

import cn.yefan.thrift.service.impl.HelloWorldServiceImpl;
import cn.yefan.thrift.service.HelloWorldService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.io.IOException;
import java.nio.channels.Selector;
import java.util.concurrent.Executors;

/**
 * @author yefan
 * @date 2018/01/07
 */
public class HelloNonServerDemo {

    public static final int SERVER_PORT = 8090;

    public static void startServer() {
        TProcessor tProcessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

        try {
            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(SERVER_PORT);
            serverTransport.registerSelector(Selector.open());
            THsHaServer.Args tArgs = new THsHaServer.Args(serverTransport);
            tArgs.processor(tProcessor);

            tArgs.executorService(Executors.newFixedThreadPool(100));

            THsHaServer server = new THsHaServer(tArgs);

            server.serve();

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startServer();
    }

}
