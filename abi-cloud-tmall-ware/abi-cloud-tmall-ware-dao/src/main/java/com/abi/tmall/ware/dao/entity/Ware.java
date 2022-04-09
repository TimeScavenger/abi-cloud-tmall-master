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
 * 仓库
 */
@ApiModel(value = "仓库")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_ware")
public class Ware extends BaseEntity implements Serializable {

    /**
     * 仓库Code
     */
    @TableField(value = "ware_code")
    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    /**
     * 仓库名
     */
    @TableField(value = "ware_name")
    @ApiModelProperty(value = "仓库名")
    private String wareName;

    /**
     * 仓库地址
     */
    @TableField(value = "ware_address")
    @ApiModelProperty(value = "仓库地址")
    private String wareAddress;

    /**
     * 区域编码
     */
    @TableField(value = "area_code")
    @ApiModelProperty(value = "区域编码")
    private String areaCode;

    private static final long serialVersionUID = 1L;


    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_WARE_NAME = "ware_name";

    public static final String COL_WARE_ADDRESS = "ware_address";

    public static final String COL_WARE_AREACODE = "ware_areacode";

}