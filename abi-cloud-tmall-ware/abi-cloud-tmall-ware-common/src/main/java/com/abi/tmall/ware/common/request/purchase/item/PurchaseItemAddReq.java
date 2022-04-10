package com.abi.tmall.ware.common.request.purchase.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 新增采购项
 */
@Data
@ApiModel(value = "新增采购项")
public class PurchaseItemAddReq implements Serializable {

    @NotNull(message = "采购商品Code不能为空")
    @ApiModelProperty(value = "采购商品Code")
    private Long skuCode;

    @NotNull(message = "采购商品数量不能为空")
    @ApiModelProperty(value = "采购商品数量")
    private Integer skuNum;

    @NotNull(message = "仓库Code不能为空")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

}