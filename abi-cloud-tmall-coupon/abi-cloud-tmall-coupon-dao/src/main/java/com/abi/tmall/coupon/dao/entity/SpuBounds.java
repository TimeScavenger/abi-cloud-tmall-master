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
 * 商品Spu积分设置
 */
@ApiModel(value = "商品Spu积分设置")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sms_spu_bounds")
public class SpuBounds extends BaseEntity implements Serializable {

    /**
     * spuCode
     */
    @TableField(value = "spu_code")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    /**
     * 成长积分
     */
    @TableField(value = "grow_bounds")
    @ApiModelProperty(value = "成长积分")
    private BigDecimal growBounds;

    /**
     * 购物积分
     */
    @TableField(value = "buy_bounds")
    @ApiModelProperty(value = "购物积分")
    private BigDecimal buyBounds;

    /**
     * 优惠生效情况 1111（四个状态位，从右到左）
     * 0 - 无优惠，成长积分是否赠送；
     * 1 - 无优惠，购物积分是否赠送；
     * 2 - 有优惠，成长积分是否赠送；
     * 3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】
     */
    @TableField(value = "`work`")
    @ApiModelProperty(value = "优惠生效情况 1111（四个状态位，从右到左）,0 - 无优惠，成长积分是否赠送；,1 - 无优惠，购物积分是否赠送；,2 - 有优惠，成长积分是否赠送；,3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】")
    private Integer work;

    private static final long serialVersionUID = 1L;

    public static final String COL_SPU_ID = "spu_code";

    public static final String COL_GROW_BOUNDS = "grow_bounds";

    public static final String COL_BUY_BOUNDS = "buy_bounds";

    public static final String COL_WORK = "work";
}