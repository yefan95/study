package cn.yefan.thrift.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class BootStrap {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("spring-context-thrift-server.xml");
        ac.start();
        System.out.println("服务已经启动。。。。");
    }
}
