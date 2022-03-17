package com.abi.tmall.search.test.itcast;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

// ES测试类
public class ElasticSearchInitTest {

    // 客户端对象
    private RestHighLevelClient restHighLevelClient;

    /**
     * 建立连接
     */
    @Before
    public void init() throws IOException {
        // 创建Rest客户端
        restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")));
    }

    /**
     * 创建索引
     *
     * @throws IOException
     */
    @Test
    public void testCreateIndex() throws IOException {
        // 1、创建CreateIndexRequest对象，并指定索引库名称
        CreateIndexRequest request = new CreateIndexRequest("product");
        // 2、指定settings配置(可以默认)
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 1)
        );
        // 3、指定mapping配置
        request.mapping("_doc",
                "{\n" +
                        "    \"properties\": {\n" +
                        "      \"id\": {\n" +
                        "        \"type\": \"long\"\n" +
                        "      },\n" +
                        "      \"title\":{\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"price\":{\n" +
                        "        \"type\": \"integer\"\n" +
                        "      },\n" +
                        "      \"images\":{\n" +
                        "        \"type\": \"keyword\"\n" +
                        "      },\n" +
                        "      \"desc\":{\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"analyzer\": \"ik_max_word\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }",
                // 指定映射的内容的类型为json
                XContentType.JSON);
        // 4、发起请求，得到响应（同步操作）
        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        // 5、打印结果
        System.out.println("response = " + response.isAcknowledged());
    }

    /**
     * 关闭客户端连接
     */
    @After
    public void close() throws IOException {
        restHighLevelClient.close();
    }
}