package com.mvp.wangll.mvptest.framework.base;

import com.mvp.wangll.mvptest.framework.base.MvpView;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public interface MvpPresenter<V extends MvpView> {
    void attachView(V view);
    void detachView();
}
