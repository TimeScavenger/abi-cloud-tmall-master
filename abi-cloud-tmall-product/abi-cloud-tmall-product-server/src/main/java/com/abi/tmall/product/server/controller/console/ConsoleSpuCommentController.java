package com.abi.tmall.product.server.controller.console;

import com.abi.tmall.product.server.service.SpuCommentService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SpuCommentController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: Spu商品评价
 */
@Api(tags = "Spu商品评价模块")
@Slf4j
@RestController
@RequestMapping("/console/spu-comment")
public class ConsoleSpuCommentController {

    @Autowired
    private SpuCommentService spuCommentService;

}
