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
@TableName(value = "wms_ware_task_sku_detail")
public class WareTaskSkuDetail implements Serializable {
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
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * sku名字
     */
    @TableField(value = "sku_name")
    @ApiModelProperty(value = "sku名字")
    private String skuName;

    /**
     * 购买个数
     */
    @TableField(value = "sku_num")
    @ApiModelProperty(value = "购买个数")
    private Integer skuNum;

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

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_SKU_NAME = "sku_name";

    public static final String COL_SKU_NUM = "sku_num";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}