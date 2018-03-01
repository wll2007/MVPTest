package com.mvp.wangll.mvptest.Mvpframework.support.RxLifecycle;

import com.mvp.wangll.mvptest.Mvpframework.base.BaseModel;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpCallback;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 *  @作者 wll
 *  @日期 2017/12/8 0008
 *  @desc RXjava 相关的 M层基类
 */

public class RxLifecycleModel<T> extends BaseModel {
    protected T mEvent;
    protected LifecycleProvider<T> mLifecycleProvider;

    public RxLifecycleModel(/*MvpCallback<D> callback,*/ T mEvent, LifecycleProvider<T> mLifecycleProvider) {
//        super(callback);
        this.mEvent = mEvent;
        this.mLifecycleProvider = mLifecycleProvider;
    }
}
