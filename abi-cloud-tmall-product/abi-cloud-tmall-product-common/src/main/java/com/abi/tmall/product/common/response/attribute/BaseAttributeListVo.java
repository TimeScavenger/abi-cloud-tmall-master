package com.abi.tmall.product.common.response.attribute;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询规格参数列表
 */
@Data
@Api("查询规格参数列表")
public class BaseAttributeListVo {

    // 解决后端传输给前端Long类型精度丢失的问题
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "分组图标")
    private String icon;

    // 解决后端传输给前端Long类型精度丢失的问题
    @JsonSerialize(using= ToStringSerializer.class)
    @ApiModelProperty(value = "所属分类的Code")
    private Long categoryCode;

    @ApiModelProperty(value = "保存整个分组关联属性实体信息")
    private List<AttributeInfo> attributeList;

    @Data
    @ApiModel(value = "属性对象", description = "属性对象")
    public static class AttributeInfo implements Serializable {

        @ApiModelProperty(value = "属性Code")
        private Long attributeCode;

        @ApiModelProperty(value = "属性名称")
        private String attributeName;

        @ApiModelProperty(value = "所属分类的Code")
        private Long categoryCode;

        @ApiModelProperty(value = "属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性")
        private Integer type;

        @ApiModelProperty(value = "属性图标")
        private String icon;

        @ApiModelProperty(value = "值类型 0-单选，1-多选")
        private Integer valueType;

        @ApiModelProperty(value = "可选值列表 用逗号分隔")
        private String valueList;

        @ApiModelProperty(value = "是否需要检索 0-不需要，1-需要")
        private Integer searchType;

        @ApiModelProperty(value = "快速展示【是否展示在介绍上 0-否，1-是】")
        private Integer quickShow;

        @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
        private Integer showed;

        @ApiModelProperty(value = "启用状态 0-未启用，1-启用")
        private Integer enabled;

    }
}
