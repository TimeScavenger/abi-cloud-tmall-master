package com.abi.tmall.auth.common.request.login;

import com.abi.tmall.auth.common.request.base.BaseLoginReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: LoginByWechatReq
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 微信登录
 */
@Data
@ApiModel(value = "微信登录")
public class LoginByWechatReq extends BaseLoginReq {

    @NotBlank(message = "appId不能为空")
    @ApiModelProperty("公众号/小程序appId")
    private String appId;

    @NotBlank(message = "验证码不能为空")
    @ApiModelProperty("验证码")
    private String authCode;
}
