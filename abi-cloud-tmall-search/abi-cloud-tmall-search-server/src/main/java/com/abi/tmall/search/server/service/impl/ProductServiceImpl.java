package com.abi.tmall.search.server.service.impl;

import com.abi.base.foundation.utils.JacksonUtils;
import com.abi.tmall.search.common.constant.ProductConstant;
import com.abi.tmall.search.common.request.SkuEsAddReq;
import com.abi.tmall.search.common.request.SkuEsSearchReq;
import com.abi.tmall.search.common.response.SkuEsModel;
import com.abi.tmall.search.common.response.SkuEsSearchResp;
import com.abi.tmall.search.server.client.ProductFeignClient;
import com.abi.tmall.search.server.config.ElasticSearchConfig;
import com.abi.tmall.search.server.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.MultiBucketsAggregation;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: ProductServiceImpl
 * @Author: illCodean
 * @CreateDate: 2021/5/18
 * @Description: 商品模块
 */
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ProductFeignClient productFeignClient;

    @Override
    public SkuEsSearchResp searchProductFromEs(SkuEsSearchReq skuEsSearchReq) {
        // 1.动态构建出查询需要的DSL语句
        SkuEsSearchResp skuEsSearchResp = new SkuEsSearchResp();

        // 2.准备检索请求
        SearchRequest searchRequest = buildSearchRequest(skuEsSearchReq);

        try {
            // 3.执行检索请求
            SearchResponse response = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);

            // 4.分析响应数据，封装成我们需要的格式
            skuEsSearchResp = buildSearchResponse(response, skuEsSearchReq);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return skuEsSearchResp;
    }

    /**
     * 准备检索请求
     * 模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存），排序，分页，高亮，聚合分析
     *
     * @return
     */
    private SearchRequest buildSearchRequest(SkuEsSearchReq req) {
        // 1.构建检索请求
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        /**
         * 2.查询：模糊匹配，过滤（按照属性，分类，品牌，价格区间，库存）
         */
        // 2.1构建 bool-query
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        // 2.2.bool-must 模糊匹配
        if (StringUtils.isNotBlank(req.getSkuTitle())) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("skuTitle", req.getSkuTitle()));
        }

        // 2.3.bool-filter catalogId 按照三级分类id查询
        if (req.getCategoryId() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("categoryId", req.getCategoryId()));
        }
        // 2.4.bool-filter brandId 按照品牌id查询
        if (req.getBrandId() != null && req.getBrandId().size() > 0) {
            boolQueryBuilder.filter(QueryBuilders.termsQuery("brandId", req.getBrandId()));
        }
        // 2.5.bool-filter attrs 按照指定的属性查询
        if (CollectionUtils.isNotEmpty(req.getAttrs())) {
            // attrs=1_5寸:8寸&2_16G:8G
            req.getAttrs().forEach(item -> {
                BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
                // attrs=1_5寸:8寸
                String[] s = item.split("_");
                String attrCode = s[0]; // 检索的属性id
                String[] attrValue = s[1].split(":"); // 这个属性检索用的值
                boolQuery.must(QueryBuilders.termQuery("attrs.attrCode", attrCode));
                boolQuery.must(QueryBuilders.termsQuery("attrs.attrValue", attrValue));

                // 每一个属性都要生成一个 nested 查询
                NestedQueryBuilder nestedQueryBuilder = QueryBuilders.nestedQuery("attrs", boolQuery, ScoreMode.None);
                boolQueryBuilder.filter(nestedQueryBuilder);
            });
        }
        // 2.6.bool-filter hasStock 按照是否有库存查询
        if (req.getHasStock() != null) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("hasStock", req.getHasStock() == 1));
        }
        // 2.7.bool-filter skuPrice 按照价格区间查询
        if (StringUtils.isNotBlank(req.getSkuPrice())) {
            // skuPrice形式为：1_500或_500或500_
            RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("skuPrice");
            String[] price = req.getSkuPrice().split("_");
            if (req.getSkuPrice().startsWith("_")) {
                rangeQueryBuilder.lte(price[1]);
            } else if (req.getSkuPrice().endsWith("_")) {
                rangeQueryBuilder.gte(price[0]);
            } else if (price[0] != null && price[1] != null) {
                rangeQueryBuilder.gte(price[0]).lte(price[1]);
            }
            boolQueryBuilder.filter(rangeQueryBuilder);
        }
        // 封装所有的查询条件
        searchSourceBuilder.query(boolQueryBuilder);
        log.info("调用ES查询商品列表，DSL语句查询：【{}】", searchSourceBuilder.toString());

        /**
         * 3.排序，分页，高亮
         */
        // 3.1.排序 sort=hotScore_asc/desc
        if (!StringUtils.isEmpty(req.getSort())) {
            String sort = req.getSort();
            // sort=hotScore_asc/desc
            String[] sortFields = sort.split("_");
            SortOrder sortOrder = "asc".equalsIgnoreCase(sortFields[1]) ? SortOrder.ASC : SortOrder.DESC;
            searchSourceBuilder.sort(sortFields[0], sortOrder);
        }

        // 3.2.分页 from = (pageNum - 1) * pageSize
        searchSourceBuilder.from((req.getPageNum() - 1) * ProductConstant.PRODUCT_PAGE_SIZE);
        searchSourceBuilder.size(ProductConstant.PRODUCT_PAGE_SIZE);

        // 3.3.高亮
        if (!StringUtils.isEmpty(req.getSkuTitle())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }
        log.info("调用ES查询商品列表，DSL语句查询、排序、分页、高亮：【{}】", searchSourceBuilder.toString());

        /**
         * 4.聚合分析
         */
        // 4.1.按照品牌进行聚合
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg");
        brand_agg.field("brandId").size(50);
        // 品牌的子聚合-品牌名聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        // 品牌的子聚合-品牌图片聚合
        brand_agg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        searchSourceBuilder.aggregation(brand_agg);

        // 4.2.按照分类信息进行聚合
        TermsAggregationBuilder catalog_agg = AggregationBuilders.terms("catalog_agg");
        catalog_agg.field("catalogId").size(20);
        catalog_agg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        searchSourceBuilder.aggregation(catalog_agg);

        // 4.3.按照属性信息进行聚合
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrs");
        // 按照属性ID进行聚合
        TermsAggregationBuilder attr_id_agg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        attr_agg.subAggregation(attr_id_agg);
        // 在每个属性ID下，按照属性名进行聚合
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        // 在每个属性ID下，按照属性值进行聚合
        attr_id_agg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        searchSourceBuilder.aggregation(attr_agg);
        log.info("调用ES查询商品列表，DSL语句查询、排序、分页、高亮、聚合：【{}】", searchSourceBuilder.toString());

        // 5.构建SearchRequest
        SearchRequest searchRequest = new SearchRequest(new String[]{ProductConstant.PRODUCT_INDEX}, searchSourceBuilder);
        return searchRequest;
    }

    /**
     * 构建结果数据
     * 模糊匹配，过滤（按照属性、分类、品牌，价格区间，库存），完成排序、分页、高亮，聚合分析功能
     *
     * @param response
     * @param req
     * @return
     */
    private SkuEsSearchResp buildSearchResponse(SearchResponse response, SkuEsSearchReq req) {
        // 1.定义返回参数
        SkuEsSearchResp result = new SkuEsSearchResp();

        // 2.返回的所有查询到的商品
        SearchHits hits = response.getHits();

        List<SkuEsModel> esModels = new ArrayList<>();
        // 遍历所有商品信息
        if (hits.getHits() != null && hits.getHits().length > 0) {
            for (SearchHit hit : hits.getHits()) {
                String sourceAsString = hit.getSourceAsString();
                SkuEsModel esModel = JacksonUtils.toBean(sourceAsString, SkuEsModel.class);
                // 判断是否按关键字检索，若是就显示高亮，否则不显示
                if (!StringUtils.isEmpty(req.getSkuTitle())) {
                    // 拿到高亮信息显示标题
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    String skuTitleValue = skuTitle.getFragments()[0].string();
                    esModel.setSkuTitle(skuTitleValue);
                }
                esModels.add(esModel);
            }
        }
        result.setProduct(esModels);

        // 3.当前商品涉及到的所有属性信息
        List<SkuEsSearchResp.AttrVo> attrVos = new ArrayList<>();
        // 获取属性信息的聚合
        ParsedNested attrsAgg = response.getAggregations().get("attr_agg");
        ParsedLongTerms attrIdAgg = attrsAgg.getAggregations().get("attr_id_agg");
        for (Terms.Bucket bucket : attrIdAgg.getBuckets()) {
            SkuEsSearchResp.AttrVo attrVo = new SkuEsSearchResp.AttrVo();
            // 3.1.得到属性的id
            long attrId = bucket.getKeyAsNumber().longValue();
            attrVo.setAttrCode(attrId);

            // 3.2.得到属性的名字
            ParsedStringTerms attrNameAgg = bucket.getAggregations().get("attr_name_agg");
            String attrName = attrNameAgg.getBuckets().get(0).getKeyAsString();
            attrVo.setAttrName(attrName);

            // 3.3.得到属性的所有值
            ParsedStringTerms attrValueAgg = bucket.getAggregations().get("attr_value_agg");
            List<String> attrValues = attrValueAgg.getBuckets().stream().map(MultiBucketsAggregation.Bucket::getKeyAsString).collect(Collectors.toList());
            attrVo.setAttrValue(attrValues);

            attrVos.add(attrVo);
        }
        result.setAttrs(attrVos);

        // 4.当前商品涉及到的所有品牌信息
        List<SkuEsSearchResp.BrandVo> brandVos = new ArrayList<>();
        // 获取到品牌的聚合
        ParsedLongTerms brandAgg = response.getAggregations().get("brand_agg");
        for (Terms.Bucket bucket : brandAgg.getBuckets()) {
            SkuEsSearchResp.BrandVo brandVo = new SkuEsSearchResp.BrandVo();

            // 4.1.得到品牌的id
            long brandId = bucket.getKeyAsNumber().longValue();
            brandVo.setBrandCode(brandId);

            // 4.2.得到品牌的名字
            ParsedStringTerms brandNameAgg = bucket.getAggregations().get("brand_name_agg");
            String brandName = brandNameAgg.getBuckets().get(0).getKeyAsString();
            brandVo.setBrandName(brandName);

            // 4.3.得到品牌的图片
            ParsedStringTerms brandImgAgg = bucket.getAggregations().get("brand_img_agg");
            String brandImg = brandImgAgg.getBuckets().get(0).getKeyAsString();
            brandVo.setBrandImg(brandImg);

            brandVos.add(brandVo);
        }
        result.setBrands(brandVos);

        // 5.当前商品涉及到的所有分类信息
        // 获取到分类的聚合
        List<SkuEsSearchResp.CategoryVo> catalogVos = new ArrayList<>();
        ParsedLongTerms catalogAgg = response.getAggregations().get("catalog_agg");
        for (Terms.Bucket bucket : catalogAgg.getBuckets()) {
            SkuEsSearchResp.CategoryVo catalogVo = new SkuEsSearchResp.CategoryVo();
            // 5.1.得到分类id
            String keyAsString = bucket.getKeyAsString();
            catalogVo.setCategoryCode(Long.parseLong(keyAsString));

            // 5.2.得到分类名
            ParsedStringTerms catalogNameAgg = bucket.getAggregations().get("catalog_name_agg");
            String catalogName = catalogNameAgg.getBuckets().get(0).getKeyAsString();
            catalogVo.setCategoryName(catalogName);
            catalogVos.add(catalogVo);
        }
        result.setCategorys(catalogVos);
        //===============以上可以从聚合信息中获取====================//

        // 6.分页信息-页码
        result.setPageNum(req.getPageNum());
        // 6.1.分页信息、总记录数
        long total = hits.getTotalHits().value;
        result.setTotal(total);

        // 6.2.分页信息-总页码-计算
        int totalPages = (int) total % ProductConstant.PRODUCT_PAGE_SIZE == 0 ?
                (int) total / ProductConstant.PRODUCT_PAGE_SIZE : ((int) total / ProductConstant.PRODUCT_PAGE_SIZE + 1);
        result.setTotalPages(totalPages);

        List<Integer> pageNavs = new ArrayList<>();
        for (int i = 1; i <= totalPages; i++) {
            pageNavs.add(i);
        }
        result.setPageNavs(pageNavs);

        return result;
    }

    @Override
    public boolean saveProductToEs(List<SkuEsAddReq> skuEsAddReqs) throws IOException {
        // 1.在ES中建立索引，建立好映射关系（doc/json/product-mapping.json）

        // 2.在ES中保存这些数据
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuEsAddReq skuEsModel : skuEsAddReqs) {
            // 构造保存请求
            IndexRequest indexRequest = new IndexRequest(ProductConstant.PRODUCT_INDEX); // 目标索引
            indexRequest.id(skuEsModel.getSkuCode().toString()); // 索引唯一ID，取SkuCode
            String jsonString = JacksonUtils.toJson(skuEsModel); // 数据内容，对象转换成JSON字符串
            indexRequest.source(jsonString, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }

        // 3.发送批量保存商品信息
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // 4.查看商品是否上架成功
        List<String> collect = Arrays.stream(bulk.getItems())
                .map(BulkItemResponse::getId)
                .collect(Collectors.toList());
        log.info("商品上架完成：{}", collect);

        // 5.如果批量错误（bulk.hasFailures()判断是否有错误）
        boolean hasFailures = bulk.hasFailures();

        // 6.TODO 如果上传错误，需要重新上传等后续处理，ES出现问题，或者传输的格式和ES种不一致

        return hasFailures;
    }


}
