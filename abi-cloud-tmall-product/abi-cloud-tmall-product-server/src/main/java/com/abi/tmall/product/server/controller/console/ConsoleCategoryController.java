package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.category.*;
import com.abi.tmall.product.common.response.category.CategoryInfoVo;
import com.abi.tmall.product.common.response.category.CategoryTreeVo;
import com.abi.tmall.product.server.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CategoryController
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description: 商品分类模块
 */
@Api(tags = "商品分类模块")
@Slf4j
@RestController
@RequestMapping("/console/category")
public class ConsoleCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list/tree")
    @ApiOperation(value = "列表 分类以及子分类 以树形结构组装起来")
    public ApiResponse<List<CategoryTreeVo>> queryCategoryListWithTree(@RequestBody @Validated CategoryTreeDto categoryTreeDto) {
        return ApiResponse.result(categoryService.queryCategoryListWithTree(categoryTreeDto.getLevel()));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 分类信息")
    public ApiResponse<Boolean> saveCategory(@RequestBody @Validated CategoryAddDto categoryAddDto) {
        return ApiResponse.result(categoryService.saveCategory(categoryAddDto));
    }

    // 注意：@RequestBody: 获取请求体，必须发送POST请求SpringMVC自动将请求体的数据（json），转为对应的对象
    @PostMapping("/remove")
    @ApiOperation(value = "删除 根据ids删除分类信息")
    public ApiResponse<Boolean> removeCategory(@RequestBody @Validated CategoryDelDto categoryDelDto) {
        return ApiResponse.result(categoryService.removeCategory(categoryDelDto));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 分类信息")
    public ApiResponse<Boolean> modifyCategory(@RequestBody @Validated CategoryEditDto categoryEditDto) {
        return ApiResponse.result(categoryService.modifyCategory(categoryEditDto));
    }

    @PostMapping("/modify/sort")
    @ApiOperation(value = "修改 分类排序")
    public ApiResponse<Boolean> modifyCategorySort(@RequestBody @Validated List<CategorySortDto> categorySortDtos) {
        return ApiResponse.result(categoryService.modifyCategorySort(categorySortDtos));
    }

    @GetMapping("/find/{categoryCode}")
    @ApiOperation(value = "查询 根据分类id查询分类信息")
    public ApiResponse<CategoryInfoVo> findCategoryByCode(@PathVariable("categoryCode") Long categoryCode) {
        return ApiResponse.result(categoryService.findCategoryByCode(categoryCode));
    }
}
