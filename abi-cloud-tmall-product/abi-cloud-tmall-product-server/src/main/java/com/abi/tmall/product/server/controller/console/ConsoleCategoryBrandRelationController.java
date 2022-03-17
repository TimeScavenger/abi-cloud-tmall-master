package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.category.CbRelationAddDto;
import com.abi.tmall.product.common.request.category.CbRelationListByCategoryDto;
import com.abi.tmall.product.common.request.category.CbRelationListByBrandDto;
import com.abi.tmall.product.common.request.category.CbRelationDelDto;
import com.abi.tmall.product.common.response.category.CbRelationListByBrandVo;
import com.abi.tmall.product.common.response.category.CbRelationListByCategoryVo;
import com.abi.tmall.product.server.service.CategoryBrandRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: CategoryBrandRelationController
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description: 商品分类-品牌关系模块
 */
@Api(tags = "商品分类-品牌关系模块")
@Slf4j
@RestController
@RequestMapping("/console/category-brand-relation")
public class ConsoleCategoryBrandRelationController {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @PostMapping("/list/categorys/by/brandCode")
    @ApiOperation(value = "查询 品牌关联的所有分类列表")
    public ApiResponse<List<CbRelationListByCategoryVo>> queryCategoryListByBrandCode(@RequestBody @Validated CbRelationListByBrandDto cbRelationListByBrandDto) {
        return ApiResponse.result(categoryBrandRelationService.queryCategoryListByBrandCode(cbRelationListByBrandDto));
    }

    @PostMapping("/list/brands/by/categoryCode")
    @ApiOperation(value = "查询 分类关联的所有品牌列表")
    public ApiResponse<List<CbRelationListByBrandVo>> queryBrandListByCategoryCode(@RequestBody @Validated CbRelationListByCategoryDto cbRelationListByCategoryDto) {
        return ApiResponse.result(categoryBrandRelationService.queryBrandListByCategoryCode(cbRelationListByCategoryDto));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 品牌和分类关系的对象")
    public ApiResponse<Boolean> saveBrandCategoryRelation(@RequestBody @Validated CbRelationAddDto cbRelationAddDto) {
        return ApiResponse.result(categoryBrandRelationService.saveBrandCategoryRelation(cbRelationAddDto));
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "删除 品牌和分类关系的对象")
    public ApiResponse<Boolean> removeBrandCategoryRelation(@RequestBody @Validated CbRelationDelDto cbRelationDelDto) {
        return ApiResponse.result(categoryBrandRelationService.removeBrandCategoryRelation(cbRelationDelDto));
    }
}
