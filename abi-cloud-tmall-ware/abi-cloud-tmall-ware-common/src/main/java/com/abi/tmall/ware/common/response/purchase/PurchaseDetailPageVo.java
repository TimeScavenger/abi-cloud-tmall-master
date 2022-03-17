package com.abi.tmall.ware.common.response.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 采购单明细信息
 */
@ApiModel(value = "采购单明细信息")
@Data
public class PurchaseDetailPageVo implements Serializable {

    @ApiModelProperty(value = "采购项Code")
    private Long purchaseDetailCode;

    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    @ApiModelProperty(value = "采购商品Code")
    private Long skuCode;

    @ApiModelProperty(value = "采购商品数量")
    private Integer skuNum;

    @ApiModelProperty(value = "采购商品金额")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败")
    private Integer status;

}