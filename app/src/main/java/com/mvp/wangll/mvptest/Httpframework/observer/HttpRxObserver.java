package com.mvp.wangll.mvptest.Httpframework.observer;


import android.util.Log;

import com.mvp.wangll.mvptest.Httpframework.exception.ApiException;
import com.mvp.wangll.mvptest.Httpframework.exception.ExceptionEngine;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc Http请求的观察者抽象
 */
public abstract class HttpRxObserver<T> implements Observer<T> {


    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    @Override
    public void onNext(@NonNull T o) {
        success(o);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        ApiException ex  = null;
        if(e instanceof ApiException){
            ex = (ApiException) e;
        }else {
            ex = new ApiException(e, ExceptionEngine.UN_KNOWN_ERROR,e.getMessage());
        }
        error(ex);
    }

    @Override
    public void onComplete() {
        complete();
    }

    public abstract void error(ApiException ex);
    public abstract void success(T o);

    public abstract void complete();
}
