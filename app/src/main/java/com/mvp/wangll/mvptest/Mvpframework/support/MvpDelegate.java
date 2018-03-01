package com.mvp.wangll.mvptest.Mvpframework.support;

import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
//V层与P层绑定、解绑需要的代理接口
public interface MvpDelegate<V extends MvpView,P extends MvpPresenter> {
//    P CreatePresenter();
    V CreateView();
    P GetPresenter();
    V GetView();
    void SetPresenter(P presenter);
    void SetView(V view);
}
