package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 编辑分类排序
 */
@Data
@Api("编辑分类排序")
public class CategorySortReq {

    @NotNull(message = "分类Code不能为空")
    @ApiModelProperty(value = "分类Code", name = "categoryCode", example = "1", required = true)
    private Long categoryCode;

    @NotNull(message = "父分类Code不能为空")
    @ApiModelProperty(value = "父分类Code", name = "parentCode", example = "1", required = true)
    private Long parentCode;

    @Max(value = 4, message = "分类层级不能超过4级")
    @Min(value = 1, message = "分类层级参数异常")
    @NotNull(message = "分类层级不能为空")
    @ApiModelProperty(value = "层级 1-4", name = "level", example = "1", required = true)
    private Integer level;

}
