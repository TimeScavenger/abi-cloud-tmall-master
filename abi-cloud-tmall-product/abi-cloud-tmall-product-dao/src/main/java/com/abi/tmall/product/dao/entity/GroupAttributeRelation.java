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
 * 商品属性分组-属性关系
 */
@ApiModel(value = "商品属性分组-属性关系")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_group_attribute_relation")
public class GroupAttributeRelation extends BaseEntity implements Serializable {

    /**
     * 分组Code
     */
    @TableField(value = "group_code")
    @ApiModelProperty(value = "分组Code")
    private Long groupCode;

    /**
     * 分组名称
     */
    @TableField(value = "group_name")
    @ApiModelProperty(value = "分组名称")
    private String groupName;

    /**
     * 属性Code
     */
    @TableField(value = "attribute_code")
    @ApiModelProperty(value = "属性Code")
    private Long attributeCode;

    /**
     * 属性名称
     */
    @TableField(value = "attribute_name")
    @ApiModelProperty(value = "属性名称")
    private String attributeName;

    /**
     * 排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "排序")
    private Integer sort;

    private static final long serialVersionUID = 1L;

    public static final String COL_GROUP_CODE = "group_code";

    public static final String COL_GROUP_NAME = "group_name";

    public static final String COL_ATTRIBUTE_CODE = "attribute_code";

    public static final String COL_ATTRIBUTE_NAME = "attribute_name";

    public static final String COL_SORT = "sort";

}