package cn.yefan.elasticsearch.utils;

import cn.yefan.elasticsearch.TestElasticsearch;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

/**
 * @author yefan
 * @date 2018/01/12
 */
public class EsClientUtil {

    private static final String HOST_NAME = "127.0.0.1";
    private static final Logger logger = LoggerFactory.getLogger(EsClientUtil.class);

    private static TransportClient client;

    static {

        Settings settings = Settings.builder()
                .put("client.transport.sniff", true)
                .put("cluster.name", "elasticsearch-cluster")
                .build();

        client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(new InetSocketAddress(HOST_NAME, 9300)));
    }

    public static void showNodesInfo() {
        logger.info("=====");
        List<DiscoveryNode> nodeList = client.connectedNodes();
        for (DiscoveryNode node : nodeList) {
            logger.info("node= {}", node.toString());
        }
    }

    public static void createIndex(List<String> dataList) {

        for (int i = 0; i < dataList.size(); i++) {
            IndexResponse response = client.prepareIndex("blog", "article", "" + (i + 1))
                    .setSource(dataList.get(i), XContentType.JSON)
                    .get();
        }
    }

    public static boolean isIndexExist(String index) {
        IndicesExistsResponse inExistsResponse = client.admin().indices().exists(new IndicesExistsRequest(index))
                .actionGet();
        if (inExistsResponse.isExists()) {
            logger.info("Index [" + "{}" + "] is exist!", index);
        } else {
            logger.info("Index [" + "{}" + "] is not exist!");
        }
        return inExistsResponse.isExists();
    }

    public static boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            logger.info("Index is not exits!");
            return false;
        }
        DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (deleteIndexResponse.isAcknowledged()) {
            logger.info("delete index {} successfully!", index);
        } else {
            logger.info("fail to delete index {}", index);
        }
        return deleteIndexResponse.isAcknowledged();
    }

    public static Map<String, Object> getSourceById(String index, String type, String id, String fields) {
        GetResponse getResponse = client.prepareGet(index, type, id).setFetchSource(fields.split(","), null).get();
        logger.info("data = {}", getResponse.getSourceAsString());
        return getResponse.getSource();
    }


    public static SearchHit[] search(String indexName, String type, QueryBuilder queryBuilder) {
        SearchResponse searchResponse = client.prepareSearch(indexName)
                .setTypes(type)
                .setQuery(queryBuilder)
                .get();
        return searchResponse.getHits().getHits();
    }

    public static void update(String indexName, String type, String id, String json) {
        UpdateResponse updateResponse = client.prepareUpdate(indexName, type, id).setDoc(json, XContentType.JSON).get();
    }
}
