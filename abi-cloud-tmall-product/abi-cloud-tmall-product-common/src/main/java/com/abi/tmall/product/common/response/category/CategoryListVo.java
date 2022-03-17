package com.abi.tmall.product.common.response.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询分类列表
 */
@Data
@Api("查询分类列表")
public class CategoryListVo {

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "父分类Code")
    private Long parentCode;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
