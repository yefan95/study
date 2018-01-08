package cn.yefan.thrift.block;

import cn.yefan.thrift.service.HelloWorldService;
import cn.yefan.thrift.iface.Reponse;
import cn.yefan.thrift.iface.Request;
import cn.yefan.thrift.iface.ServiceException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author yefan
 * @date 2018/01/07
 */
public class HelloClient {

    public static void main(String[] args) {
        TSocket transport = new TSocket("127.0.0.1", 9111);
        TProtocol protocol = new TBinaryProtocol(transport);
        try {
            // 准备调用参数
            Request request = new Request("{\"param\":\"field1\"}", "\\mySerivce\\queryService");
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            // 准备传输
            transport.open();
            // 正式调用接口
            Reponse reponse = client.send(request);
            System.out.println(reponse.toString());
            // 一定要记住关闭
            transport.close();
        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }
    }


}
