package com.zheye.assignment.constant;

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {

    private ResultCode resultCode;

    private String msg;

    private int code;

    public BusinessException(ResultCode resultCode) {
        this(resultCode.getCode(), resultCode.getMsg());
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

}
