package com.mvp.wangll.mvptest.demo.list.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class HttpResponse {
    private static int successCode  = 200;
    @SerializedName("msg")
    private String Msg;
    @SerializedName("retCode")
    private int code;
    @SerializedName("result")
    private Object result;


    public String toString(){
        String response = "[http response]" + "{\"code\": " + code + ",\"msg\":" + Msg + ",\"result\":" + new Gson().toJson(result) + "}";
        return response;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isSuccess(){
        return code==successCode?true:false;
    }
}
