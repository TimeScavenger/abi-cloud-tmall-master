package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 完成采购单
 */
@Data
@ApiModel(value = "完成采购单")
public class PurchaseDoneReq implements Serializable {

    @NotNull(message = "采购单Code不能为空")
    @ApiModelProperty(value = "采购单COde")
    private Long purchaseCode;

    @NotEmpty(message = "采购项列表不能为空")
    @ApiModelProperty(value = "采购项列表")
    List<PurchaseDetailDoneReq> purchaseDetailDoneReqList;

    @Data
    @ApiModel(value = "采购项对象", description = "采购项对象")
    public static class PurchaseDetailDoneReq implements Serializable {
        @NotEmpty(message = "采购项状态不能为空")
        @ApiModelProperty(value = "采购项Code")
        private Long purchaseDetailCode;

        @NotNull(message = "采购项状态不能为空")
        @ApiModelProperty(value = "采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败")
        private Integer status;

        @ApiModelProperty(value = "原因")
        private String reason;
    }

}