package com.abi.tmall.ware.common.response.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 采购单信息
 */
@Data
@ApiModel(value = "采购单信息")
public class PurchasePageResp implements Serializable {

    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    @ApiModelProperty(value = "采购单名字")
    private String purchaseName;

    @ApiModelProperty(value = "采购项数")
    private Integer purchaseDetailCount;

    @ApiModelProperty(value = "采购人Code")
    private Long assigneeCode;

    @ApiModelProperty(value = "采购人姓名")
    private String assigneeName;

    @ApiModelProperty(value = "采购人电话")
    private String assigneePhone;

    @ApiModelProperty(value = "采购单状态 0-新建，1-已分配，2-已领取，3-已完成，4-有异常")
    private Integer status;

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "总金额")
    private BigDecimal allAmount;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDateTime modifyTime;

}