package com.abi.tmall.search.test.itcast;

import com.abi.base.foundation.utils.JacksonUtils;
import com.abi.tmall.search.test.entity.Bank;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

// ES测试类
public class ElasticSearchSelectTest {

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

    @Test
    public void testMatchAllQuery() throws IOException {
        // 1、创建SearchRequest对象，并指定索引库名称
        SearchRequest request = new SearchRequest("bank");

        // ************ 构建查询条件************
        // 2、创建SearchSourceBuilder对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 3、添加查询条件QueryBuilders
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        // 4、添加SearchSourceBuilder对象到SearchRequest对象中
        request.source(sourceBuilder);

        // 5、发起请求，得到结果
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 6、解析结果
        SearchHits searchHits = response.getHits();
        // 7、获取总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("总记录数：" + total);

        // 8、获取SearchHits数组，并遍历
        for (SearchHit searchHit : searchHits) {
            // 取分数
            System.out.println("文档得分：" + searchHit.getScore());
            // 获取其中的`_source`，是JSON数据
            String productJson = searchHit.getSourceAsString();
            // 把`_source`反序列化为Product对象
            Bank bank = JacksonUtils.toBean(productJson, Bank.class);
            System.out.println(bank);
        }
    }

    /**
     * 抽取通用构建查询条件执行查询方法
     */
    public void printResultByQuery(QueryBuilder queryBuilder) throws Exception {
        // 1、创建SearchRequest对象，并指定索引库名称
        SearchRequest request = new SearchRequest("bank");

        // ************ 构建查询条件************
        // 2、创建SearchSourceBuilder对象
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        // 3、添加查询条件QueryBuilders
        sourceBuilder.query(queryBuilder);
        // 4、添加排序
        sourceBuilder.sort("accountNumber", SortOrder.DESC);
        // 5、添加SearchSourceBuilder对象到SearchRequest对象中
        request.source(sourceBuilder);

        // 6、发起请求，得到结果
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        // 7、解析结果
        SearchHits searchHits = response.getHits();
        // 8、获取总条数
        long total = searchHits.getTotalHits().value;
        System.out.println("总记录数：" + total);

        // 9、获取SearchHits数组，并遍历
        for (SearchHit searchHit : searchHits) {
            // 取分数
            System.out.println("文档得分：" + searchHit.getScore());
            // 获取其中的`_source`，是JSON数据
            String productJson = searchHit.getSourceAsString();
            // 把`_source`反序列化为Product对象
            Bank bank = JacksonUtils.toBean(productJson, Bank.class);
            System.out.println(bank);
        }
    }

    /**
     * 匹配查询：MatchQuery对条件进行分词
     */
    @Test
    public void testMatchQuery() throws Exception {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("address", "mill lane");
        queryBuilder.analyzer("ik_max_word"); // 指定分词器
        queryBuilder.operator(Operator.OR); // 分词后的各个词之间的关系 默认为OR
        printResultByQuery(queryBuilder);
    }

    /**
     * 短语匹配：MatchPhraseQuery不会进行分词
     */
    @Test
    public void testMatchPhraseQuery() throws Exception {
        MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("address", "789 Madison");
        printResultByQuery(queryBuilder);
    }

    /**
     * 多字段匹配：multiMatchQuery针对多个field进行检索
     */
    @Test
    public void testMultiMatchQuery() throws Exception {
        MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("mill movico", "address", "city");
        printResultByQuery(queryBuilder);
    }

    /**
     * 词条查询：MatchPhraseQuery不会进行分词
     */
    @Test
    public void testTermQuery() throws Exception {
        TermQueryBuilder queryBuilder = QueryBuilders.termQuery("age", "28");
        printResultByQuery(queryBuilder);
    }

    /**
     * 模糊查询：FuzzyQuery对查询条件可以修正
     */
    @Test
    public void testFuzzyQuery() throws Exception{
        FuzzyQueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("address", "Albemarlo");
        queryBuilder.fuzziness(Fuzziness.TWO); // 修正的次数，最大为2
        printResultByQuery(queryBuilder);
    }

    /**
     * 范围查询：
     */
    @Test
    public void testRangeQuery() throws Exception{
        RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("age");
        queryBuilder.gte(20); // 22 <= age < 27
        queryBuilder.lt(30);
        printResultByQuery(queryBuilder);
    }

    /**
     * 复合查询：BoolQuery 布尔查询 + 过滤
     */
    @Test
    public void testBoolQuery() throws Exception{
        // 1、构建bool条件对象
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        // 2、构建matchQuery对象，性别为M，地址中包含mill
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("gender", "M");
        queryBuilder.must(matchQueryBuilder);
        MatchQueryBuilder matchQueryBuilder2 = QueryBuilders.matchQuery("address", "mill");
        queryBuilder.must(matchQueryBuilder2);


        // 3、过滤姓名`name`包含：小武
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "小武");
        queryBuilder.filter(termQueryBuilder);

        // 4、过滤年龄`age`在：23-27
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age").gte(23).lte(27);
        queryBuilder.filter(rangeQueryBuilder);

        printResultByQuery(queryBuilder);
    }


    /**
     * 关闭客户端连接
     */
    @After
    public void close() throws IOException {
        restHighLevelClient.close();
    }

}