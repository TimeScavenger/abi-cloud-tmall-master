package com.abi.tmall.ware.common.request.purchase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 新增采购单
 */
@ApiModel(value = "新增采购单")
@Data
public class PurchaseAddDto implements Serializable {

    @NotBlank(message = "采购单名不能为空")
    @ApiModelProperty(value = "采购单名")
    private String purchaseName;

    @NotNull(message = "优先级不能为空")
    @ApiModelProperty(value = "优先级 1-5的优先级")
    private Integer priority;

    @NotNull(message = "仓库Code不能为空")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

}