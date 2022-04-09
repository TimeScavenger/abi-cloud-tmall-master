package com.abi.tmall.ware.common.request.waresku;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品库存
 */
@Data
@ApiModel(value = "商品库存")
public class WareSkuRelationPageReq extends PageRequest {

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "sku名字")
    private String skuName;

}