package com.abi.tmall.product.common.request.attribute;

import com.abi.infrastructure.core.verified.annotation.EnumValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 查询属性列表
 */
@Data
@ApiModel(description = "查询属性列表")
public class AttributeListDto {

    @ApiModelProperty(value = "属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性", name = "type", example = "1")
    @EnumValue(intValues = {0, 1, 2})
    @NotNull(message = "属性类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "分类Code", name = "categoryCode", example = "1")
    @NotNull(message = "分类ID不能为空")
    private Long categoryCode;

}
