package cn.yefan.thrift.server.service;

import cn.yefan.thrift.api.HelloService;
import org.apache.thrift.TException;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class HelloServiceImpl implements HelloService.Iface {
    public void sayHello() throws TException {
        System.out.println("hello world!");
    }
}
