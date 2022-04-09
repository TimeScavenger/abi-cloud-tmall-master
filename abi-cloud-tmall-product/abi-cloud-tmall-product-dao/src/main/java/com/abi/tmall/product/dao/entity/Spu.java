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
 * Spu
 */
@ApiModel(value = "Spu")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_spu")
public class Spu  extends BaseEntity implements Serializable {

    /**
     * spuCode
     */
    @TableField(value = "spu_code")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    /**
     * spu名称
     */
    @TableField(value = "spu_name")
    @ApiModelProperty(value = "spu名称")
    private String spuName;

    /**
     * spu描述
     */
    @TableField(value = "spu_desc")
    @ApiModelProperty(value = "spu描述")
    private String spuDesc;

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
     * 商品重量
     */
    @TableField(value = "weight")
    @ApiModelProperty(value = "商品重量")
    private BigDecimal weight;

    /**
     * 上架状态 0-新建，1-上架，2-下架
     */
    @TableField(value = "publish_status")
    @ApiModelProperty(value = "上架状态 0-新建，1-上架，2-下架")
    private Integer publishStatus;

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

    public static final String COL_SPU_NAME = "spu_name";

    public static final String COL_SPU_DESC = "spu_desc";

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_BRAND_CODE = "brand_code";

    public static final String COL_WEIGHT = "weight";

    public static final String COL_PUBLISH_STATUS = "publish_status";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

}