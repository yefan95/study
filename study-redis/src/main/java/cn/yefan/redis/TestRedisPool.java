package cn.yefan.redis;

import cn.yefan.redis.utils.JedisUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yefan
 * @date 2018/01/06
 */
public class TestRedisPool {

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("id", "20243525346");
        hashMap.put("name", "admin");
        hashMap.put("sex", "男");
        hashMap.put("age", "24");
        boolean code = JedisUtil.getInstance().hmset("user", hashMap);
        JedisUtil.getInstance().expire("user", JedisUtil.EXPIRE_HOUR);

        System.out.println("code= " + code);

        String sex = JedisUtil.getInstance().hget("user", "sex");
        System.out.println("性别: " + sex);
    }

}
