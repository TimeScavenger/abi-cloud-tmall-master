package com.abi.tmall.auth.server.controller;

import com.abi.tmall.auth.server.service.LoginCaptchaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName: LoginCaptchaController
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录验证码 控制器
 */
@Api(tags = "登录验证码")
@Slf4j
@RestController
@RequestMapping("/login-captcha")
public class LoginCaptchaController {

    @Autowired
    private LoginCaptchaService loginCaptchaService;

    /**
     * 生成 图片验证码
     *
     * @param response
     * @param uuid
     * @throws IOException
     */
    @GetMapping("/captcha.jpg")
    @ApiModelProperty(value = "获取 图片验证码")
    public void generateImgCaptcha(HttpServletResponse response, String uuid) throws IOException {
        // 获取图片验证码
        BufferedImage bufferedImage = loginCaptchaService.generateImgCaptcha(uuid);
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(bufferedImage, "jpg", outputStream);
        IOUtils.closeQuietly(outputStream);
    }
}
