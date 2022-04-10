package com.abi.tmall.ware.common.request.purchase.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 移除采购项
 */
@Data
@ApiModel(value = "移除采购项")
public class PurchaseItemDelReq {

    @NotEmpty(message = "采购项Code不能为空")
    @ApiModelProperty(value = "采购项Code列表")
    private List<Long> purchaseDetailCodes;

}