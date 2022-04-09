package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 查询分类树结构
 */
@Data
@Api("查询分类树结构")
public class CategoryTreeReq {

    @Max(value = 4, message = "分类层级不能超过4级")
    @Min(value = 1, message = "分类层级参数异常")
    @NotNull(message = "分类层级不能为空")
    @ApiModelProperty(value = "层级 1-4", name = "level", example = "1", required = true)
    private Integer level;

}
