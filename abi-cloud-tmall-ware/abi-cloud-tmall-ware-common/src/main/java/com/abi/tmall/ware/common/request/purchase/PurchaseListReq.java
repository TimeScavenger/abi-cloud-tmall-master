package com.abi.tmall.ware.common.request.purchase;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询采购单列表
 */
@Data
@ApiModel(value = "查询采购单列表")
public class PurchaseListReq extends PageRequest {

    @ApiModelProperty(value = "采购状态")
    private Integer status;

}