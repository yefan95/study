package cn.yefan.activemq.stomp;

import org.apache.activemq.transport.stomp.StompConnection;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author yefan
 * @date 2018/0/06
 */
public class TestProducer {


    public static void main(String[] args) {
        try {
            StompConnection conn = new StompConnection();
            Socket socket = new Socket("127.0.0.1", 61613);
            conn.open(socket);
            // 注意，协议版本可以是1.2，也可以是1.1
            conn.setVersion("1.2");
            // 设置用户名和密码
            conn.connect("admin", "admin");
            while (true) {
                Thread.sleep(5000);
                // 以下发送一条信息（也可以使用“事务”方式）
                conn.send("/map", "current time is " + new Date().toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



