package cn.yefan.thrift.client;

import cn.yefan.thrift.api.HelloService;
import cn.yefan.thrift.api.SmartService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class SmartServiceTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-context-thrift-client.xml");
        SmartService.Iface smartSerivce = (SmartService.Iface) context.getBean("smartService");
        HelloService.Iface helloService = (HelloService.Iface) context.getBean("helloService");
        try {
            System.out.println(smartSerivce.getUserById(1));
            helloService.sayHello();
            System.out.println("测试成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
