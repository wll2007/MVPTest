package com.mvp.wangll.mvptest.demo.http;

import com.google.gson.Gson;
import com.mvp.wangll.mvptest.Httpframework.exception.ServerException;
import com.mvp.wangll.mvptest.demo.list.bean.HttpResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc  Http请求结果转换类
 */
public class ServerResultFunction implements Function<HttpResponse,Object> {
    private static ServerResultFunction mInstance;

    public static ServerResultFunction getInstance(){
        if(mInstance==null){
            synchronized (ServerResultFunction.class){
                if(mInstance==null){
                    mInstance = new ServerResultFunction();
                }
            }
        }
        return mInstance;
    }

    @Override
    public Object apply(@NonNull HttpResponse o) throws Exception {
        if(!o.isSuccess()){
            throw new ServerException(o.getCode(),o.getMsg());
        }
        return new Gson().toJson(o.getResult());
    }
}
