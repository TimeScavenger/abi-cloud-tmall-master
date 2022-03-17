package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.brand.*;
import com.abi.tmall.product.common.response.brand.BrandInfoVo;
import com.abi.tmall.product.common.response.brand.BrandListVo;
import com.abi.tmall.product.common.response.brand.BrandPageVo;
import com.abi.tmall.product.server.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: BrandController
 * @Author: illidan
 * @CreateDate: 2021/5/18
 * @Description: 商品品牌模块
 */
@Api(tags = "商品品牌模块")
@Slf4j
@RestController
@RequestMapping("/console/brand")
public class ConsoleBrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 品牌分页列表")
    public ApiResponse<PageResponse<BrandPageVo>> queryBrandPageByCondition(@RequestBody BrandPageDto brandPageDto) {
        return ApiResponse.result(brandService.queryBrandPageByCondition(brandPageDto));
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询 品牌列表")
    public ApiResponse<List<BrandListVo>> queryBrandListByCondition(@RequestBody BrandListDto brandListDto) {
        return ApiResponse.result(brandService.queryBrandListByCondition(brandListDto));
    }

    @PostMapping("/list/by/code")
    @ApiOperation(value = "查询 根据品牌ids查询品牌列表")
    public ApiResponse<List<BrandListVo>> queryBrandListByCodes(@RequestBody List<Long> brandCodes) {
        return ApiResponse.result(brandService.queryBrandListByCodes(brandCodes));
    }

    @PostMapping("/list/brandName/by/code")
    @ApiOperation(value = "查询 根据品牌ids查询品牌名称列表")
    public ApiResponse<List<String>> queryBrandNameListByCodes(@RequestBody List<Long> brandCodes) {
        return ApiResponse.result(brandService.queryBrandNameListByCodes(brandCodes));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 品牌信息")
    public ApiResponse<Boolean> saveBrand(@RequestBody @Validated BrandAddDto brandAddDto) {
        return ApiResponse.result(brandService.saveBrand(brandAddDto));
    }

    @DeleteMapping("/remove")
    @ApiOperation(value = "删除 根据ids删除品牌信息")
    public ApiResponse<Boolean> removeBrand(@RequestBody BrandDelDto brandCodes) {
        return ApiResponse.result(brandService.removeBrand(brandCodes));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 品牌信息")
    public ApiResponse<Boolean> modifyBrand(@RequestBody @Validated BrandEditDto brandEditDto) {
        return ApiResponse.result(brandService.modifyBrand(brandEditDto));
    }

    @PostMapping("/modify/showed")
    @ApiOperation(value = "修改 根据品牌id和状态修改品牌显示状态")
    public ApiResponse<Boolean> modifyBrandShowed(@RequestBody @Validated BrandShowedDto brandShowedDto) {
        return ApiResponse.result(brandService.modifyBrandShowed(brandShowedDto));
    }

    @GetMapping("/modify/{brandCode}/{enabled}")
    @ApiOperation(value = "修改 根据品牌id和状态修改品牌可用状态")
    public ApiResponse<Boolean> modifyBrandEnabled(@PathVariable("brandCode") Long brandCode, @PathVariable("enabled") Integer enabled) {
        return ApiResponse.result(brandService.modifyBrandEnabled(brandCode, enabled));
    }

    @GetMapping("/info/{brandCode}")
    @ApiOperation(value = "查询 根据品牌id查询品牌信息")
    public ApiResponse<BrandInfoVo> findBrandByCode(@PathVariable("brandCode") Long brandCode) {
        return ApiResponse.result(brandService.findBrandByCode(brandCode));
    }

}
