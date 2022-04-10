package com.abi.tmall.coupon.common.request.skufullreduction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品满减信息
 */
@ApiModel(value = "商品满减信息")
@Data
public class SkuFullReductionAddReq implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "满多少")
    private BigDecimal fullPrice;

    @ApiModelProperty(value = "减多少")
    private BigDecimal reducePrice;

    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer addOther;

}