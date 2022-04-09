package com.abi.tmall.product.common.response.attribute;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询属性信息
 */
@Data
@Api("查询属性信息")
public class AttributeInfoResp {

    // 解决后端传输给前端Long类型精度丢失的问题
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "属性Code")
    private Long attributeCode;

    @ApiModelProperty(value = "属性名称")
    private String attributeName;

    @ApiModelProperty(value = "属性类型 0-销售属性，1-基本属性，2-既是销售属性又是基本属性")
    private Integer type;

    @ApiModelProperty(value = "属性图标")
    private String icon;

    @ApiModelProperty(value = "值类型 0-单选，1-多选")
    private Integer valueType;

    @ApiModelProperty(value = "是否需要检索 0-不需要，1-需要")
    private Integer searchType;

    @ApiModelProperty(value = "可选值列表 用逗号分隔")
    private String valueList;

    @ApiModelProperty(value = "所属分类的Code")
    private Long categoryCode;

    @ApiModelProperty(value = "快速展示【是否展示在介绍上 0-否，1-是】")
    private Integer quickShow;

    @ApiModelProperty(value = "启用状态 0-未启用，1-启用")
    private Integer enabled;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    // 解决后端传输给前端Long类型精度丢失的问题
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "分类path路径")
    private Long[] categoryPath;
}
