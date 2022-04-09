package com.abi.tmall.product.server.controller.web;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.category.CategoryTreeReq;
import com.abi.tmall.product.common.response.category.CategoryTreeResp;
import com.abi.tmall.product.server.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品分类 Web模块
 *
 * @ClassName: WebCategoryController
 * @Author: illidan
 * @CreateDate: 2021/05/13
 * @Description: 商品分类模块
 */
@Api(tags = "商品分类 Web模块")
@Slf4j
@RestController
@RequestMapping("/web/category")
public class WebCategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/list/tree")
    @ApiOperation(value = "列表 分类以及子分类 以树形结构组装起来")
    public ApiResponse<List<CategoryTreeResp>> queryCategoryListWithTree(@RequestBody @Validated CategoryTreeReq categoryTreeReq) {
        return ApiResponse.result(categoryService.queryCategoryListWithTree(categoryTreeReq.getLevel()));
    }

}
