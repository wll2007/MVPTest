package com.mvp.wangll.mvptest.Mvpframework.support.Lce.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.MvpLceView;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.MvpLceViewImpl;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.animator.ILceAnimator;
import com.mvp.wangll.mvptest.Mvpframework.support.activity.MvpActivity;
import com.mvp.wangll.mvptest.Mvpframework.support.RxLifecycle.MvpRxLifecycle;
import com.trello.rxlifecycle2.LifecycleProvider;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public  class MvpLceActivity<V extends MvpView,P extends MvpPresenter<V>,E> extends MvpActivity<V,P> implements MvpRxLifecycle<E> {

    private MvpLceViewImpl mMvpLceViewImpl;  //静态代理

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         if(mMvpLceViewImpl == null){
            mMvpLceViewImpl = new MvpLceViewImpl();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMvpLceViewImpl.initView(getWindow().getDecorView());
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
        mMvpLceViewImpl.showLoading(isPullRefresh);
    }

    @Override
    public void showContent(boolean isPullRefresh) {
        mMvpLceViewImpl.showContent(isPullRefresh);
    }

    @Override
    public void showError(boolean isPullRefresh) {
        mMvpLceViewImpl.showError(isPullRefresh);
    }

    @Override
    public void loadData(boolean isPullRefresh) {
        mMvpLceViewImpl.loadData(isPullRefresh);
    }

    public void setLceAnimator(ILceAnimator animator){
        mMvpLceViewImpl.setLceAnimator(animator);
    }

    @Override
    public LifecycleProvider<E> getRxLifecycle() {
        return null;
    }

    @Override
    public E getRxEvent() {
        return null;
    }
}
