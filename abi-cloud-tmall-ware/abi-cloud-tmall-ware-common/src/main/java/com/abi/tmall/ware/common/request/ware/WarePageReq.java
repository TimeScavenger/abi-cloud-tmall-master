package com.abi.tmall.ware.common.request.ware;

import com.abi.infrastructure.dao.page.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 仓库信息
 */
@Data
@ApiModel(value = "仓库信息")
public class WarePageReq extends PageRequest {

    @ApiModelProperty(value = "仓库Code")
    private Long wareCode;

    @ApiModelProperty(value = "仓库名")
    private String wareName;

    @ApiModelProperty(value = "仓库地址")
    private String wareAddress;

    @ApiModelProperty(value = "区域编码")
    private String areaCode;

}