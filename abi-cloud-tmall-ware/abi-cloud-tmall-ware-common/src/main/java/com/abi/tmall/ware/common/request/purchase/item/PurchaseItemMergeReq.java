package com.abi.tmall.ware.common.request.purchase.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 合并采购项
 */
@Data
@ApiModel(value = "合并采购项")
public class PurchaseItemMergeReq implements Serializable {

    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    @NotEmpty(message = "采购项Code集合不能为空")
    @ApiModelProperty(value = "采购项Code集合")
    private List<Long> purchaseDetailCodes;

}