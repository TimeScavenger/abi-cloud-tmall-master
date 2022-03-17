package com.abi.tmall.ware.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 库存工作单
 */
@ApiModel(value = "库存工作单")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_ware_task")
public class WareTask implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

    /**
     * 工作单Code
     */
    @TableField(value = "task_code")
    @ApiModelProperty(value = "工作单Code")
    private Long taskCode;

    /**
     * 仓库Code
     */
    @TableField(value = "ware_code")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    /**
     * 订单Code
     */
    @TableField(value = "order_code")
    @ApiModelProperty(value = "订单Code")
    private Long orderCode;

    /**
     * 订单码
     */
    @TableField(value = "order_sn")
    @ApiModelProperty(value = "订单码")
    private String orderSn;

    /**
     * 收货人
     */
    @TableField(value = "consignee")
    @ApiModelProperty(value = "收货人")
    private String consignee;

    /**
     * 收货人电话
     */
    @TableField(value = "consignee_phone")
    @ApiModelProperty(value = "收货人电话")
    private String consigneePhone;

    /**
     * 配送地址
     */
    @TableField(value = "delivery_address")
    @ApiModelProperty(value = "配送地址")
    private String deliveryAddress;

    /**
     * 订单备注
     */
    @TableField(value = "order_comment")
    @ApiModelProperty(value = "订单备注")
    private String orderComment;

    /**
     * 付款方式 1-在线付款，2-货到付款
     */
    @TableField(value = "payment_way")
    @ApiModelProperty(value = "付款方式 1-在线付款，2-货到付款")
    private Integer paymentWay;

    /**
     * 任务状态
     */
    @TableField(value = "task_status")
    @ApiModelProperty(value = "任务状态")
    private Integer taskStatus;

    /**
     * 订单描述
     */
    @TableField(value = "order_body")
    @ApiModelProperty(value = "订单描述")
    private String orderBody;

    /**
     * 物流单号
     */
    @TableField(value = "tracking_no")
    @ApiModelProperty(value = "物流单号")
    private String trackingNo;

    /**
     * 工作单备注
     */
    @TableField(value = "task_comment")
    @ApiModelProperty(value = "工作单备注")
    private String taskComment;

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

    public static final String COL_TASK_CODE = "task_code";

    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_ORDER_CODE = "order_code";

    public static final String COL_ORDER_SN = "order_sn";

    public static final String COL_CONSIGNEE = "consignee";

    public static final String COL_CONSIGNEE_PHONE = "consignee_phone";

    public static final String COL_DELIVERY_ADDRESS = "delivery_address";

    public static final String COL_ORDER_COMMENT = "order_comment";

    public static final String COL_PAYMENT_WAY = "payment_way";

    public static final String COL_TASK_STATUS = "task_status";

    public static final String COL_ORDER_BODY = "order_body";

    public static final String COL_TRACKING_NO = "tracking_no";

    public static final String COL_TASK_COMMENT = "task_comment";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}