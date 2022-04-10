package com.abi.tmall.ware.common.request.purchase.item;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查看采购项分页
 */
@Data
@ApiModel(value = "查看采购项分页")
public class PurchaseItemPageReq extends PageRequest {

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败")
    private Integer status;

    @ApiModelProperty(value = "sku名字")
    private String skuName;

}