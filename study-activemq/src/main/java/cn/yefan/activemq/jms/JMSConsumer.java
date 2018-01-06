package cn.yefan.activemq.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author yefan
 * @date 2018/01/06
 */
public class JMSConsumer {

    public static void main(String[] args) {
        // 定义JMS-ActiveMQ连接信息
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Session session = null;
        Destination sendQueue = null;
        Connection connection = null;

        try {
            //进行连接
            connection = connectionFactory.createConnection();
            connection.start();

            //建立会话(设置为自动ack)
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //建立Queue（当然如果有了就不会重复建立）
            sendQueue = session.createQueue("/test");
            //建立消息发送者对象
            MessageConsumer consumer = session.createConsumer(sendQueue);
            consumer.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    // 接收到消息后，不需要再发送ack了。
                    System.out.println("Message= " + message);
                }
            });

            synchronized (JMSConsumer.class) {
                JMSConsumer.class.wait();
            }
            //关闭
            consumer.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
