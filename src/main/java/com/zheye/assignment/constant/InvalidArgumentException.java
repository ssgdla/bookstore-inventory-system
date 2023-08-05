package com.zheye.assignment.constant;

public class InvalidArgumentException extends BusinessException {

    public InvalidArgumentException(String msg) {
        super(ResultCode.INVALID_PARAMETER.getCode(), msg);
    }

}
