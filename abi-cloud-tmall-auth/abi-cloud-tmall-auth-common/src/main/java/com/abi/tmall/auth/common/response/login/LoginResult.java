package com.abi.tmall.auth.common.response.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: LoginResult
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: token返回信息
 */
@Data
@ApiModel(value = "token返回信息")
public class LoginResult {

    @ApiModelProperty(value = "token名称")
    private String tokenName;

    @ApiModelProperty(value = "token值")
    private String tokenValue;
}
