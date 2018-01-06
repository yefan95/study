package cn.yefan.redis;

import cn.yefan.redis.utils.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class TestJedis {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        System.out.println("================list操作==================");
        jedis.lpush("list", "Java");
        jedis.lpush("list", "C++");
        jedis.lpush("list", "Python");
        jedis.lpush("list", "PHP");
        jedis.expire("list", 10);

        List<String> listCache = jedis.lrange("list", 0, 4);

        for (int i = 0; i < listCache.size(); i++) {
            System.out.println(listCache.get(i));
        }

        System.out.println("================map操作==================");
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("a", "java");
        hashMap.put("b", "php");
        hashMap.put("c", "android");
        jedis.hmset("map", hashMap);
        jedis.expire("map", 10);
        //获取hash类型缓存数据
        Map<String, String> hashData = jedis.hgetAll("map");
        System.out.println("获取hash缓存数据（a）：" + hashData.get("a"));
        String value = jedis.hget("map", "a");
        System.out.println("获取缓存数据（a）：" + value);
        jedis.hdel("map", "b");
    }

}
