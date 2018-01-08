package cn.yefan.thrift.server.service;

import cn.yefan.thrift.api.SmartService;
import cn.yefan.thrift.api.bean.User;
import org.apache.thrift.TException;

/**
 * @author yefan
 * @date 2018/01/08
 */
public class SmartServiceImpl implements SmartService.Iface {
    public User getUserById(int uid) throws TException {
        System.out.println("rpc start ,id= " + uid);
        return new User(1, "admin", "123456");
    }
}
