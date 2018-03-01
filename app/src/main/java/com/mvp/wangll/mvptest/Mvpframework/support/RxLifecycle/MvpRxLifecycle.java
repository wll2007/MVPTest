package com.mvp.wangll.mvptest.Mvpframework.support.RxLifecycle;

import com.mvp.wangll.mvptest.Mvpframework.support.Lce.MvpLceView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc 获取RxActivity的生命周期接口
 */
public interface MvpRxLifecycle<T> extends MvpLceView{
    LifecycleProvider<T> getRxLifecycle();
    T getRxEvent();
}
