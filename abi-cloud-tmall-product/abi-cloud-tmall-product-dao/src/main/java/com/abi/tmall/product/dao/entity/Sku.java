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
import java.math.BigDecimal;

/**
 * Sku
 */
@ApiModel(value = "Sku")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_sku")
public class Sku extends BaseEntity implements Serializable {

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * sku名称
     */
    @TableField(value = "sku_name")
    @ApiModelProperty(value = "sku名称")
    private String skuName;

    /**
     * sku描述
     */
    @TableField(value = "sku_desc")
    @ApiModelProperty(value = "sku描述")
    private String skuDesc;

    /**
     * 分类Code
     */
    @TableField(value = "category_code")
    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    /**
     * 品牌Code
     */
    @TableField(value = "brand_code")
    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    /**
     * spuCode
     */
    @TableField(value = "spu_code")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    /**
     * sku默认图片
     */
    @TableField(value = "sku_default_img")
    @ApiModelProperty(value = "sku默认图片")
    private String skuDefaultImg;

    /**
     * sku标题
     */
    @TableField(value = "sku_title")
    @ApiModelProperty(value = "sku标题")
    private String skuTitle;

    /**
     * sku副标题
     */
    @TableField(value = "sku_sub_title")
    @ApiModelProperty(value = "sku副标题")
    private String skuSubTitle;

    /**
     * sku价格
     */
    @TableField(value = "sku_price")
    @ApiModelProperty(value = "sku价格")
    private BigDecimal skuPrice;

    /**
     * sku销量
     */
    @TableField(value = "sku_sale_count")
    @ApiModelProperty(value = "sku销量")
    private Long skuSaleCount;

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

    public static final String COL_SKU_NAME = "sku_name";

    public static final String COL_SKU_DESC = "sku_desc";

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_BRAND_CODE = "brand_code";

    public static final String COL_SPU_CODE = "spu_code";

    public static final String COL_SKU_DEFAULT_IMG = "sku_default_img";

    public static final String COL_SKU_TITLE = "sku_title";

    public static final String COL_SKU_SUB_TITLE = "sku_sub_title";

    public static final String COL_SKU_PRICE = "sku_price";

    public static final String COL_SKU_SALE_COUNT = "sku_sale_count";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

}