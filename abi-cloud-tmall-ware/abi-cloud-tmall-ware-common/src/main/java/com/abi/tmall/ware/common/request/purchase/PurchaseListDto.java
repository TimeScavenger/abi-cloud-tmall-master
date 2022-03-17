package com.abi.tmall.ware.common.request.purchase;

import com.abi.base.foundation.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询采购单列表
 */
@ApiModel(value = "查询采购单列表")
@Data
public class PurchaseListDto extends PageRequest {

    @ApiModelProperty(value = "采购状态")
    private Integer status;

}