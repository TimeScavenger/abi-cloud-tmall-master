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
 * 商品库存
 */
@ApiModel(value = "商品库存")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_ware_sku_relation")
public class WareSkuRelation extends BaseEntity implements Serializable {

    /**
     * 仓库Code
     */
    @TableField(value = "ware_code")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    /**
     * skuCode
     */
    @TableField(value = "sku_code")
    @ApiModelProperty(value = "skuCode")
    private Long skuCode;

    /**
     * 库存数
     */
    @TableField(value = "stock")
    @ApiModelProperty(value = "库存数")
    private Integer stock;

    /**
     * 锁定库存
     */
    @TableField(value = "stock_locked")
    @ApiModelProperty(value = "锁定库存")
    private Integer stockLocked;

    private static final long serialVersionUID = 1L;

    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_SKU_CODE = "sku_code";

    public static final String COL_STOCK = "stock";

    public static final String COL_STOCK_LOCKED = "stock_locked";

}