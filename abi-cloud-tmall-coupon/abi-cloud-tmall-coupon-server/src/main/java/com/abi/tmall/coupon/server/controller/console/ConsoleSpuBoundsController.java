package com.abi.tmall.coupon.server.controller.console;

import com.abi.infrastructure.core.response.ApiResponse;
import com.abi.tmall.coupon.dao.entity.SpuBounds;
import com.abi.tmall.coupon.server.service.SpuBoundsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品Spu积分设置 Console模块
 *
 * @ClassName: ConsoleSpuBoundsController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description:
 */
@Api(tags = "商品Spu积分设置")
@Slf4j
@RestController
@RequestMapping("/console/spu-bounds")
public class ConsoleSpuBoundsController {

    @Autowired
    private SpuBoundsService spuBoundsService;

    /**
     * 添加 积分信息
     *
     * @param spuBounds 积分信息
     * @return 添加是否成功: true-成功, false-失败
     */
    @PostMapping("/save")
    public ApiResponse<Boolean> saveSpuBounds(@RequestBody SpuBounds spuBounds) {
        return ApiResponse.result(spuBoundsService.save(spuBounds));
    }

}
