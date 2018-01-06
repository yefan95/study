package cn.yefan.activemq.stomp;

import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Map;

/**
 * @author yefan
 * @date 2018/01/06
 */
public class TestConsumer {

    public static void main(String[] args) {
        StompConnection conn = new StompConnection();
        try {
            // 建立连接
            Socket socket = new Socket("127.0.0.1", 61613);
            conn.open(socket);
            conn.setVersion("1.2");
            conn.connect("admin", "admin");
            String ack = "client";
            conn.subscribe("/test", "client");
            // 接受消息（使用循环进行）
            for (; ; ) {
                StompFrame frame = null;
                try {
                    // 注意，如果没有接收到消息，
                    // 这个消费者线程会停在这里，直到本次等待超时
                    frame = conn.receive();
                } catch (SocketTimeoutException e) {
                    continue;
                }
                // 打印本次接收到的消息
                System.out.println("frame.getAction()= " + frame.getAction());
                Map<String, String> headers = frame.getHeaders();
                String message_id = headers.get("message-id");
                System.out.println("frame.getBody()= " + frame.getBody());
                System.out.println("frame.getCommandId()= " + frame.getCommandId());
                // 在ack是client标记的情况下，确认消息
                if ("client".equals(ack)) {
                    conn.ack(message_id);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
