package com.mvp.wangll.mvptest.Httpframework.exception;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class ApiException extends Throwable {
    private int code;
    private String msg;

    public ApiException(Throwable e, int code, String msg) {
        super(e);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ApiException-----"+"code:"+code+"  "+"msg:"+msg;
    }
}
