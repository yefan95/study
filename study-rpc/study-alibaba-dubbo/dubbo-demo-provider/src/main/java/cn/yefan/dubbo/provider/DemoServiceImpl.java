package cn.yefan.dubbo.provider;

import cn.yefan.dubbo.api.DemoService;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class DemoServiceImpl implements DemoService {
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
