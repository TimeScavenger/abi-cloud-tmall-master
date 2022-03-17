package com.abi.tmall.product.common.request.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 删除分组属性关联关系
 */
@Data
@ApiModel(description = "删除分组属性关联关系")
public class GaRelationDelDto {

    @ApiModelProperty(value = "分组Code")
    @NotNull(message = "分组Code不能为空")
    private Long groupCode;

    @ApiModelProperty(value = "属性Code")
    @NotNull(message = "属性Code不能为空")
    private Long attributeCode;

}
