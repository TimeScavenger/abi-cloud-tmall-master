package com.abi.tmall.auth.dao.entity;

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
import java.time.LocalDateTime;

/**
 * 登录验证码
 */
@ApiModel(value = "登录验证码")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mms_login_captcha")
public class LoginCaptcha extends BaseEntity implements Serializable {

    /**
     * UUID
     */
    @TableField(value = "uuid")
    @ApiModelProperty(value = "UUID")
    private String uuid;

    /**
     * 验证码
     */
    @TableField(value = "code")
    @ApiModelProperty(value = "验证码")
    private String code;

    /**
     * 过期时间
     */
    @TableField(value = "expire_time")
    @ApiModelProperty(value = "过期时间")
    private LocalDateTime expireTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_UUID = "uuid";

    public static final String COL_CODE = "code";

    public static final String COL_EXPIRE_TIME = "expire_time";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}