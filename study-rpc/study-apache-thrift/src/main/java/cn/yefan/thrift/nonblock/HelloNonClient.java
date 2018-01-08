package cn.yefan.thrift.nonblock;

import cn.yefan.thrift.iface.Reponse;
import cn.yefan.thrift.iface.Request;
import cn.yefan.thrift.service.HelloWorldService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TNonblockingSocket;

import java.io.IOException;

/**
 * @author yefan
 * @date 2018/01/07
 */
public class HelloNonClient {

    private static Object WAITOBJECT = new Object();

    public static void main(String[] args) {
        try {
            TNonblockingSocket transport = new TNonblockingSocket("127.0.0.1", 8090);
            TAsyncClientManager clientManager = new TAsyncClientManager();

            Request request = new Request("{\"param\":\"field1\"}", "\\mySerivce\\queryService");

            HelloWorldService.AsyncClient asyncClient = new HelloWorldService.AsyncClient.Factory(clientManager, new TBinaryProtocol.Factory())
                    .getAsyncClient(transport);

            try {
                asyncClient.send(request, new AsyncMethodCallback<Reponse>() {

                    @Override
                    public void onComplete(Reponse reponse) {
                        System.out.println(reponse.toString());
                    }

                    @Override
                    public void onError(Exception e) {
                        System.out.println("error: " + e.getMessage());
                    }
                });
            } catch (TException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (WAITOBJECT) {
            try {
                WAITOBJECT.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
