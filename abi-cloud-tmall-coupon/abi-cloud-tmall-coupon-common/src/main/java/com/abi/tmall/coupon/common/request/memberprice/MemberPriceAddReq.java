package com.abi.tmall.coupon.common.request.memberprice;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品会员价格
 */
@ApiModel(value = "商品会员价格")
@Data
public class MemberPriceAddReq implements Serializable {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    @ApiModelProperty(value = "会员等级Code")
    private Long memberLevelCode;

    @ApiModelProperty(value = "会员等级名")
    private String memberLevelName;

    @ApiModelProperty(value = "会员对应价格")
    private BigDecimal memberPrice;

    @ApiModelProperty(value = "是否参与其他优惠 0-不可叠加优惠，1-可叠加")
    private Integer addOther;

}