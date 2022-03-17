package com.abi.tmall.search.test.itcast;

import com.abi.base.foundation.utils.JacksonUtils;
import com.abi.tmall.search.test.entity.Product;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// ES测试类
public class ElasticSearchBulkTest {

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
     * 批量 添加数据
     */
    @Test
    public void testBulkAddDocumentList() throws IOException {
        // 1.从数据库查询文档数据
        //第一步：准备数据源。本案例使用List来模拟数据源。
        List<Product> productList = Arrays.asList(
                new Product(1L, "小米手机", "http://www.baidu.cim/1231245.jpg", 2399, "中国制造，质量妥妥的"),
                new Product(2L, "华为平板", "http://www.baidu.cim/1231245.jpg", 1399, "国产品牌，民族骄傲"),
                new Product(3L, "VIVO手机", "http://www.baidu.cim/1231245.jpg", 3399, "中国品牌，自主研发"),
                new Product(4L, "米家汽车", "http://www.baidu.cim/1231245.jpg", 6399, "米家品牌，舒适驾乘体验"),
                new Product(5L, "魅族电脑", "http://www.baidu.cim/1231245.jpg", 4399, "美国小品牌，质量一般"),
                new Product(6L, "三星手机", "http://www.baidu.cim/1231245.jpg", 8399, "韩国小品牌，经常爆炸"),
                new Product(7L, "苹果耳机", "http://www.baidu.cim/1231245.jpg", 9399, "年轻人的最爱，，质量可以"),
                new Product(8L, "OPPO手机", "http://www.baidu.cim/121245.jpg", 1399, "步步高的品牌"),
                new Product(9L, "小米平板", "http://www.baidu.cim/1231245.jpg", 2399, "小米制造，质量妥妥的"),
                new Product(10L, "大米耳机", "http://www.baidu.cim/1231245.jpg", 2399, "大米制造，质量妥妥的")
        );
        // 2.创建BulkRequest对象
        BulkRequest bulkRequest = new BulkRequest();
        // 3.创建多个IndexRequest对象，并添加到BulkRequest中
        for (Product product : productList) {
            bulkRequest.add(new IndexRequest("product").id(product.getId().toString()).source(JacksonUtils.toJson(product), XContentType.JSON));
        }
        // 4.发起请求
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("status: " + bulkResponse.status());
    }

    /**
     * 批量 修改数据
     */
    @Test
    public void testBulkUpdDocumentList() throws IOException {
        // 1.从数据库查询文档数据
        //第一步：准备数据源。本案例使用List来模拟数据源。
        List<Product> productList = Arrays.asList(
                new Product(1L, "小米手机", "http://www.baidu.cim/1231245.jpg", 2399, "中国制造，质量妥妥的"),
                new Product(2L, "华为平板", "http://www.baidu.cim/1231245.jpg", 1399, "国产品牌，民族骄傲"),
                new Product(3L, "VIVO手机", "http://www.baidu.cim/1231245.jpg", 3399, "中国品牌，自主研发"),
                new Product(4L, "米家汽车", "http://www.baidu.cim/1231245.jpg", 6399, "米家品牌，舒适驾乘体验"),
                new Product(5L, "魅族电脑", "http://www.baidu.cim/1231245.jpg", 4399, "美国小品牌，质量一般"),
                new Product(6L, "三星手机", "http://www.baidu.cim/1231245.jpg", 8399, "韩国小品牌，经常爆炸"),
                new Product(7L, "苹果耳机", "http://www.baidu.cim/1231245.jpg", 9399, "年轻人的最爱，，质量可以"),
                new Product(8L, "OPPO手机", "http://www.baidu.cim/121245.jpg", 1399, "步步高的品牌"),
                new Product(9L, "小米平板", "http://www.baidu.cim/1231245.jpg", 2399, "小米制造，质量妥妥的"),
                new Product(10L, "大米耳机", "http://www.baidu.cim/1231245.jpg", 2399, "大米制造，质量妥妥的")
        );
        // 2.创建BulkRequest对象
        BulkRequest bulkRequest = new BulkRequest();
        // 3.创建多个IndexRequest对象，并添加到BulkRequest中
        for (Product product : productList) {
            //bulkRequest.add(new UpdateRequest("product").id(Product.getId().toString()).source(JSON.toJSONString(Product), XContentType.JSON));
        }
        // 4.发起请求
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("status: " + bulkResponse.status());
    }

    /**
     * 批量 删除数据
     */
    @Test
    public void testBulkDelDocumentList() throws IOException {
        // 1.从数据库查询文档数据
        //第一步：准备数据源。本案例使用List来模拟数据源。
        List<Product> productList = Arrays.asList(
                new Product(1L, "小米手机", "http://www.baidu.cim/1231245.jpg", 2399, "中国制造，质量妥妥的"),
                new Product(2L, "华为平板", "http://www.baidu.cim/1231245.jpg", 1399, "国产品牌，民族骄傲"),
                new Product(3L, "VIVO手机", "http://www.baidu.cim/1231245.jpg", 3399, "中国品牌，自主研发"),
                new Product(4L, "米家汽车", "http://www.baidu.cim/1231245.jpg", 6399, "米家品牌，舒适驾乘体验"),
                new Product(5L, "魅族电脑", "http://www.baidu.cim/1231245.jpg", 4399, "美国小品牌，质量一般"),
                new Product(6L, "三星手机", "http://www.baidu.cim/1231245.jpg", 8399, "韩国小品牌，经常爆炸"),
                new Product(7L, "苹果耳机", "http://www.baidu.cim/1231245.jpg", 9399, "年轻人的最爱，，质量可以"),
                new Product(8L, "OPPO手机", "http://www.baidu.cim/121245.jpg", 1399, "步步高的品牌"),
                new Product(9L, "小米平板", "http://www.baidu.cim/1231245.jpg", 2399, "小米制造，质量妥妥的"),
                new Product(10L, "大米耳机", "http://www.baidu.cim/1231245.jpg", 2399, "大米制造，质量妥妥的")
        );
        // 2.创建BulkRequest对象
        BulkRequest bulkRequest = new BulkRequest();
        // 3.创建多个IndexRequest对象，并添加到BulkRequest中
        for (Product product : productList) {
            bulkRequest.add(new DeleteRequest("product").id(product.getId().toString()));
        }
        // 4.发起请求
        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("status: " + bulkResponse.status());
    }

    /**
     * 关闭客户端连接
     */
    @After
    public void close() throws IOException {
        restHighLevelClient.close();
    }

}