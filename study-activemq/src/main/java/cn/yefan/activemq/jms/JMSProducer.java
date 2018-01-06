package cn.yefan.activemq.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * @author yefan
 * @date 2018/01/06
 */
public class JMSProducer {

    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");

        Session session = null;

        Destination sendQueue = null;

        Connection connection = null;

        try {
            connection = connectionFactory.createConnection();
            connection.start();
            //建立会话（设置一个带有事务特性的会话）
            session = connection.createSession(true, Session.SESSION_TRANSACTED);
            //建立queue（当然如果有了就不会重复建立）
            sendQueue = session.createQueue("/test");
            //建立消息发送者对象
            MessageProducer sender = session.createProducer(sendQueue);

            while (true) {
                //发送消息
                sendTextMessage(sender, session);
                Thread.sleep(5000);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sendTextMessage(MessageProducer sender, Session session) {
        TextMessage outMessage = null;
        try {
            outMessage = session.createTextMessage();
            outMessage.setText("current time is " + new Date().toString());
            //发送（JMS是支持事务的）
            sender.send(outMessage);
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


}
