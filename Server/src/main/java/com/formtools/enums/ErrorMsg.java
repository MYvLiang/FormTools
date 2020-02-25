package com.formtools.enums;


/**
 * @author NaNRailgun
 * 错误信息枚举类
 */
public enum ErrorMsg {

    ACCOUNT_EXIT("用户已存在"),
    EMAIL_SEND_ERROR("邮件发送失败 请重试"),
    PARAM_ERROR("参数错误"),
    OPERAT_FREQUENCY("操作频繁 稍后重试");

    private String msg;

    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
