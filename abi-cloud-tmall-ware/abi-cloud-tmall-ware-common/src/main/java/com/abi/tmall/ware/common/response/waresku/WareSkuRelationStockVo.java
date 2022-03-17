package com.abi.tmall.ware.common.response.waresku;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品库存
 */
@ApiModel(value = "商品库存")
@Data
public class WareSkuRelationStockVo implements Serializable {

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "库存数")
    private Boolean hasStock;

}