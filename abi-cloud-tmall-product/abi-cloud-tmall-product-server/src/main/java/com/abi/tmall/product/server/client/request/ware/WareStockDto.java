package com.abi.tmall.product.server.client.request.ware;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 仓库信息
 */
@ApiModel(value = "仓库信息")
@Data
public class WareStockDto {

    @NotEmpty(message = "SkuCode不能为空")
    @ApiModelProperty(value = "SkuCode列表")
    private List<Long> skuCodes;

}