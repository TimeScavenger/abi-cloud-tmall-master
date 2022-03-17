package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 删除分类
 */
@Data
@Api("删除分类")
public class CategoryDelDto {

    @ApiModelProperty(value = "分类Code集合", name = "categoryCodes", example = "[1,2,3]", required = true)
    @NotEmpty(message = "分类Code集合不能为空")
    private List<Long> categoryCodes;

}
