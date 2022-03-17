package com.abi.tmall.product.common.response.spu;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 查询Spu分页
 */
@Data
@Api("查询Spu分页")
public class SpuPageVo implements Serializable {

    @ApiModelProperty(value = "spuCode")
    private Long spuCode;

    @ApiModelProperty(value = "商品名称")
    private String spuName;

    @ApiModelProperty(value = "商品简单描述")
    private String spuDesc;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "商品重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "上架状态 0-新建，1-上架，2-下架")
    private Integer publishStatus;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;

}