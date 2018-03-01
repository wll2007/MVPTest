package com.mvp.wangll.mvptest.demo.list;

import android.view.View;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.animator.DefaultLceAnimator;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class LoadLcAnimator extends DefaultLceAnimator {
    private LoadingView loadingView;

    @Override
    public void showLoadingView(View loadView, View contentView, View errorView) {
        super.showLoadingView(loadView, contentView, errorView);
        loadingView = (LoadingView) loadView.findViewById(R.id.lv_loading);
        loadingView.openAnimation();
    }

    @Override
    public void showContentView(View loadView, View contentView, View errorView) {
        if(loadingView != null){
            loadingView.closeAnimation();
        }
        super.showContentView(loadView, contentView, errorView);
    }

    @Override
    public void showErrorView(View loadView, View contentView, View errorView) {
        if(loadingView != null){
            loadingView.closeAnimation();
        }
        super.showErrorView(loadView, contentView, errorView);
    }
}
