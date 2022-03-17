package com.abi.tmall.product.common.response.group;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询分组信息
 */
@Data
@Api("查询分组信息")
public class GroupInfoVo {

    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "所属分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String desc;

    @ApiModelProperty(value = "分组图标")
    private String icon;

    @ApiModelProperty(value = "分类path路径")
    private Long[] categoryPath;

}
