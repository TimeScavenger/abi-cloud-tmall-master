package com.abi.tmall.coupon.common.request.spubounds;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品Spu积分设置
 */
@ApiModel(value = "商品Spu积分设置")
@Data
public class SpuBoundsAddReq {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    @ApiModelProperty(value = "成长积分")
    private BigDecimal growBounds;

    @ApiModelProperty(value = "购物积分")
    private BigDecimal buyBounds;

    @ApiModelProperty(value = "优惠生效情况 1111（四个状态位，从右到左）,0 - 无优惠，成长积分是否赠送；,1 - 无优惠，购物积分是否赠送；,2 - 有优惠，成长积分是否赠送；,3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】")
    private Integer work;

}