package com.abi.tmall.product.dao.entity;

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
 * 商品分类
 */
@ApiModel(value = "商品分类")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "pms_category")
public class Category implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

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
     * 分类父级Code
     */
    @TableField(value = "parent_code")
    @ApiModelProperty(value = "分类父级Code")
    private Long parentCode;

    /**
     * 分类层级 1-一级，2-二级，3-三级，4-四级
     */
    @TableField(value = "`level`")
    @ApiModelProperty(value = "分类层级 1-一级，2-二级，3-三级，4-四级")
    private Integer level;

    /**
     * 分类排序
     */
    @TableField(value = "sort")
    @ApiModelProperty(value = "分类排序")
    private Integer sort;

    /**
     * 图标地址
     */
    @TableField(value = "icon")
    @ApiModelProperty(value = "图标地址")
    private String icon;

    /**
     * 计量单位
     */
    @TableField(value = "product_unit")
    @ApiModelProperty(value = "计量单位")
    private String productUnit;

    /**
     * 商品数量
     */
    @TableField(value = "product_count")
    @ApiModelProperty(value = "商品数量")
    private Integer productCount;

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

    public static final String COL_CATEGORY_CODE = "category_code";

    public static final String COL_CATEGORY_NAME = "category_name";

    public static final String COL_PARENT_CODE = "parent_code";

    public static final String COL_LEVEL = "level";

    public static final String COL_SORT = "sort";

    public static final String COL_ICON = "icon";

    public static final String COL_PRODUCT_UNIT = "product_unit";

    public static final String COL_PRODUCT_COUNT = "product_count";

    public static final String COL_SHOWED = "showed";

    public static final String COL_ENABLED = "enabled";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}