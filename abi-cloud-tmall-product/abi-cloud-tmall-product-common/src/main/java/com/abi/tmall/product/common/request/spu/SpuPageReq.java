package com.abi.tmall.product.common.request.spu;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询Spu分页
 */
@Data
@ApiModel("查询Spu分页")
public class SpuPageReq extends PageRequest {

    @ApiModelProperty(value = "所属分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "品牌Code")
    private Long brandCode;

    @ApiModelProperty(value = "上架状态 0-新建，1-上架，2-下架")
    private Integer publishStatus;

    @ApiModelProperty(value = "商品名称")
    private String spuName;

}