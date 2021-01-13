package com.bobo.demo.knife4j.controller.enumclass;

public enum ResultCode {

    SUCCESS(0,"成功"),
    //1001-1999 参数问题
    PARAM_IS_BLANK(1001,"参数为空"),
    PARAM_TYPE_ERROR(1002,"参数类型错误"),

    //2001-2999 用户问题
    USER_NOT_LOGIN(2001,"用户未登录"),
    USER_LOGIN_ERROR(2002,"用户名或密码错误"),
    USER_NOT_EXIT(2003,"用户不存在"),
    USER_IS_EXIT(2004,"用户已存在");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
