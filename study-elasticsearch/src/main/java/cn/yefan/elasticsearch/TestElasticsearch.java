package cn.yefan.elasticsearch;

import cn.yefan.elasticsearch.bean.Blog;
import cn.yefan.elasticsearch.utils.EsClientUtil;
import cn.yefan.elasticsearch.utils.JsonUtil;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yefan
 * @date 2018/01/11
 */
public class TestElasticsearch {

    private static final Logger logger = LoggerFactory.getLogger(TestElasticsearch.class);

    public static List<String> getInitJsonData() {
        List<String> list = new ArrayList<String>();
        String data1 = JsonUtil.model2Json(new Blog(1, "git简介", new Date(), "SVN与Git最主要的区别..."));
        String data2 = JsonUtil.model2Json(new Blog(2, "Java中泛型的介绍与简单使用", new Date(), "学习目标 掌握泛型的产生意义..."));
        String data3 = JsonUtil.model2Json(new Blog(3, "SQL基本操作", new Date(), "基本操作：CRUD ..."));
        String data4 = JsonUtil.model2Json(new Blog(4, "Hibernate框架基础", new Date(), "Hibernate框架基础..."));
        String data5 = JsonUtil.model2Json(new Blog(5, "Shell基本知识", new Date(), "Shell是什么..."));
        String data6 = JsonUtil.model2Json(new Blog(6, "Java入门指南", new Date(), "Java基础知识..."));
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);
        return list;
    }

    public static void main(String[] args) {

//        boolean isDeleted = EsClientUtil.deleteIndex("blog");

//        EsClientUtil.createIndex(getInitJsonData());

//        Map<String, Object> map = EsClientUtil.getSourceById("blog", "article", "1", "title,content");

        QueryBuilder termQuery = QueryBuilders.termQuery("id", 1);

        QueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("content", "基础").fuzziness(Fuzziness.ONE).prefixLength(0);

        SearchHit[] hits = EsClientUtil.search("blog", "article", fuzzyQuery);

        for (SearchHit hit : hits) {
            logger.info(hit.getSourceAsString());
        }

        String json = JsonUtil.model2Json(new Blog(1, "git简介....", new Date(), "SVN与Git最主要的区别..."));
        EsClientUtil.update("blog", "article", "1", json);


    }

}
