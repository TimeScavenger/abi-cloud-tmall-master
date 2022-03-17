package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 查看采购单
 */
@ApiModel(value = "查看采购单")
@Data
public class PurchaseInfoDto implements Serializable {

    @NotNull(message = "采购单Code不能为空")
    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

}