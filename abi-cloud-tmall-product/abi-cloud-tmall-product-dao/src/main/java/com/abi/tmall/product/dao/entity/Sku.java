package com.abi.tmall.product.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Sku
 */
@ApiModel(value = "Sku")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_sku")
public class Sku implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

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

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}