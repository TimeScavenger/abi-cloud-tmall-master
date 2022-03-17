package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 编辑采购单
 */
@ApiModel(value = "编辑采购单")
@Data
public class PurchaseEditDto implements Serializable {

    @NotNull(message = "采购单Code不能为空")
    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    @NotBlank(message = "采购单名不能为空")
    @ApiModelProperty(value = "采购单名")
    private String purchaseName;

    @NotNull(message = "优先级不能为空")
    @ApiModelProperty(value = "优先级 1-5的优先级")
    private Integer priority;

    @NotNull(message = "仓库Code不能为空")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "采购人Code")
    private Long assigneeCode;

    @ApiModelProperty(value = "采购人姓名")
    private String assigneeName;

    @ApiModelProperty(value = "采购人电话")
    private String assigneePhone;

    @ApiModelProperty(value = "采购状态 0-新建，1-已分配，2-已领取，3-已完成，4-有异常")
    private Integer status;

    @ApiModelProperty(value = "总金额")
    private BigDecimal allAmount;

}