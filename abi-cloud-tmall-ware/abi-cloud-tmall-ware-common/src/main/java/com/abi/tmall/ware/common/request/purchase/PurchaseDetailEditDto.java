package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 编辑采购项
 */
@ApiModel(value = "编辑采购项")
@Data
public class PurchaseDetailEditDto implements Serializable {

    @NotNull(message = "采购项Code不能为空")
    @ApiModelProperty(value = "采购项Code")
    private Long purchaseDetailCode;

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