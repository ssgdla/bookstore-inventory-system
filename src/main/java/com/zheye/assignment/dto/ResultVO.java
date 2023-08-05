package com.zheye.assignment.dto;

import com.zheye.assignment.constant.ResultCode;
import lombok.Getter;

@Getter
public class ResultVO<T> {

    private int code;

    private String msg;

    private T data;

    public ResultVO(T data) {
        this(ResultCode.SUCCESS, data);
    }

    public ResultVO(ResultCode resultCode) {
        this(resultCode, null);
    }

    public ResultVO(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public ResultVO(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
