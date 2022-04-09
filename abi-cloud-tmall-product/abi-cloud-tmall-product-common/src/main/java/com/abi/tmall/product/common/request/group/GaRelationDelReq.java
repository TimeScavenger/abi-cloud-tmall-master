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
public class GaRelationDelReq {

    @NotNull(message = "分组Code不能为空")
    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    @NotNull(message = "属性Code不能为空")
    @ApiModelProperty(value = "属性Code")
    private Long attributeCode;

}
