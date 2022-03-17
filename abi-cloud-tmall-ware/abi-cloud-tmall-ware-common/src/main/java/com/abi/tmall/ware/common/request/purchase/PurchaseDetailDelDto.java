package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 移除采购项
 */
@ApiModel(value = "移除采购项")
@Data
public class PurchaseDetailDelDto {

    @NotEmpty(message = "采购项Code不能为空")
    @ApiModelProperty(value = "采购项Code列表")
    private List<Long> purchaseDetailCodes;

}