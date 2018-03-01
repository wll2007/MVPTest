package com.mvp.wangll.mvptest.Httpframework.exception;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class ServerException extends RuntimeException {
    private int code;
    private String msg;

    public ServerException(int code, String msg) {
        super(msg);
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
