package com.abi.tmall.product.common.response.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 查询分类树结构
 */
@Data
@Api("查询分类树结构")
public class CategoryTreeVo {

    @ApiModelProperty(value = "分类Code")
    private Long categoryCode;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "父分类Code")
    private Long parentCode;

    @ApiModelProperty(value = "层级")
    private Integer level;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @JsonInclude(JsonInclude.Include.NON_EMPTY) // 标注：该字段为空的时候不返回
    @ApiModelProperty(value = "子类分类列表")
    private List<CategoryTreeVo> children;

}
