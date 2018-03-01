package com.mvp.wangll.mvptest.Mvpframework.support.Lce;

import android.view.View;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.animator.ILceAnimator;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
//MvpLceFragment的目标类
public class MvpLceViewImpl implements MvpLceView {

    private ILceAnimator animator;
    private View loadView;
    private View errorView;
    private View contentView;

    public void initView(View view) {
        if (view == null){
            throw new NullPointerException("rootView不能够为空");
        }
        if (this.loadView == null){
            this.loadView = view.findViewById(R.id.loadingView);
        }
        if (this.contentView == null){
            this.contentView = view.findViewById(R.id.contentView);
        }
        if (this.errorView == null){
            this.errorView = view.findViewById(R.id.errorView);
        }
        if (loadView == null){
            throw new NullPointerException("loadingView不能够为空");
        }
        if (contentView == null){
            throw new NullPointerException("contentView不能够为空");
        }
        if (errorView == null){
            throw new NullPointerException("errorView不能够为空");
        }
    }

    public void setLceAnimator(ILceAnimator animator ){
        this.animator = animator;
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        if(!isPullRefresh) {
            animator.showLoadingView(loadView, contentView, errorView);
        }
    }

    @Override
    public void showContent(boolean isPullRefresh) {
        if(!isPullRefresh) {
            animator.showContentView(loadView, contentView, errorView);
        }
    }

    @Override
    public void showError(boolean isPullRefresh) {
        if(!isPullRefresh) {
            animator.showErrorView(loadView, contentView, errorView);
        }
    }

    @Override
    public void loadData(boolean isPullRefresh) {

    }
}
