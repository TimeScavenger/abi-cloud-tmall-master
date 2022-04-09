package com.abi.tmall.ware.common.request.purchasedetail;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查询采购项
 */
@Data
@ApiModel(value = "查询采购项")
public class PurchaseDetailInfoReq implements Serializable {

    @NotNull(message = "采购项Code不能为空")
    @ApiModelProperty(value = "采购项Code")
    private Long purchaseDetailCode;

}