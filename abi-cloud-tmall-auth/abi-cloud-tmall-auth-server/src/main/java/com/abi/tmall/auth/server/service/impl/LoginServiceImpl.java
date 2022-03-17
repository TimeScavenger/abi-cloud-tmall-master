package com.abi.tmall.auth.server.service.impl;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.exception.BusinessException;
import com.abi.base.foundation.response.ApiResponse;
import com.abi.tmall.auth.common.constant.AuthErrorCode;
import com.abi.tmall.auth.common.request.login.*;
import com.abi.tmall.auth.common.response.login.LoginResult;
import com.abi.tmall.auth.dao.entity.Member;
import com.abi.tmall.auth.dao.service.MemberDao;
import com.abi.tmall.auth.server.enums.MemberStatusEnum;
import com.abi.tmall.auth.server.service.LoginCaptchaService;
import com.abi.tmall.auth.server.service.LoginService;
import com.abi.tmall.auth.server.service.LoginTokenService;
import com.abi.tmall.communication.api.client.SmsCommunicationApi;
import com.abi.tmall.communication.common.request.sms.CheckSmsVerificationCodeReq;
import com.abi.tmall.communication.common.response.verification.CheckVerificationCodeRes;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @ClassName: LoginServiceImpl
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 登录 服务实现类
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginCaptchaService loginCaptchaService;

    @Autowired
    private LoginTokenService loginTokenService;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private SmsCommunicationApi smsCommunicationApi;

    @Value("${login.sms.sign-name:百威中国}")
    private String signName;

    /**
     * 账号密码验证码登录系统
     *
     * @param req
     * @return
     * @throws BusinessException
     */
    @Override
    public String loginByPwdWithCap(LoginByPwdWithCapReq req) throws BusinessException {
        // 1、校验验证码
        boolean bool = loginCaptchaService.checkImgCaptcha(req.getUuid(), req.getCaptcha());
        if (!bool) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), "校验验证码失败");
        }

        // 2、校验用户账号密码
        Member member = memberDao.queryInfoByUsername(req.getUsername());
        // 账号不存在、密码错误
        if (Objects.isNull(member) || !Objects.equals(member.getPassword(), new Sha256Hash(req.getPassword(), member.getSalt()).toHex())) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), "账号不存在、密码错误");
        }
        // 账号锁定，无法登录
        if (MemberStatusEnum.LOCK.getCode().equals(member.getStatus())) {
            throw new BusinessException(ResultCode.SERVER_ERROR.code(), "账号已被锁定，请联系管理员");
        }

        // 3、生成token，并保存到数据库
        return loginTokenService.createToken(member.getMemberCode());
    }

    /**
     * 账号密码登录系统
     *
     * @param req
     * @return
     */
    @Override
    public LoginResult loginByPwd(LoginByPwdReq req) {
        // 1、校验用户账号密码
        Member member = memberDao.queryInfoByUsername(req.getUsername());
        // 账号不存在、密码错误
        if (Objects.isNull(member) || !Objects.equals(member.getPassword(), new Sha256Hash(req.getPassword(), member.getSalt()).toHex())) {
            throw new BusinessException(ResultCode.PARAM_IS_ERROR.code(), "账号不存在、密码错误");
        }
        // 账号锁定，无法登录
        if (MemberStatusEnum.LOCK.getCode().equals(member.getStatus())) {
            throw new BusinessException(ResultCode.SERVER_ERROR.code(), "账号已被锁定，请联系管理员");
        }
        // 2、组装token
        return loginTokenService.buildLoginResult(member);
    }

    /**
     * 手机号验证码登录
     *
     * @param req
     * @return
     */
    @Override
    public LoginResult loginBySms(LoginBySmsReq req) {
        // 1、校验手机验证码是否匹配
        checkSmsCode(req);

        // 2、保存会话并返回token
        Member member = memberDao.queryInfoByPhone(req.getPhone(), MemberStatusEnum.NORMAL.getCode());
        if (Objects.isNull(member)) {
            throw new BusinessException(AuthErrorCode.QUERY_USER_FAIL.code(), AuthErrorCode.QUERY_USER_FAIL.message());
        }

        return loginTokenService.buildLoginResult(member);
    }

    /**
     * 微信登录
     *
     * @param req
     * @return
     */
    @Override
    public LoginResult loginByWechat(LoginByWechatReq req) {
        return null;
    }

    /**
     * 钉钉登录
     *
     * @param req
     * @return
     */
    @Override
    public LoginResult loginByDingtalk(LoginByDingtalkReq req) {
        return null;
    }

    /**
     * 对密码进行MD5加密
     *
     * @param password
     * @return
     */
    private String generateMd5Pwd(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 对明文密码进行加密
        String encode = passwordEncoder.encode(password);
        return encode;
    }

    /**
     * 对MD5加密的密码进行验证
     *
     * @param password
     * @return
     */
    private boolean checkPassword(String password) {
        // 假设是数据库中的密文密码
        String encodeDb = "25f9e794323b453885f5181f1b624d0b";

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 密码进行匹配校验
        boolean bool = passwordEncoder.matches(password, encodeDb); // 前面明文密码，后面加密密码，可直接进行密码比较
        return bool;
    }

    /**
     * 验证码登录 校验短信验证码
     *
     * @param req
     */
    private void checkSmsCode(LoginBySmsReq req) {
        // 1、初始化入参
        CheckSmsVerificationCodeReq checkSmsVerificationCodeReq = new CheckSmsVerificationCodeReq();
        // 2、接收者手机号
        checkSmsVerificationCodeReq.setPhoneNumbers(req.getPhone());
        // 3、验证码
        checkSmsVerificationCodeReq.setVerificationCode(req.getAuthCode());
        // 4、短信签名名称
        checkSmsVerificationCodeReq.setSignName(signName);
        // 5、调用接口，返回校验结果（ture：成功，false：失败）
        ApiResponse<CheckVerificationCodeRes> checkVerificationCodeResApiResponse = smsCommunicationApi.checkSmsVerificationCode(checkSmsVerificationCodeReq);
        boolean checkStatus = checkVerificationCodeResApiResponse.getData().isCheckStatus();
        if (!checkStatus) {
            throw new BusinessException(AuthErrorCode.CHECK_VERIFICATIONCODERE_FAIL.code(), AuthErrorCode.CHECK_VERIFICATIONCODERE_FAIL.message());
        }
    }

}
