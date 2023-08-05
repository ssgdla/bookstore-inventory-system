package com.zheye.assignment.constant;
public enum ResultCode {
    SUCCESS(0, "SUCCESS"),
    INTERNAL_ERROR(500, "Internal Error"),
    INVALID_PARAMETER(20001, "Invalid parameter"),
    BOOK_NOT_FOUND(20002, "Book not found"),
    USER_EXIST(30001, "Username already exist, please try another one"),
    PASSWORD_ERROR(30002, "Wrong password, please try another one"),
    INVALID_TOKEN(30003, "Invalid token in header"),
    TOKEN_EXPIRE(30004, "Token has expired"),
    AUTHORITY_ERROR(30005, "authority error");

    private int code;

    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
