package com.abi.tmall.ware.server.controller.console;

import com.abi.base.foundation.page.PageResponse;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.ware.common.request.ware.*;
import com.abi.tmall.ware.common.response.ware.WarePageVo;
import com.abi.tmall.ware.dao.entity.Ware;
import com.abi.tmall.ware.server.service.WareService;
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
 * @ClassName: WareController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description: 仓库信息
 */
@Api(tags = "仓库信息")
@Slf4j
@RestController
@RequestMapping("/console/ware")
public class WareController {

    @Autowired
    WareService wareService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 仓库分页")
    public ApiResponse<PageResponse<WarePageVo>> queryWarePageByCondition(@RequestBody WarePageDto warePageDto) {
        return ApiResponse.result(wareService.queryWarePageByCondition(warePageDto));
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询 仓库列表")
    public ApiResponse<List<Ware>> queryWareListByCondition(@RequestBody WareListDto wareListDto) {
        return ApiResponse.result(wareService.queryWareListByCondition(wareListDto));
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增 仓库信息")
    public ApiResponse<Boolean> saveWare(@RequestBody @Validated WareAddDto wareAddDto) {
        return ApiResponse.result(wareService.saveWare(wareAddDto));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除 仓库信息")
    public ApiResponse<Boolean> removeWare(@RequestBody @Validated WareDelDto wareDelDto) {
        return ApiResponse.result(wareService.removeWare(wareDelDto));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 仓库信息")
    public ApiResponse<Boolean> modifyWare(@RequestBody @Validated WareEditDto wareEditDto) {
        return ApiResponse.result(wareService.modifyWare(wareEditDto));
    }

    @PostMapping("/info")
    @ApiOperation(value = "查询 仓库信息")
    public ApiResponse<Ware> findWareByCode(@RequestBody @Validated WareInfoDto wareInfoDto) {
        return ApiResponse.result(wareService.findWareByCode(wareInfoDto));
    }

}
