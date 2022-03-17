package com.abi.tmall.product.server.controller.console;

import com.abi.infrastructure.dao.page.PageResponse;
import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.product.common.request.spu.SpuAddDto;
import com.abi.tmall.product.common.request.spu.SpuPageDto;
import com.abi.tmall.product.common.request.spu.SpuUpDto;
import com.abi.tmall.product.common.response.spu.SpuPageVo;
import com.abi.tmall.product.server.service.SpuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SpuController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: Spu信息
 */
@Api(tags = "Spu信息模块")
@Slf4j
@RestController
@RequestMapping("/console/spu-info")
public class ConsoleSpuController {

    @Autowired
    private SpuService spuService;

    @PostMapping("/page")
    @ApiOperation(value = "查询 Spu分页")
    public ApiResponse<PageResponse<SpuPageVo>> querySpuPageByCondition(@RequestBody SpuPageDto spuPageDto) {
        return ApiResponse.result(spuService.querySpuPageByCondition(spuPageDto));
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加 Spu")
    public ApiResponse<Boolean> saveSpu(@RequestBody @Validated SpuAddDto spuAddDto) {
        return ApiResponse.result(spuService.saveSpu(spuAddDto));
    }

    @PostMapping("/up")
    @ApiOperation(value = "上架 Spu")
    public ApiResponse<Boolean> upSpu(@RequestBody @Validated SpuUpDto spuUpDto) {
        return ApiResponse.result(spuService.upSpu(spuUpDto));
    }

}
