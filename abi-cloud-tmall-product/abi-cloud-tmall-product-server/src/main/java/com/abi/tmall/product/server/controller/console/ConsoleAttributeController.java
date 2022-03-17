package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.attribute.*;
import com.abi.tmall.product.common.response.attribute.AttributeInfoVo;
import com.abi.tmall.product.common.response.attribute.AttributePageVo;
import com.abi.tmall.product.common.response.attribute.BaseAttributeListVo;
import com.abi.tmall.product.common.response.attribute.SaleAttributeListVo;
import com.abi.tmall.product.server.service.AttributeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName: AttributeController
 * @Author: illidan
 * @CreateDate: 2021/5/20
 * @Description: 商品属性
 */
@Api(tags = "商品属性模块")
@Slf4j
@RestController
@RequestMapping("/console/attribute")
public class ConsoleAttributeController {

    @Autowired
    private AttributeService attributeService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 属性分页列表")
    public ApiResponse<PageResponse<AttributePageVo>> queryAttributePageByCondition(@RequestBody AttributePageDto attributePageDto) {
        return ApiResponse.result(attributeService.queryAttributePageByCondition(attributePageDto));
    }

    @PostMapping("/list/base")
    @ApiOperation(value = "查询 根据分类id查询基本属性的信息")
    public ApiResponse<List<BaseAttributeListVo>> queryBaseAttributeListByCategoryCode(@RequestBody @Validated AttributeListDto attributeListDto) {
        return ApiResponse.result(attributeService.queryBaseAttributeListByCategoryCode(attributeListDto));
    }

    @PostMapping("/list/sale")
    @ApiOperation(value = "查询 根据分类id查询销售属性的信息")
    public ApiResponse<List<SaleAttributeListVo>> querySaleAttributeListByCategoryCode(@RequestBody @Validated AttributeListDto attributeListDto) {
        return ApiResponse.result(attributeService.querySaleAttributeListByCategoryCode(attributeListDto));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 属性信息")
    public ApiResponse<Boolean> saveAttribute(@RequestBody @Validated AttributeAddDto attributeAddDto) {
        return ApiResponse.result(attributeService.saveAttribute(attributeAddDto));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除 根据ids删除属性信息")
    public ApiResponse<Boolean> removeAttribute(@RequestBody AttributeDelDto attributeDelDto) {
        return ApiResponse.result(attributeService.removeAttribute(attributeDelDto));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 属性信息")
    public ApiResponse<Boolean> modifyAttribute(@RequestBody @Validated AttributeEditDto attributeEditDto) {
        return ApiResponse.result(attributeService.modifyAttribute(attributeEditDto));
    }

    @GetMapping("/find/{attributeCode}")
    @ApiOperation(value = "查询 根据属性id查询属性的信息")
    public ApiResponse<AttributeInfoVo> findAttributeByCode(@PathVariable("attributeCode") Long attributeCode) {
        return ApiResponse.result(attributeService.findAttributeByCode(attributeCode));
    }

}
