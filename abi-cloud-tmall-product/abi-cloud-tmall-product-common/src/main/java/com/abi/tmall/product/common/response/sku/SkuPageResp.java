package com.abi.tmall.product.common.response.sku;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 查询Sku分页
 */
@Data
@Api("查询Sku分页")
public class SkuPageResp implements Serializable {

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

    @ApiModelProperty(value = "默认图片")
    private String skuDefaultImg;

    @ApiModelProperty(value = "价格")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "销量")
    private Long skuSaleCount;

    @ApiModelProperty(value = "sku标题")
    private String skuTitle;

    @ApiModelProperty(value = "sku副标题")
    private String skuSubTitle;

    @ApiModelProperty(value = "sku描述")
    private String skuDesc;

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

}