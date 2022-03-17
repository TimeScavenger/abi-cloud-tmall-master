package com.abi.tmall.search.common.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 搜索ES中Sku的数据
 */
@Data
public class SkuEsSearchReq {

    @ApiModelProperty(value = "页面传递过来的全文匹配关键字（全文检索）")
    private String skuTitle;

    @ApiModelProperty(value = "三级分类id")
    private Long categoryId;

    /**
     * 排序
     * sort=saleCount_asc/desc
     * sort=hasStock_asc/desc
     * sort=skuPrice_asc/desc
     */
    @ApiModelProperty(value = "销售数量（销量）")
    private String sort;

    /**
     * 过滤
     * hasStock=0/1
     * skuPrice=1_500/_500/500_
     * brandId=1
     * attrs=2.5寸:6寸
     */
    @ApiModelProperty(value = "是否显示有货")
    private Integer hasStock;

    @ApiModelProperty(value = "价格区间查询")
    private String skuPrice;

    @ApiModelProperty(value = "品牌id,可以多选")
    private List<Long> brandId;

    @ApiModelProperty(value = "按照属性进行筛选")
    private List<String> attrs;

    @ApiModelProperty(value = "页码")
    private Integer pageNum = 1;

    @ApiModelProperty(value = "原生的所有查询条件")
    private String queryString;

}
