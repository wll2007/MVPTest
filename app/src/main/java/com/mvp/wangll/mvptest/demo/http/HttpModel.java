package com.mvp.wangll.mvptest.demo.http;

import com.mvp.wangll.mvptest.Mvpframework.support.RxLifecycle.RxLifecycleModel;
import com.trello.rxlifecycle2.LifecycleProvider;


/**
 * Created by Administrator on 2017/12/7 0007.
 */

public abstract class HttpModel<T> extends RxLifecycleModel<T> {
    public HttpModel(LifecycleProvider<T> mLifecycleProvider, T mEvent) {
        super(mEvent,mLifecycleProvider);
    }
}
