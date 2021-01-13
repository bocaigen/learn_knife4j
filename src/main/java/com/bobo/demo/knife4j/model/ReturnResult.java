package com.bobo.demo.knife4j.model;

import com.bobo.demo.knife4j.controller.enumclass.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(description = "返回接口类")
public class ReturnResult implements Serializable {

    @ApiModelProperty(value = "返回对象")
    private Object data;
    @ApiModelProperty(value = "标识码")
    private Integer code = 1;
    @ApiModelProperty(value = "提示信息")
    private String message = "成功";

    public ReturnResult(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public ReturnResult(ResultCode resultCode,Object data) {
        this.code = resultCode.code();
        this.message = resultCode.message();
        this.data = data;
    }

    public static ReturnResult success(){
        return new ReturnResult(ResultCode.SUCCESS);
    }

    public static ReturnResult success(Object data){
        return new ReturnResult(ResultCode.SUCCESS,data);
    }

    public static ReturnResult fail(ResultCode resultCode){
        return new ReturnResult(resultCode);
    }

    public static ReturnResult fail(ResultCode resultCode,Object data){
        return new ReturnResult(resultCode,data);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnResult{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}







