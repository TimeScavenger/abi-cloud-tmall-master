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
public class WareSkuRelationStockResp implements Serializable {

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "库存数")
    private Boolean hasStock;

}