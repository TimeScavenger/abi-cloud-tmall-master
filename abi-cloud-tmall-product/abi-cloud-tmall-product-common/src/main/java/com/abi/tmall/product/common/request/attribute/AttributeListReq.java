package com.abi.tmall.product.common.request.attribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 查询属性列表
 */
@Data
@ApiModel(description = "查询属性列表")
public class AttributeListReq {

    @NotNull(message = "属性类型不能为空")
    @ApiModelProperty(value = "属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性", name = "type", example = "1")
    private Integer type;

    @NotNull(message = "分类ID不能为空")
    @ApiModelProperty(value = "分类Code", name = "categoryCode", example = "1")
    private Long categoryCode;

}
