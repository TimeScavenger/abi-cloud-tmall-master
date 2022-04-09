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

/**
 * 库存工作单项
 */
@ApiModel(value = "库存工作单项")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_ware_task_sku_detail")
public class WareTaskItem extends BaseEntity implements Serializable {

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

    private static final long serialVersionUID = 1L;

    public static final String COL_TASK_CODE = "task_code";

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_SKU_NAME = "sku_name";

    public static final String COL_SKU_NUM = "sku_num";

}