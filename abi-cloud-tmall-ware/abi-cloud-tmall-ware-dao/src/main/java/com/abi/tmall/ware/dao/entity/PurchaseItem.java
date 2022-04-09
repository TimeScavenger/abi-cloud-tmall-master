package com.abi.tmall.ware.dao.entity;

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
 * 采购项
 */
@ApiModel(value = "采购项")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_purchase_detail")
public class PurchaseItem extends BaseEntity implements Serializable {

    /**
     * 采购项Code
     */
    @TableField(value = "purchase_detail_code")
    @ApiModelProperty(value = "采购项Code")
    private Long purchaseDetailCode;

    /**
     * 采购单Code
     */
    @TableField(value = "purchase_code")
    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    /**
     * 采购商品Code
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "采购商品Code")
    private Long skuCode;

    /**
     * 采购商品数量
     */
    @TableField(value = "sku_num")
    @ApiModelProperty(value = "采购商品数量")
    private Integer skuNum;

    /**
     * 采购商品金额
     */
    @TableField(value = "sku_price")
    @ApiModelProperty(value = "采购商品金额")
    private BigDecimal skuPrice;

    /**
     * 仓库Code
     */
    @TableField(value = "ware_code")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    /**
     * 采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败")
    private Integer status;

    /**
     * 原因
     */
    @TableField(value = "reason")
    @ApiModelProperty(value = "原因")
    private String reason;

    private static final long serialVersionUID = 1L;

    public static final String COL_PURCHASE_DETAIL_CODE = "purchase_detail_code";

    public static final String COL_PURCHASE_CODE = "purchase_code";

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_SKU_NUM = "sku_num";

    public static final String COL_SKU_PRICE = "sku_price";

    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_STATUS = "status";

    public static final String COL_REASON = "reason";

}