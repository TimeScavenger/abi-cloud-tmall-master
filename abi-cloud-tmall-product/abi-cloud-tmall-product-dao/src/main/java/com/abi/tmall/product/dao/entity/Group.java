package com.abi.tmall.product.dao.entity;

import com.abi.infrastructure.dao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品属性分组
 */
@ApiModel(value = "商品属性分组")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_group")
public class Group extends BaseEntity implements Serializable {

    /**
     * 分组Code
     */
    @TableField(value = "group_code")
    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    /**
     * 分组名称
     */
    @TableField(value = "group_name")
    @ApiModelProperty(value = "分组名称")
    private String groupName;

    /**
     * 分类Code
     */
    @TableField(value = "category_code")
    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    /**
     * 分组排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "分组排序")
    private Integer sort;

    /**
     * 描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value = "描述")
    private String desc;

    /**
     * 图标
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "图标")
    private String icon;

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

    private static final long serialVersionUID = 1L;

    public static final String COL_GROUP_CODE = "group_code";

    public static final String COL_GROUP_NAME = "group_name";

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_SORT = "sort";

    public static final String COL_DESC = "desc";

    public static final String COL_ICON = "icon";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

}