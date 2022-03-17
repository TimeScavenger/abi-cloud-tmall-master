package com.abi.tmall.ware.common.request.waresku;

import com.abi.base.foundation.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品库存
 */
@ApiModel(value = "商品库存")
@Data
public class WareSkuRelationPageDto extends PageRequest {

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "sku名字")
    private String skuName;

}