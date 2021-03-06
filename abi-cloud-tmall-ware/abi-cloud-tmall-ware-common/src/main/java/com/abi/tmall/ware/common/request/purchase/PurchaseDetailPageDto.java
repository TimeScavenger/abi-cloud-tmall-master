package com.abi.tmall.ware.common.request.purchase;

import com.abi.base.foundation.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查看采购项分页
 */
@ApiModel(value = "查看采购项分页")
@Data
public class PurchaseDetailPageDto extends PageRequest {

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "采购项状态 0-新建，1-已分配，2-正在采购，3-已完成，4-采购失败")
    private Integer status;

    @ApiModelProperty(value = "sku名字")
    private String skuName;

}