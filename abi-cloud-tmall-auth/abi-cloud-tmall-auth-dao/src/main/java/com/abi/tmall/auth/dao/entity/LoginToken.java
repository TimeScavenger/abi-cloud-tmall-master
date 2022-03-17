package com.abi.tmall.auth.dao.entity;

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
import java.time.LocalDateTime;

/**
 * 登录Token
 */
@ApiModel(value = "登录Token")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mms_login_token")
public class LoginToken implements Serializable {
    /**
     * 自增ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增ID")
    private Long id;

    /**
     * 会员Code
     */
    @TableField(value = "member_code")
    @ApiModelProperty(value = "会员Code")
    private Long memberCode;

    /**
     * 童虎类型 0-系统用户 1-TM用户
     */
    @TableField(value = "`type`")
    @ApiModelProperty(value = "童虎类型 0-系统用户 1-TM用户")
    private Integer type;

    /**
     * token
     */
    @TableField(value = "token")
    @ApiModelProperty(value = "token")
    private String token;

    /**
     * 过期时间
     */
    @TableField(value = "expire_time")
    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    /**
     * 创建人ID
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value = "创建人ID")
    private String createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 最近一次修改人ID
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value = "最近一次修改人ID")
    private String updateBy;

    /**
     * 最近一次修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "最近一次修改时间")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_CODE = "user_code";

    public static final String COL_TYPE = "type";

    public static final String COL_TOKEN = "token";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}