package com.mvp.wangll.mvptest.framework.base;

/**
 * Created by Administrator on 2017/9/26 0026.
 */

public interface PresentFactory<T extends MvpPresenter> {
    T create();
}
