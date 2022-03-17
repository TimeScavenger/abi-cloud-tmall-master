package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * 领取采购单
 */
@ApiModel(value = "领取采购单")
@Data
public class PurchaseReceiveDto implements Serializable {

    @NotEmpty(message = "采购单Code列表不能为空")
    @ApiModelProperty(value = "采购单Code列表")
    private List<Long> purchaseCodes;

}