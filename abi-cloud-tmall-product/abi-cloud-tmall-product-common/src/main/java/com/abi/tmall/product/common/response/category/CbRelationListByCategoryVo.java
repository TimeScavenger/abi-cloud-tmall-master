package com.abi.tmall.product.common.response.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 根据分类查询分类品牌关联关系
 */
@Data
@Api("根据分类查询分类品牌关联关系")
public class CbRelationListByCategoryVo {

    @ApiModelProperty(value = "主键ID")
    private Long id;

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "品牌名字")
    private String brandName;

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

}
