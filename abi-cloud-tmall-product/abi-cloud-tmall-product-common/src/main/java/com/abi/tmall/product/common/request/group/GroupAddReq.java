package com.abi.tmall.product.common.request.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增分组
 */
@Data
@Api("新增分组")
public class GroupAddReq {

    @NotBlank(message = "分组名称不能为空")
    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @NotNull(message = "所属分类Code不能为空")
    @ApiModelProperty(value = "所属分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "分组图标")
    private String icon;

}
