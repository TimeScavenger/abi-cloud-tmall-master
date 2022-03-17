package com.abi.tmall.product.server.controller.console;

import com.abi.tmall.product.server.service.SkuImgService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SkuImgController
 * @Author: illidan
 * @CreateDate: 2021/6/10
 * @Description: Sku图片
 */
@Api(tags = "Sku图片模块")
@Slf4j
@RestController
@RequestMapping("/console/sku-img")
public class ConsoleSkuImgController {

    @Autowired
    private SkuImgService skuImagesService;

}
