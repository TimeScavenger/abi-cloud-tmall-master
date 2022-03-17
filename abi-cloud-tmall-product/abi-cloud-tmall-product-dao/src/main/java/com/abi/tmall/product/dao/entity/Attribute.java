package com.abi.tmall.product.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品属性
 */
@ApiModel(value = "商品属性")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_attribute")
public class Attribute implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

    /**
     * 属性Code
     */
    @TableField(value = "attribute_code")
    @ApiModelProperty(value = "属性Code")
    private Long attributeCode;

    /**
     * 属性名称
     */
    @TableField(value = "attribute_name")
    @ApiModelProperty(value = "属性名称")
    private String attributeName;

    /**
     * 分类Code
     */
    @TableField(value = "category_code")
    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    /**
     * 属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "属性类型 0-规格参数，1-销售属性，2-既是销售属性又是基本属性")
    private Integer type;

    /**
     * 属性图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "属性图标")
    private String icon;

    /**
     * 属性值类型 0-单选，1-多选
     */
    @TableField(value = "value_type")
    @ApiModelProperty(value = "属性值类型 0-单选，1-多选")
    private Integer valueType;

    /**
     * 属性可选值列表，用逗号分隔
     */
    @TableField(value = "value_list")
    @ApiModelProperty(value = "属性可选值列表，用逗号分隔")
    private String valueList;

    /**
     * 是否需要检索 0-不需要，1-需要
     */
    @TableField(value = "search_type")
    @ApiModelProperty(value = "是否需要检索 0-不需要，1-需要")
    private Integer searchType;

    /**
     * 快速展示【是否展示在介绍上 0-否，1-是】，在sku中仍然可以调整
     */
    @TableField(value = "quick_show")
    @ApiModelProperty(value = "快速展示【是否展示在介绍上 0-否，1-是】，在sku中仍然可以调整")
    private Integer quickShow;

    /**
     * 是否显示 0-不显示，1-显示
     */
    @TableField(value = "showed")
    @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
    private Integer showed;

    /**
     * 启用状态 0-未启用，1-启用
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "启用状态 0-未启用，1-启用")
    private Integer enabled;

    /**
     * 是否删除 0-未删除，1-删除
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "是否删除 0-未删除，1-删除")
    private Integer deleted;

    /**
     * 创建人Code
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人Code")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 最近一次修改人Code
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value = "最近一次修改人Code")
    private String modifyBy;

    /**
     * 最近一次修改时间
     */
    @TableField(value = "modify_time")
    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDateTime modifyTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ATTRIBUTE_CODE = "attribute_code";

    public static final String COL_ATTRIBUTE_NAME = "attribute_name";

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_TYPE = "type";

    public static final String COL_ICON = "icon";

    public static final String COL_VALUE_TYPE = "value_type";

    public static final String COL_VALUE_LIST = "value_list";

    public static final String COL_SEARCH_TYPE = "search_type";

    public static final String COL_QUICK_SHOW = "quick_show";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}