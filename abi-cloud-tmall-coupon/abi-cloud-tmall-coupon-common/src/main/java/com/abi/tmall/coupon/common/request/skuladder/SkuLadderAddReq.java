package com.abi.tmall.coupon.common.request.skuladder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品阶梯价格
 */
@ApiModel(value = "商品阶梯价格")
@Data
public class SkuLadderAddReq implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "spu_id")
    private Long skuCode;

    @ApiModelProperty(value = "满几件")
    private String fullCount;

    @ApiModelProperty(value = "打几折")
    private String discount;

    @ApiModelProperty(value = "折后价")
    private BigDecimal price;

    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer addOther;

}