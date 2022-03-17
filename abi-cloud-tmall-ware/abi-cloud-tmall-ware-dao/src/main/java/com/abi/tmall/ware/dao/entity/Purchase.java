package com.abi.tmall.ware.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 采购单信息
 */
@ApiModel(value = "采购单信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_purchase")
public class Purchase implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

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

    /**
     * 是否删除 0-未删除，1-删除
     */
    @TableLogic
    @TableField(value = "deleted")
    @ApiModelProperty(value = "是否删除 0-未删除，1-删除")
    private Integer deleted;

    /**
     * 创建人Code
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人Code")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 最近一次修改人Code
     */
    @TableField(value = "modify_by")
    @ApiModelProperty(value = "最近一次修改人Code")
    private String modifyBy;

    /**
     * 最近一次修改时间
     */
    @TableField(value = "modify_time")
    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDateTime modifyTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

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

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}