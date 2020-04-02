package com.formtools.enums;


/**
 * @author NaNRailgun
 * 错误信息枚举类
 */
public enum ErrorMsg {

    ACCOUNT_EXIT("用户已存在"),
    EMAIL_SEND_ERROR("邮件发送失败 请重试"),
    PARAM_ERROR("参数错误"),
    SYSTEM_ERROR("系统错误"),
    REGISTER_ERROR("注册失败"),
    FILE_TYPE_ERROR("文件类型错误 请选择.jpg或.png"),
    FILE_UPLOAD_ERROR("文件上传失败"),
    FILE_SIZE_ERROR("文件过大"),
    OPERAT_FREQUENCY("操作频繁 稍后重试"),
    MISSING_PARAMETER("缺少参数"),
    COOKIE_ERROR("请重新登录"),
    EMAIL_LOGIN_ERROR("登录失败 账号密码错误"),
    JSON_READ_ERROR("json参数解析错误");

    private String msg;

    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
