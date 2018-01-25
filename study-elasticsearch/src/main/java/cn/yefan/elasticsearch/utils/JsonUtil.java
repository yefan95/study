package cn.yefan.elasticsearch.utils;

import cn.yefan.elasticsearch.bean.Blog;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yefan
 * @date 2018/01/11
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public static String model2Json(Blog blog) {
        String jsonData = null;
        try {
            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
            jsonBuilder.startObject().field("id", blog.getId())
                    .field("title", blog.getTitle())
                    .field("postTime", blog.getPostTime())
                    .field("content", blog.getContent())
                    .endObject();
            jsonData = jsonBuilder.string();
            logger.info("json data = {}", jsonData);
        } catch (Exception e) {
            logger.error("error: {}", e);
        }
        return jsonData;
    }

}
