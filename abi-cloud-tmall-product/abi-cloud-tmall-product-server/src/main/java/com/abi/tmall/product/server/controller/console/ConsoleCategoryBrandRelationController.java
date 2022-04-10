package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.category.CbRelationAddReq;
import com.abi.tmall.product.common.request.category.CbRelationDelReq;
import com.abi.tmall.product.common.request.category.CbRelationListByBrandReq;
import com.abi.tmall.product.common.request.category.CbRelationListByCategoryReq;
import com.abi.tmall.product.common.response.category.CbRelationListByBrandResp;
import com.abi.tmall.product.common.response.category.CbRelationListByCategoryResp;
import com.abi.tmall.product.server.service.CategoryBrandRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品分类-品牌关系 Console模块
 *
 * @ClassName: CategoryBrandRelationController
 * @Author: illidan
 * @CreateDate: 2021/5/19
 * @Description:
 */
@Api(tags = "商品分类-商品品牌关系 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/category-brand-relation")
public class ConsoleCategoryBrandRelationController {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 查询 品牌关联的所有分类列表
     *
     * @param cbRelationListByBrandReq 品牌Code
     * @return 分类列表
     */
    @PostMapping("/list/categorys/by/brandCode")
    @ApiOperation(value = "查询 品牌关联的所有分类列表")
    public ApiResponse<List<CbRelationListByCategoryResp>> queryCategoryListByBrandCode(@RequestBody @Validated CbRelationListByBrandReq cbRelationListByBrandReq) {
        return ApiResponse.result(categoryBrandRelationService.queryCategoryListByBrandCode(cbRelationListByBrandReq));
    }

    /**
     * 查询 分类关联的所有品牌列表
     *
     * @param cbRelationListByCategoryReq 分类Code
     * @return 品牌列表
     */
    @PostMapping("/list/brands/by/categoryCode")
    @ApiOperation(value = "查询 分类关联的所有品牌列表")
    public ApiResponse<List<CbRelationListByBrandResp>> queryBrandListByCategoryCode(@RequestBody @Validated CbRelationListByCategoryReq cbRelationListByCategoryReq) {
        return ApiResponse.result(categoryBrandRelationService.queryBrandListByCategoryCode(cbRelationListByCategoryReq));
    }

    /**
     * 添加 品牌和分类关系的对象
     *
     * @param cbRelationAddReq 品牌和分类关系
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加 品牌和分类关系的对象")
    public ApiResponse<Boolean> saveBrandCategoryRelation(@RequestBody @Validated CbRelationAddReq cbRelationAddReq) {
        return ApiResponse.result(categoryBrandRelationService.saveBrandCategoryRelation(cbRelationAddReq));
    }

    /**
     * 删除 品牌和分类关系的对象
     *
     * @param cbRelationDelReq 品牌和分类关系
     * @return 删除是否成功: true-成功, false-失败
     */
    @DeleteMapping("/remove")
    @ApiOperation(value = "删除 品牌和分类关系的对象")
    public ApiResponse<Boolean> removeBrandCategoryRelation(@RequestBody @Validated CbRelationDelReq cbRelationDelReq) {
        return ApiResponse.result(categoryBrandRelationService.removeBrandCategoryRelation(cbRelationDelReq));
    }
}
