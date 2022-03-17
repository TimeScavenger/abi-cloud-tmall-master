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
 * 采购单明细信息
 */
@ApiModel(value = "采购单明细信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_purchase_detail")
public class PurchaseDetail implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

    /**
     * 采购项Code
     */
    @TableField(value = "purchase_detail_code")
    @ApiModelProperty(value = "采购项Code")
    private Long purchaseDetailCode;

    /**
     * 采购单Code
     */
    @TableField(value = "purchase_code")
    @ApiModelProperty(value = "采购单Code")
    private Long purchaseCode;

    /**
     * 采购商品Code
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "采购商品Code")
    private Long skuCode;

    /**
     * 采购商品数量
     */
    @TableField(value = "sku_num")
    @ApiModelProperty(value = "采购商品数量")
    private Integer skuNum;

    /**
     * 采购商品金额
     */
    @TableField(value = "sku_price")
    @ApiModelProperty(value = "采购商品金额")
    private BigDecimal skuPrice;

    /**
     * 仓库Code
     */
    @TableField(value = "ware_code")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    /**
     * 采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败")
    private Integer status;

    /**
     * 原因
     */
    @TableField(value = "reason")
    @ApiModelProperty(value = "原因")
    private String reason;

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

    public static final String COL_PURCHASE_DETAIL_CODE = "purchase_detail_code";

    public static final String COL_PURCHASE_CODE = "purchase_code";

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_SKU_NUM = "sku_num";

    public static final String COL_SKU_PRICE = "sku_price";

    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_STATUS = "status";

    public static final String COL_REASON = "reason";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}