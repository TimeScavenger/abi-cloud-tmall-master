package com.abi.tmall.auth.common.request.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: BaseLoginReq
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录基础类
 */
@Data
@ApiModel(value = "登录基础类")
public class BaseLoginReq {

    /**
     * 系统ID 1-中台console，2-iSales-Console，3-钉钉
     */
    @ApiModelProperty(value = "系统ID 1-中台console，2-iSales-Console，3-钉钉")
    private Integer systemId = 1;
}
