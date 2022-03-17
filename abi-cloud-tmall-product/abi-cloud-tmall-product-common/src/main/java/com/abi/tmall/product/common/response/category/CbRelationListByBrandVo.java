package com.abi.tmall.product.common.response.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 根据品牌查询分类品牌关联关系
 */
@Data
@Api("根据品牌查询分类品牌关联关系")
public class CbRelationListByBrandVo {

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

}
