package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 根据分类查询分类品牌关联关系
 */
@Data
@Api("根据分类查询分类品牌关联关系")
public class CbRelationListByCategoryReq {

    @NotNull(message = "分类Code不能为空")
    @ApiModelProperty(value = "分类Code", name = "categoryCode", example = "1", required = true)
    private Long categoryCode;

}
