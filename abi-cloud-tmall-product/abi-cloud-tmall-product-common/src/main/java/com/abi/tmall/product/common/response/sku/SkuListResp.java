package com.abi.tmall.product.common.response.sku;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询Sku列表
 */
@Data
@Api("查询Sku列表")
public class SkuListResp implements Serializable {

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "商品名称")
    private String skuName;

}