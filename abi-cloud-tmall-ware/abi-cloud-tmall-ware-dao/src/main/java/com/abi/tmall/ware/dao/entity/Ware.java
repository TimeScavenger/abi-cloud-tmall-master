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
 * 仓库信息
 */
@ApiModel(value = "仓库信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "wms_ware")
public class Ware implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

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

    public static final String COL_WARE_CODE = "ware_code";

    public static final String COL_WARE_NAME = "ware_name";

    public static final String COL_WARE_ADDRESS = "ware_address";

    public static final String COL_WARE_AREACODE = "ware_areacode";

    public static final String COL_DELETED = "deleted";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_MODIFY_BY = "modify_by";

    public static final String COL_MODIFY_TIME = "modify_time";
}