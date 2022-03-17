package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 新增分类品牌关联关系
 */
@Data
@Api("新增分类品牌关联关系")
public class CbRelationAddDto {

    @ApiModelProperty(value = "分类Code", name = "categoryCode", example = "1", required = true)
    @NotNull(message = "分类Code不能为空")
    private Long categoryCode;

    @ApiModelProperty(value = "分类名称", name = "categoryName", example = "电脑", required = true)
    private String categoryName;

    @ApiModelProperty(value = "品牌Code", name = "brandCode", example = "1", required = true)
    @NotNull(message = "品牌Code不能为空")
    private Long brandCode;

    @ApiModelProperty(value = "品牌名称", name = "brandName", example = "小米", required = true)
    private String brandName;

}
