package com.abi.tmall.ware.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.infrastructure.dao.page.PageResponse;
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
 * 仓库 Console模块
 *
 * @ClassName: ConsoleWareController
 * @Author: illidan
 * @CreateDate: 2021/11/18
 * @Description:
 */
@Api(tags = "仓库 Console模块")
@Slf4j
@RestController
@RequestMapping("/console/ware")
public class ConsoleWareController {

    @Autowired
    WareService wareService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 仓库分页")
    public ApiResponse<PageResponse<WarePageVo>> queryWarePageByCondition(@RequestBody WarePageReq warePageReq) {
        return ApiResponse.result(wareService.queryWarePageByCondition(warePageReq));
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询 仓库列表")
    public ApiResponse<List<Ware>> queryWareListByCondition(@RequestBody WareListReq wareListReq) {
        return ApiResponse.result(wareService.queryWareListByCondition(wareListReq));
    }

    @PostMapping("/save")
    @ApiOperation(value = "新增 仓库信息")
    public ApiResponse<Boolean> saveWare(@RequestBody @Validated WareAddReq wareAddReq) {
        return ApiResponse.result(wareService.saveWare(wareAddReq));
    }

    @PostMapping("/remove")
    @ApiOperation(value = "删除 仓库信息")
    public ApiResponse<Boolean> removeWare(@RequestBody @Validated WareDelReq wareDelReq) {
        return ApiResponse.result(wareService.removeWare(wareDelReq));
    }

    @PostMapping("/modify")
    @ApiOperation(value = "修改 仓库信息")
    public ApiResponse<Boolean> modifyWare(@RequestBody @Validated WareEditReq wareEditReq) {
        return ApiResponse.result(wareService.modifyWare(wareEditReq));
    }

    @PostMapping("/info")
    @ApiOperation(value = "查询 仓库信息")
    public ApiResponse<Ware> findWareByCode(@RequestBody @Validated WareInfoReq wareInfoReq) {
        return ApiResponse.result(wareService.findWareByCode(wareInfoReq));
    }

}
