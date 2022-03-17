package com.abi.tmall.product.common.response.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询分类信息
 */
@Data
@Api("查询分类信息")
public class CategoryInfoVo {

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

    @ApiModelProperty(value = "图标地址")
    private String icon;

    @ApiModelProperty(value = "计量单位")
    private String productUnit;

    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

}
