package com.abi.tmall.product.common.request.category;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增分类
 */
@Data
@Api("新增分类")
public class CategoryAddReq {

    @NotBlank(message = "分类名称不能为空")
    @ApiModelProperty(value = "分类名称", name = "categoryName", example = "电脑", required = true)
    private String categoryName;

    @NotNull(message = "父分类Code不能为空")
    @ApiModelProperty(value = "父分类Code", name = "parentCode", example = "1", required = true)
    private Long parentCode;

    @Max(value = 4, message = "分类层级不能超过4级")
    @Min(value = 1, message = "分类层级参数异常")
    @NotNull(message = "分类层级不能为空")
    @ApiModelProperty(value = "层级 1-4", name = "level", example = "1", required = true)
    private Integer level;

    @ApiModelProperty(value = "排序", name = "sort", example = "1")
    private Integer sort;

    @ApiModelProperty(value = "图标地址", name = "icon", example = "https://tmall-master.oss-cn-shanghai.aliyuncs.com/2021-03-28/a1dd375d-8c3d-4d0c-8156-333ed8e3070f.jpeg")
    private String icon;

    @ApiModelProperty(value = "计量单位", name = "sort", example = "件")
    private String productUnit;

    @ApiModelProperty(value = "商品数量", name = "productCount", example = "1")
    private Integer productCount;

}
