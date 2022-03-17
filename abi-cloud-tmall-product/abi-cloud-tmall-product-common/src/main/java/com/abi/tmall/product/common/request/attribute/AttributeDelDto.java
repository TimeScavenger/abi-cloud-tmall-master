package com.abi.tmall.product.common.request.attribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 删除属性
 */
@Data
@ApiModel(description = "删除属性")
public class AttributeDelDto {

    @ApiModelProperty(value = "属性Code集合", name = "attributeCodes", example = "[1,2,3]", required = true)
    @NotEmpty(message = "属性Code集合不能为空")
    private List<Long> attributeCodes;

}
