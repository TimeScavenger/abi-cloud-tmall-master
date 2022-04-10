package com.abi.tmall.coupon.dao.entity;

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
 * 商品折扣信息
 */
@ApiModel(value = "商品折扣信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sms_sku_ladder")
public class SkuLadder extends BaseEntity implements Serializable {

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * 满几件
     */
    @TableField(value = "full_count")
    @ApiModelProperty(value = "满几件")
    private String fullCount;

    /**
     * 打几折
     */
    @TableField(value = "discount")
    @ApiModelProperty(value = "打几折")
    private String discount;

    /**
     * 折后价
     */
    @TableField(value = "price")
    @ApiModelProperty(value = "折后价")
    private BigDecimal price;

    /**
     * 是否参与其他优惠 0-不可叠加优惠，1-可叠加
     */
    @TableField(value = "add_other")
    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer addOther;

    private static final long serialVersionUID = 1L;

    public static final String COL_SKU_ID = "sku_code";

    public static final String COL_FULL_COUNT = "full_count";

    public static final String COL_DISCOUNT = "discount";

    public static final String COL_PRICE = "price";

    public static final String COL_ADD_OTHER = "add_other";
}