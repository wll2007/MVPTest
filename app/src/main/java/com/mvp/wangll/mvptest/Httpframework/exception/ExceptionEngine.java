package com.mvp.wangll.mvptest.Httpframework.exception;

import com.google.gson.JsonParseException;
import com.google.gson.stream.MalformedJsonException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * Created by Administrator on 2017/11/14 0014.
 */

public class ExceptionEngine {
    public static final int UN_KNOWN_ERROR = 1000;//未知错误
    public static final int ANALYTIC_SERVER_DATA_ERROR = 1001;//解析(服务器)数据错误
    public static final int CONNECT_ERROR = 1003;//网络连接错误
    public static final int TIME_OUT_ERROR = 1004;//网络连接超时
    public static final int  DATA_TYPE_ERROR = 1005;//数据类型不匹配


    public static ApiException transform(Throwable throwable){
        ApiException exception = null;
        if(throwable instanceof HttpException){
            HttpException ex = (HttpException) throwable;
            exception = new ApiException(throwable,ex.code(),ex.message());
        }else if(throwable  instanceof ServerException){
            exception = new ApiException(throwable,((ServerException) throwable).getCode(),((ServerException) throwable).getMsg());
        }else if(throwable instanceof JSONException
                || throwable instanceof JsonParseException
                || throwable instanceof ParseException
                || throwable instanceof MalformedJsonException){
            exception = new ApiException(throwable,ANALYTIC_SERVER_DATA_ERROR,"解析数据错误!");
        }else if(throwable instanceof ConnectException){
            exception = new ApiException(throwable,CONNECT_ERROR,"网络连接错误!");
        }else if(throwable instanceof SocketTimeoutException){
            exception = new ApiException(throwable,TIME_OUT_ERROR,"网络连接超时!");
        }else {
            exception = new ApiException(throwable,UN_KNOWN_ERROR,"未知错误!");
        }
        return exception;
    }
}
