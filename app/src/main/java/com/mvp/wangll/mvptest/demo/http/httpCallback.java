package com.mvp.wangll.mvptest.demo.http;

import android.util.Log;

import com.google.gson.Gson;
import com.mvp.wangll.mvptest.Httpframework.exception.ApiException;
import com.mvp.wangll.mvptest.Httpframework.observer.HttpRxObserver;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpCallback;
import com.mvp.wangll.mvptest.demo.list.bean.HttpResponse;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;

import static com.mvp.wangll.mvptest.Httpframework.exception.ExceptionEngine.DATA_TYPE_ERROR;
import static com.mvp.wangll.mvptest.Httpframework.exception.ExceptionEngine.UN_KNOWN_ERROR;
public  abstract class httpCallback<D> extends HttpRxObserver implements MvpCallback<D> {

    @Override
    public void error(ApiException ex) {
        CallbackError(ex);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void success(Object o) {
        Type type =getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)type).getActualTypeArguments();

        if(o instanceof HttpResponse && ((Class<D>)params[0]).getName().equals(HttpResponse.class.getName())){
            CallbackSuccess(new Gson().fromJson(o.toString(),(Class<D>)params[0]));
            return;
        }

        if(o instanceof ResponseBody && ((Class<D>)params[0]).getName().equals(ResponseBody.class.getName())){
            CallbackSuccess(((Class<D>)params[0]).cast(o));
            return;
        }


        if(!(o instanceof ResponseBody || o instanceof HttpResponse)
                && !(((Class<D>)params[0]).getName().equals(ResponseBody.class.getName())||((Class<D>)params[0]).getName().equals(HttpResponse.class.getName())) ){
            CallbackSuccess(new Gson().fromJson(o.toString(),(Class<D>)params[0]));
            return;
        }

        onError(new ApiException(new Throwable(),DATA_TYPE_ERROR,"网络返回的数据类型与期望的数据不匹配，也无法进行Gson转换！"));
    }

    @Override
    public void CallbackError(Throwable e) {
        ApiException exception;
        if(e instanceof ApiException){
            OnError((ApiException)e);
        }else {
            exception = new ApiException(e,UN_KNOWN_ERROR,"未知错误!");
            OnError(exception);
        }
    }

    public abstract void OnError(ApiException ex);
}
