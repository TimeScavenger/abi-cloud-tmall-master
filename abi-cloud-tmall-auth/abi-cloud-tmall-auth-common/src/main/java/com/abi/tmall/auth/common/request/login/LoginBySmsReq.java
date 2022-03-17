package com.abi.tmall.auth.common.request.login;

import com.abi.tmall.auth.common.request.base.BaseLoginReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: LoginBySmsReq
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 手机号验证码登录
 */
@Data
@ApiModel(value = "手机号验证码登录")
public class LoginBySmsReq extends BaseLoginReq {

    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "手机号")
    private String phone;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String authCode;
}