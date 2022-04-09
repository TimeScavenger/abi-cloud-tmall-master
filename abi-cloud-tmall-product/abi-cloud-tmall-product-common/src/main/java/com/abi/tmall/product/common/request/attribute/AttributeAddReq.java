package com.abi.tmall.product.common.request.attribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 添加属性
 */
@Data
@ApiModel(description = "添加属性")
public class AttributeAddReq {

    @NotBlank(message = "属性名称不能为空")
    @ApiModelProperty(value = "属性名称", name = "attributeName", example = "基本属性", required = true)
    private String attributeName;

    @NotNull(message = "所属分类的Code不能为空")
    @ApiModelProperty(value = "所属分类的Code", name = "categoryCode", example = "1", required = true)
    private Long categoryCode;

    @ApiModelProperty(value = "属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性", name = "type", example = "1", required = true)
    @NotNull(message = "属性类型不能为空")
    private Integer type;

    @ApiModelProperty(value = "属性图标", name = "icon")
    private String icon;

    @ApiModelProperty(value = "值类型 0-单选，1-多选")
    private Integer valueType;

    @ApiModelProperty(value = "可选值列表 用逗号分隔")
    private String valueList;

    @ApiModelProperty(value = "是否需要检索 0-不需要，1-需要")
    private Integer searchType;

    @ApiModelProperty(value = "快速展示【是否展示在介绍上 0-否，1-是】")
    private Integer quickShow;

    @ApiModelProperty(value = "启用状态 0-未启用，1-启用")
    private Integer enabled;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "分类path路径")
    private Long[] categoryPath;

}
