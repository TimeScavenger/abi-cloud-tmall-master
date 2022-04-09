package com.abi.tmall.product.dao.entity;

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
 * Spu介绍图片
 */
@ApiModel(value = "Spu介绍图片")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_spu_img_desc")
public class SpuImgDesc extends BaseEntity implements Serializable {

    /**
     * spuCode
     */
    @TableField(value = "spu_code")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    /**
     * 商品介绍图片Url
     */
    @TableField(value = "img_url")
    @ApiModelProperty(value = "商品介绍图片Url")
    private String imgUrl;

    /**
     * 是否显示 0-不显示，1-显示
     */
    @TableField(value = "showed")
    @ApiModelProperty(value = "是否显示 0-不显示，1-显示")
    private Integer showed;

    /**
     * 启用状态 0-未启用，1-启用
     */
    @TableField(value = "enabled")
    @ApiModelProperty(value = "启用状态 0-未启用，1-启用")
    private Integer enabled;

    private static final long serialVersionUID = 1L;

    public static final String COL_SPU_CODE = "spu_code";

    public static final String COL_IMG_URL = "img_url";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

}