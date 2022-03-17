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
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 会员信息
 */
@ApiModel(value = "会员信息")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "mms_member")
public class Member implements Serializable {
    /**
     * 自增Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "自增Id")
    private Long id;

    /**
     * 会员Code
     */
    @TableField(value = "member_code")
    @ApiModelProperty(value = "会员Code")
    private Long memberCode;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "`password`")
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 盐
     */
    @TableField(value = "salt")
    @ApiModelProperty(value = "盐")
    private String salt;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 性别
     */
    @TableField(value = "gender")
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 手机号
     */
    @TableField(value = "mobile")
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 会员等级Code
     */
    @TableField(value = "level_code")
    @ApiModelProperty(value = "会员等级Code")
    private Long levelCode;

    /**
     * 头像
     */
    @TableField(value = "`header`")
    @ApiModelProperty(value = "头像")
    private String header;

    /**
     * 出生日期
     */
    @TableField(value = "birth")
    @ApiModelProperty(value = "出生日期")
    private LocalDate birth;

    /**
     * 所在城市
     */
    @TableField(value = "city")
    @ApiModelProperty(value = "所在城市")
    private String city;

    /**
     * 职业
     */
    @TableField(value = "job")
    @ApiModelProperty(value = "职业")
    private String job;

    /**
     * 个性签名
     */
    @TableField(value = "sign")
    @ApiModelProperty(value = "个性签名")
    private String sign;

    /**
     * 用户来源
     */
    @TableField(value = "source_type")
    @ApiModelProperty(value = "用户来源")
    private Integer sourceType;

    /**
     * 积分
     */
    @TableField(value = "integration")
    @ApiModelProperty(value = "积分")
    private Integer integration;

    /**
     * 成长值
     */
    @TableField(value = "growth")
    @ApiModelProperty(value = "成长值")
    private Integer growth;

    /**
     * 启用状态
     */
    @TableField(value = "`status`")
    @ApiModelProperty(value = "启用状态")
    private Integer status;

    /**
     * 访问令牌
     */
    @TableField(value = "social_uid")
    @ApiModelProperty(value = "访问令牌")
    private String socialUid;

    /**
     * 访问令牌的时间
     */
    @TableField(value = "accwss_token")
    @ApiModelProperty(value = "访问令牌的时间")
    private String accwssToken;

    /**
     * 访问令牌过期时间
     */
    @TableField(value = "expires_in")
    @ApiModelProperty(value = "访问令牌过期时间")
    private String expiresIn;

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
    @TableField(value = "update_by")
    @ApiModelProperty(value = "最近一次修改人Code")
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

    public static final String COL_USERNAME = "username";

    public static final String COL_PASSWORD = "password";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_GENDER = "gender";

    public static final String COL_MOBILE = "mobile";

    public static final String COL_EMAIL = "email";

    public static final String COL_LEVEL_CODE = "level_code";

    public static final String COL_HEADER = "header";

    public static final String COL_BIRTH = "birth";

    public static final String COL_CITY = "city";

    public static final String COL_JOB = "job";

    public static final String COL_SIGN = "sign";

    public static final String COL_SOURCE_TYPE = "source_type";

    public static final String COL_INTEGRATION = "integration";

    public static final String COL_GROWTH = "growth";

    public static final String COL_STATUS = "status";

    public static final String COL_SOCIAL_UID = "social_uid";

    public static final String COL_ACCWSS_TOKEN = "accwss_token";

    public static final String COL_EXPIRES_IN = "expires_in";

    public static final String COL_CREATE_BY = "create_by";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_BY = "update_by";

    public static final String COL_UPDATE_TIME = "update_time";
}