package com.abi.tmall.product.common.request.spu;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 上架Spu
 */
@Data
@Api("上架Spu")
public class SpuUpReq {

    @NotNull(message = "SpuCode不能为空")
    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

}