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
 * Sku销售属性-属性值
 */
@ApiModel(value = "Sku销售属性-属性值")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_sku_sale_attribute_value")
public class SkuSaleAttributeValue extends BaseEntity implements Serializable {

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * 销售属性Code
     */
    @TableField(value = "attribute_code")
    @ApiModelProperty(value = "销售属性Code")
    private Long attributeCode;

    /**
     * 销售属性名
     */
    @TableField(value = "attribute_name")
    @ApiModelProperty(value = "销售属性名")
    private String attributeName;

    /**
     * 销售属性值
     */
    @TableField(value = "attribute_value")
    @ApiModelProperty(value = "销售属性值")
    private String attributeValue;

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

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_ATTRIBUTE_CODE = "attribute_code";

    public static final String COL_ATTRIBUTE_NAME = "attribute_name";

    public static final String COL_ATTRIBUTE_VALUE = "attribute_value";

    public static final String COL_SORT = "sort";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

}