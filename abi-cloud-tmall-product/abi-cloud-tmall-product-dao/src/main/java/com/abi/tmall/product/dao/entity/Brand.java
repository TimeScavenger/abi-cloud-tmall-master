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
 * 商品品牌
 */
@ApiModel(value = "商品品牌")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_brand")
public class Brand extends BaseEntity implements Serializable {

    /**
     * 品牌Code
     */
    @TableField(value = "brand_code")
    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    /**
     * 品牌名字
     */
    @TableField(value = "brand_name")
    @ApiModelProperty(value = "品牌名字")
    private String brandName;

    /**
     * 品牌logo
     */
    @TableField(value = "logo")
    @ApiModelProperty(value = "品牌logo")
    private String logo;

    /**
     * 品牌描述
     */
    @TableField(value = "`desc`")
    @ApiModelProperty(value = "品牌描述")
    private String desc;

    /**
     * 品牌排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "品牌排序")
    private Integer sort;

    /**
     * 检索首字母
     */
    @TableField(value = "first_letter")
    @ApiModelProperty(value = "检索首字母")
    private String firstLetter;

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

    public static final String COL_BRAND_CODE = "brand_code";

    public static final String COL_BRAND_NAME = "brand_name";

    public static final String COL_LOGO = "logo";

    public static final String COL_DESC = "desc";

    public static final String COL_SORT = "sort";

    public static final String COL_FIRST_LETTER = "first_letter";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

}