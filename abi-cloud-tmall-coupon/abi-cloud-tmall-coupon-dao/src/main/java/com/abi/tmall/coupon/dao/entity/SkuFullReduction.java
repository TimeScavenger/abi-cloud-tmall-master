package com.abi.tmall.coupon.dao.entity;

import com.abi.infrastructure.dao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * 商品满减信息
 */
@ApiModel(value = "商品满减信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sms_sku_full_reduction")
public class SkuFullReduction extends BaseEntity implements Serializable {

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * 满多少
     */
    @TableField(value = "full_price")
    @ApiModelProperty(value = "满多少")
    private BigDecimal fullPrice;

    /**
     * 减多少
     */
    @TableField(value = "reduce_price")
    @ApiModelProperty(value = "减多少")
    private BigDecimal reducePrice;

    /**
     * 是否参与其他优惠
     */
    @TableField(value = "add_other")
    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer addOther;

    private static final long serialVersionUID = 1L;

    public static final String COL_SKU_ID = "sku_code";

    public static final String COL_FULL_PRICE = "full_price";

    public static final String COL_REDUCE_PRICE = "reduce_price";

    public static final String COL_ADD_OTHER = "add_other";
}