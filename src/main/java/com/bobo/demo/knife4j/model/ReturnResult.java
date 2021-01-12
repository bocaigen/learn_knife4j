package com.bobo.demo.knife4j.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


@ApiModel(description = "返回接口类")
public class ReturnResult implements Serializable {
    @ApiModelProperty(value = "是否成功")
    private boolean success = true;
    @ApiModelProperty(value = "返回对象")
    private Object data;
    @ApiModelProperty(value = "标识码")
    private Integer code = 1;
    @ApiModelProperty(value = "提示信息")
    private String message = "成功";


    public ReturnResult(Object data, Integer code, String message) {
        super();
        this.data = data;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
                "success=" + success +
                ", data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}







