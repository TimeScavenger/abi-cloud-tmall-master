package com.abi.tmall.auth.api.util;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.abi.base.foundation.utils.JacksonUtils;
import com.abi.tmall.auth.common.constant.SessionConstant;
import com.abi.tmall.auth.common.response.member.MemberInfoVoResp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: MemberAuthUtils
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 会员工具类
 */
public class MemberAuthUtils {

    // 获取HttpServletRequest对象
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    // 获取request header信息
    public static String getHeader(String name) {
        try {
            return MemberAuthUtils.getRequest().getHeader(name);
        } catch (Exception ex) {  // 非请求线程获取不到HttpServletRequest
            return "";
        }
    }

    /**
     * 获取登录会员的Code
     *
     * @return
     */
    public static String getMemberLoginCode() {
        return MemberAuthUtils.getHeader("memberLoginCode");
    }

    /**
     * 从Header中获取会员信息
     *
     * @return
     */
    public static MemberInfoVoResp getMemberInfo() {
        String loginMemberCode = MemberAuthUtils.getHeader("memberLoginCode");
        return getMemberInfo(loginMemberCode);
    }

    /**
     * 获取会员信息（根据会员Code获取）
     *
     * @param loginMemberCode
     * @return
     */
    public static MemberInfoVoResp getMemberInfo(String loginMemberCode) {
        SaSession sessionByLoginId = StpUtil.getSessionByLoginId(loginMemberCode);
        MemberInfoVoResp mpMemberInfo = (MemberInfoVoResp) sessionByLoginId.getDataMap().get(SessionConstant.SATOKEN_LOGIN_SESSION_MEMBER_INFO);
        return mpMemberInfo;
    }

    /**
     * 从Header中获取角色信息
     *
     * @return
     */
    public static List<String> getRoles() {
        String roleIds = MemberAuthUtils.getHeader("memberRoleCodes");
        if (StringUtils.isNotBlank(roleIds)) {
            return JacksonUtils.toList(roleIds, String.class);
        }
        return Collections.emptyList();
    }

    /**
     * 获取角色信息（根据会员Code获取）
     *
     * @param loginMemberCode
     * @return
     */
    public static List<String> getRoles(String loginMemberCode) {
        SaSession sessionByLoginId = StpUtil.getSessionByLoginId(loginMemberCode);
        List<String> roleList = (List<String>) sessionByLoginId.getDataMap().get(SessionConstant.SATOKEN_LOGIN_SESSION_MEMBER_ROLE_LIST);
        return roleList;
    }

}
