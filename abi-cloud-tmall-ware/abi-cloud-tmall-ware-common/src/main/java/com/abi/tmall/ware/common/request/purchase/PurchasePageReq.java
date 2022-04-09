package com.abi.tmall.ware.common.request.purchase;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询采购单分页
 */
@Data
@ApiModel(value = "查询采购单分页")
public class PurchasePageReq extends PageRequest {

    @ApiModelProperty(value = "采购单名字")
    private String purchaseName;

    @ApiModelProperty(value = "采购状态")
    private Integer status;

}