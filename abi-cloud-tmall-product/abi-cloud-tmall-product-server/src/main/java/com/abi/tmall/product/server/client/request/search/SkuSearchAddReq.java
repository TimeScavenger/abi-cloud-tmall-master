package com.abi.tmall.product.server.client.request.search;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 传输对象，存储到es的数据
 *
 * @author: zhangshuaiyin
 * @date: 2021/3/12 15:37
 */
@Data
public class SkuSearchAddReq {

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "sku标题")
    private String skuTitle;

    @ApiModelProperty(value = "sku价格")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "sku默认图片")
    private String skuDefaultImg;

    @ApiModelProperty(value = "sku销量")
    private Long skuSaleCount;

    @ApiModelProperty(value = "是否有库存")
    private Boolean hasStock;

    @ApiModelProperty(value = "热度")
    private Long hotScore;

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "品牌名字")
    private String brandName;

    @ApiModelProperty(value = "品牌logo")
    private String logo;

    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    @ApiModelProperty(value = "属性列表")
    private List<Attribute> attributes;

    @Data
    public static class Attribute {
        private Long attrCode;
        private String attrName;
        private String attrValue;
    }
}