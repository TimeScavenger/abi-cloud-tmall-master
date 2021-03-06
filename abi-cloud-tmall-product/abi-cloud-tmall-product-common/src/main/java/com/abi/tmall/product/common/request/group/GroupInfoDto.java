package com.abi.tmall.product.common.request.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 查看分组
 */
@Data
@Api("查看分组")
public class GroupInfoDto {

    @ApiModelProperty(value = "分组Code")
    @NotNull(message = "分组Code不能为空")
    private Long groupCode;

}
