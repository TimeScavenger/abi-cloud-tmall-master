package com.abi.tmall.ware.server.client.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 查询Sku列表
 */
@Data
@ApiModel("查询Sku列表")
public class SkuListByCodeReq {

    @ApiModelProperty(value = "skuCodes")
    @NotEmpty(message = "SkuCode列表不能为空")
    List<Long> skuCodes;

}