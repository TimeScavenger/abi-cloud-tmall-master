package com.abi.tmall.auth.common.request.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: LoginByPwdWithCapReq
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 账号密码验证码登录
 */
@Data
@ApiModel(value = "账号密码验证码登录")
public class LoginByPwdWithCapReq {

    @NotBlank(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String captcha;

    @ApiModelProperty(value = "uuid")
    private String uuid;
}