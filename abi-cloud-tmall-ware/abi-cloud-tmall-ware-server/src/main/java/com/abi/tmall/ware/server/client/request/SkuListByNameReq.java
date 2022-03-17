package com.abi.tmall.ware.server.client.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询Sku列表
 */
@Data
@ApiModel("查询Sku列表")
public class SkuListByNameReq {

    @ApiModelProperty(value = "skuName")
    private String skuName;

}