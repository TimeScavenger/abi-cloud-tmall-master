package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 根据品牌查询分类品牌关联关系
 */
@Data
@Api("根据品牌查询分类品牌关联关系")
public class CbRelationListByBrandDto {

    @ApiModelProperty(value = "品牌Code", name = "brandCode", example = "1", required = true)
    @NotNull(message = "品牌Code不能为空")
    private Long brandCode;

}
