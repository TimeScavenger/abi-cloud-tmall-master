package com.abi.tmall.auth.common.constant;

/**
 * @ClassName: AuthErrorCode
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: Auth服务通用错误码
 */
public enum AuthErrorCode {

    PARAM_ERROR(400000, "请求参数错误"),
    CHECK_PASSWORD_FAIL(400001, "密码输入不正确"),
    PASSWORD_NOT_EQUAL(400002, "两次密码输入不一致"),
    CHECK_VERIFICATIONCODERE_FAIL(400003, "短信验证码校验失败"),
    CHECK_WECHAT_LOGIN_FAIL(400004, "微信小程序登录校验失败"),
    CHECK_QY_WECHAT_LOGIN_FAIL(400005, "企业微信小程序登录校验失败"),
    CHECK_DINGDING_LOGIN_FAIL(400006, "钉钉登录校验失败"),
    QUERY_USER_NO_EXISTS(400007, "账号不存在"),
    QUERY_USER_THIRDPART_FAIL(400008, "第三方账号无效"),
    QUERY_USER_FAIL(400009, "账号已停用");

    private int code;
    private String message;

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    AuthErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
