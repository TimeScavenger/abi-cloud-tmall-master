package com.abi.tmall.ware.common.response.ware.sku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品库存
 */
@Data
@ApiModel(value = "商品库存")
public class WareSkuRelationPageResp implements Serializable {

    @ApiModelProperty(value = "自增Code")
    private Long id;

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "仓库名")
    private String wareName;

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "sku名字")
    private String skuName;

    @ApiModelProperty(value = "库存数")
    private Integer stock;

    @ApiModelProperty(value = "锁定库存")
    private Integer stockLocked;

}