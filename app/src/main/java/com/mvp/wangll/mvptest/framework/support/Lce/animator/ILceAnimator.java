package com.mvp.wangll.mvptest.framework.support.Lce.animator;

import android.view.View;

/**
 * Created by Administrator on 2017/10/21 0021.
 */
//加载,内容,加载失败界面的显示动画的接口
public interface ILceAnimator {

    void showLoadingView(View loadView,View contentView,View errorView);

    void showContentView(View loadView,View contentView,View errorView);

    void showErrorView(View loadView,View contentView,View errorView);

}
