package com.abi.tmall.ware.dao.entity;

import com.abi.infrastructure.dao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 采购单
 */
@ApiModel(value = "采购单")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_purchase")
public class Purchase extends BaseEntity implements Serializable {

    /**
     * 采购单Code
     */
    @TableField(value = "purchase_code")
    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    /**
     * 采购单名字
     */
    @TableField(value = "purchase_name")
    @ApiModelProperty(value = "采购单名字")
    private String purchaseName;

    /**
     * 采购人Code
     */
    @TableField(value = "assignee_code")
    @ApiModelProperty(value = "采购人Code")
    private Long assigneeCode;

    /**
     * 采购人姓名
     */
    @TableField(value = "assignee_name")
    @ApiModelProperty(value = "采购人姓名")
    private String assigneeName;

    /**
     * 采购人电话
     */
    @TableField(value = "assignee_phone")
    @ApiModelProperty(value = "采购人电话")
    private String assigneePhone;

    /**
     * 采购单状态 0-新建，1-已分配，2-已领取，3-已完成，4-有异常
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "采购单状态 0-新建，1-已分配，2-已领取，3-已完成，4-有异常")
    private Integer status;

    /**
     * 原因
     */
    @TableField(value = "reason")
    @ApiModelProperty(value = "原因")
    private String reason;

    /**
     * 仓库Code
     */
    @TableField(value = "ware_code")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    /**
     * 总金额
     */
    @TableField(value = "all_amount")
    @ApiModelProperty(value = "总金额")
    private BigDecimal allAmount;

    /**
     * 优先级
     */
    @TableField(value = "priority")
    @ApiModelProperty(value = "优先级 1-5的优先级")
    private Integer priority;

    private static final long serialVersionUID = 1L;

    public static final String COL_PURCHASE_CODE = "purchase_code";

    public static final String COL_PURCHASE_NAME = "purchase_name";

    public static final String COL_ASSIGNEE_CODE = "assignee_code";

    public static final String COL_ASSIGNEE_NAME = "assignee_name";

    public static final String COL_ASSIGNEE_PHONE = "assignee_phone";

    public static final String COL_STATUS = "status";

    public static final String COL_REASON = "reason";

    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_ALL_AMOUNT = "all_amount";

    public static final String COL_PRIORITY = "priority";

}