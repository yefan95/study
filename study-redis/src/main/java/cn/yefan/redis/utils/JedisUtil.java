package cn.yefan.redis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author yefan
 * @date 2018/01/05
 */
public class JedisUtil {

    private static JedisPool jedisPool = null;
    /**
     * 一分钟
     */
    public final static int EXPIRE_MIN = 60;
    /**
     * 一小时
     */
    public final static int EXPIRE_HOUR = 60 * EXPIRE_MIN;
    /**
     * 一天
     */
    public final static int EXPIRE_DAY = EXPIRE_HOUR * 24;
    /**
     * 一个月
     */
    public final static int EXRP_MONTH = EXPIRE_DAY * 30;

    private static final String OK = "OK";

    private JedisUtil() {
    }

    private static final JedisUtil jedisUtil = new JedisUtil();

    /**
     * 获取JedisUtil实例
     *
     * @return
     */
    public static JedisUtil getInstance() {
        return jedisUtil;
    }

    static {
        Properties properties = PropertyUtil.loadProperties("redis.properties");
        String host = properties.getProperty("redis.host");
        String port = properties.getProperty("redis.port");
        String pass = properties.getProperty("redis.pass");
        String timeout = properties.getProperty("redis.timeout");
        String maxIdle = properties.getProperty("redis.maxIdle");
        String maxTotal = properties.getProperty("redis.maxTotal");
        String maxWaitMillis = properties.getProperty("redis.maxWaitMillis");
        String testOnBorrow = properties.getProperty("redis.testOnBorrow");

        JedisPoolConfig config = new JedisPoolConfig();
        //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
        //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
        config.setMaxTotal(Integer.parseInt(maxTotal));
        //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
        config.setMaxIdle(Integer.parseInt(maxIdle));
        //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
        config.setMaxWaitMillis(Long.parseLong(maxWaitMillis));
        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
        config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));

        jedisPool = new JedisPool(config, host, Integer.parseInt(port), Integer.parseInt(timeout), null);
    }

    private Jedis getJedis() {
        return jedisPool.getResource();
    }

    /**
     * 回收jedis(放到finally中)
     *
     * @param jedis
     */
    private void returnJedis(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 销毁连接(放到catch中)
     *
     * @param jedis
     */
    private static void returnBrokenResource(Jedis jedis) {
        if (null != jedis && null != jedisPool) {
            jedisPool.returnResource(jedis);
        }
    }


    /**
     * 添加对应关系，如果对应关系已存在，则覆盖
     *
     * @param key
     * @param map 对应关系
     * @return 状态，成功返回OK
     */
    public boolean hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        String code = jedis.hmset(key, map);
        returnJedis(jedis);
        return OK.equals(code);
    }

    public Map<String, String> hgetAll(String key) {
        Jedis jedis = getJedis();
        return jedis.hgetAll(key);
    }

    public String hget(String key, String field) {
        Jedis jedis = getJedis();
        return jedis.hget(key, field);
    }

    /**
     * 添加sorted set
     *
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, String value, double score) {
        Jedis jedis = getJedis();
        jedis.zadd(key, score, value);
        returnJedis(jedis);
    }

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrange(key, start, end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 获取给定区间的元素，原始按照权重由高到低排序
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        Set<String> set = jedis.zrevrange(key, start, end);
        returnJedis(jedis);
        return set;
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    public long rpush(String key, String value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        returnJedis(jedis);
        return count;
    }

    /**
     * 向List头部追加记录
     *
     * @param key
     * @param value
     * @return 记录总数
     */
    private long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        long count = jedis.rpush(key, value);
        returnJedis(jedis);
        return count;
    }

    /**
     * 设置失效时间
     *
     * @param key
     * @param seconds
     */
    public void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        returnJedis(jedis);
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public long del(String key) {
        Jedis jedis = getJedis();
        long s = jedis.del(key);
        returnJedis(jedis);
        return s;
    }

    /**
     * 从集合中删除成员
     *
     * @param key
     * @param value
     * @return 返回1成功
     */
    public long zrem(String key, String... value) {
        Jedis jedis = getJedis();
        long s = jedis.zrem(key, value);
        returnJedis(jedis);
        return s;
    }

}
