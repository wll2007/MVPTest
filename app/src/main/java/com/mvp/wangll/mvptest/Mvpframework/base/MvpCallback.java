package com.mvp.wangll.mvptest.Mvpframework.base;

/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc P层与M层的回调接口
 */
public interface MvpCallback<D> {
    void  CallbackError(Throwable e);
    void  CallbackSuccess(D o);
}
