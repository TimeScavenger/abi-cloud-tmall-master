package com.abi.tmall.search.test.itcast;

import com.abi.base.foundation.utils.JacksonUtils;
import com.abi.tmall.search.test.entity.Product;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

// ES测试类
public class ElasticSearchCrudTest {

    // 客户端对象
    private RestHighLevelClient restHighLevelClient;

    /**
     * 建立连接
     */
    @Before
    public void init() throws IOException {
        // 创建Rest客户端
        restHighLevelClient = new RestHighLevelClient(
                RestClient.builder(
                        // 如果是集群，则设置多个主机，注意端口是http协议的端口
                        new HttpHost("127.0.0.1", 9200, "http")
//                        ,new HttpHost("localhost", 9201, "http")
//                        ,new HttpHost("localhost", 9202, "http")
                )
        );
    }

    /**
     * 插入一个文档
     *
     * @throws IOException
     */
    @Test
    public void testAddDocument() throws Exception {
        // 1. 准备文档数据
        Product product = new Product(192939L, "小牛汽车", "http://www.163.com/12312.jpg", 2300, "中国制造");
        // 2. 创建IndexRequest对象，并指定索引库名称
        IndexRequest indexRequest = new IndexRequest("product");
        // 3. 指定新增的数据的id
        indexRequest.id(product.getId().toString());
        // 4. 将新增的文档数据变成JSON格式
        //  user.setAge(null);
        String userJson = JacksonUtils.toJson(product);
        // 5. 将JSON数据添加到IndexRequest中
        indexRequest.source(userJson, XContentType.JSON);
        // 6. 发起请求，得到结果
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("indexResponse= " + response.getResult());
    }

    /**
     * 根据id查询一个文档
     *
     * @throws IOException
     */
    @Test
    public void testfindDocumentById() throws Exception {
        // 1. 创建GetRequest 对象，并指定索引库名称、文档ID
        GetRequest getRequest = new GetRequest("product", "192939");
        // 2. 发起请求，得到结果
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        // 3. 从结果中得到source，是json字符串
        String sourceAsString = response.getSourceAsString();
        // 4. 将JSON反序列化为对象
        Product product = JacksonUtils.toBean(sourceAsString, Product.class);
        System.out.println(product);
    }

    /**
     * 根据id删除文档
     *
     * @throws IOException
     */
    @Test
    public void testDeleteDocumentById() throws IOException {
        // 1.创建DeleteRequest对象，指定索引库名称、文档ID
        DeleteRequest request = new DeleteRequest("product", "110");
        // 2.发起请求
        DeleteResponse deleteResponse = restHighLevelClient.delete(
                request, RequestOptions.DEFAULT);

        System.out.println("deleteResponse = " + deleteResponse.getResult());
    }

    /**
     * 关闭客户端连接
     */
    @After
    public void close() throws IOException {
        restHighLevelClient.close();
    }

}