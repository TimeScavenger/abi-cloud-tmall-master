package com.abi.tmall.auth.server.enums;

import com.abi.base.foundation.code.ResultCode;
import com.abi.base.foundation.entity.BaseEnumEntity;
import com.abi.base.foundation.enums.EnabledEnum;
import com.abi.base.foundation.exception.BusinessException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: MemberStatusEnum
 * @Author: illidan
 * @CreateDate: 2022/2/26
 * @Description: 会员状态
 */
@Getter
@AllArgsConstructor
public enum MemberStatusEnum {

    NORMAL(0, "正常"),
    LOCK(1, "锁定");

    private static final Set<EnabledEnum> ALL = EnumSet.allOf(EnabledEnum.class);
    private final Integer code;
    private final String label;

    /**
     * 根据code获取到枚举的label
     *
     * @param code
     * @return
     */
    public static String getLabelByCode(Integer code) {
        return ALL.stream()
                .filter(enums -> enums.getCode().equals(code))
                .findFirst()
                .map(EnabledEnum::getLabel)
                .orElseThrow(() -> new BusinessException(ResultCode.ENUM_DATA_ERROR.code(), "无效状态枚举类: " + code + "未匹配到相关值！"));
    }

    /**
     * 根据code获取枚举对象
     *
     * @param code
     * @return
     */
    public static EnabledEnum getEnumByCode(Integer code) {
        return ALL.stream()
                .filter(enums -> enums.getCode().equals(code))
                .findAny()
                .orElseThrow(() -> new BusinessException(ResultCode.ENUM_DATA_ERROR.code(), "无效状态枚举类: " + code + "未匹配到相关值！"));
    }

    /**
     * 将枚举转换成list格式
     *
     * @return
     */
    public static List<BaseEnumEntity> toList() {
        return ALL.stream()
                .map(item -> {
                    BaseEnumEntity codeLabel = new BaseEnumEntity();
                    codeLabel.setCode(item.getCode());
                    codeLabel.setLabel(item.getLabel());
                    return codeLabel;
                })
                .collect(Collectors.toList());
    }

    /**
     * 将枚举转换成map格式
     *
     * @return
     */
    public static Map<Integer, String> toMap() {
        return ALL.stream()
                .collect(Collectors.toMap(EnabledEnum::getCode, EnabledEnum::getLabel));
    }

}