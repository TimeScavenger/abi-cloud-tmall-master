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
 * Spu规格参数-属性值
 */
@ApiModel(value = "Spu规格参数-属性值")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_spu_base_attribute_value")
public class SpuBaseAttributeValue  extends BaseEntity implements Serializable {

    /**
     * spuCode
     */
    @TableField(value = "spu_code")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    /**
     * 规格参数属性Code
     */
    @TableField(value = "attribute_code")
    @ApiModelProperty(value = "规格参数属性Code")
    private Long attributeCode;

    /**
     * 规格参数属性名
     */
    @TableField(value = "attribute_name")
    @ApiModelProperty(value = "规格参数属性名")
    private String attributeName;

    /**
     * 规格参数属性值
     */
    @TableField(value = "attribute_value")
    @ApiModelProperty(value = "规格参数属性值")
    private String attributeValue;

    /**
     * 快速展示【是否展示在介绍上 0-否，1-是】，在sku中仍然可以调整
     */
    @TableField(value = "quick_show")
    @ApiModelProperty(value = "快速展示【是否展示在介绍上 0-否，1-是】，在sku中仍然可以调整")
    private Integer quickShow;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

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

    public static final String COL_SPU_CODE = "spu_code";

    public static final String COL_ATTRIBUTE_CODE = "attribute_code";

    public static final String COL_ATTRIBUTE_NAME = "attribute_name";

    public static final String COL_ATTRIBUTE_VALUE = "attribute_value";

    public static final String COL_QUICK_SHOW = "quick_show";

    public static final String COL_SORT = "sort";

    public static final String COL_SHOWED = "showed";

}