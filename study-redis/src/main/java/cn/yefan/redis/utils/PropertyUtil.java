package cn.yefan.redis.utils;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author yefan
 * @date 2018/01/06
 */
public class PropertyUtil {

    public static Properties loadProperties(String propertyFile) {
        Properties properties = new Properties();
        try {
            InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(propertyFile);
            if (is == null) {
                is = PropertyUtil.class.getClassLoader().getResourceAsStream("redis.properties");
            }
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return properties;
    }

    public static String getValue(String propertyFile, String key) {
        Properties properties = loadProperties(propertyFile);
        return properties.getProperty(key);
    }

}
