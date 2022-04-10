package com.abi.tmall.ware.common.request.ware;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 仓库信息
 */
@Data
@ApiModel(value = "仓库信息")
public class WareStockReq {

    @NotEmpty(message = "SkuCode不能为空")
    @ApiModelProperty(value = "SkuCode列表")
    private List<Long> skuCodes;

}