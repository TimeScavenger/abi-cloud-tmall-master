package com.abi.tmall.product.dao.entity;

import com.abi.infrastructure.dao.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 商品分类-品牌关系
 */
@ApiModel(value = "商品分类-品牌关系")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_category_brand_relation")
public class CategoryBrandRelation extends BaseEntity implements Serializable {

    /**
     * 分类Code
     */
    @TableField(value = "category_code")
    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    /**
     * 分类名称
     */
    @TableField(value = "category_name")
    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    /**
     * 品牌Code
     */
    @TableField(value = "brand_code")
    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    /**
     * 品牌名称
     */
    @TableField(value = "brand_name")
    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    private static final long serialVersionUID = 1L;

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_CATEGORY_NAME = "category_name";

    public static final String COL_BRAND_CODE = "brand_code";

    public static final String COL_BRAND_NAME = "brand_name";
}