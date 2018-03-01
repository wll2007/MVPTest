package com.mvp.wangll.mvptest.Mvpframework.support.Lce.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.MvpLceInvocationHandler;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.MvpLceView;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.animator.ILceAnimator;
import com.mvp.wangll.mvptest.Mvpframework.support.RxLifecycle.MvpRxLifecycle;
import com.mvp.wangll.mvptest.Mvpframework.support.fragment.MvpFragment;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
//此类是处理Lce界面的,是框架的,公共的
//如果不用代理模式,万一Lce 界面显示要修改或者扩展,此类就必须修改,此类做作为框架是不允许轻易修改的
public class MvpLceFragment<V extends MvpView,P extends MvpPresenter<V>,T>  extends MvpFragment<V,P> implements MvpRxLifecycle<T> {

    //private MvpLceViewImpl<D> mMvpLceViewImpl;  //静态代理
    private MvpLceInvocationHandler  handler;     //动态代理
    private MvpLceView mvpLceViewImpl;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       /* if(mMvpLceViewImpl == null){
            mMvpLceViewImpl = new MvpLceViewImpl<D>();
        }
        mMvpLceViewImpl.initView(view);*/

        if(handler == null){
            handler = new MvpLceInvocationHandler(view);
        }

        mvpLceViewImpl  = (MvpLceView) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class<?>[]{MvpLceView.class},handler);
    }

    @Override
    public void showLoading(boolean isPullRefresh) {
//        mMvpLceViewImpl.showLoading(isPullRefresh);
        mvpLceViewImpl.showLoading(isPullRefresh);
    }

    @Override
    public void showContent(boolean isPullRefresh) {
//        mMvpLceViewImpl.showContent(isPullRefresh);
        mvpLceViewImpl.showContent(isPullRefresh);

    }

    @Override
    public void showError(boolean isPullRefresh) {
//        mMvpLceViewImpl.showError(isPullRefresh);
        mvpLceViewImpl.showError(isPullRefresh);
    }


    @Override
    public void loadData(boolean isPullRefresh) {
//        mMvpLceViewImpl.loadData(isPullRefresh);
        mvpLceViewImpl.loadData(isPullRefresh);
    }

    public void setLceAnimator(ILceAnimator animator){
//        mMvpLceViewImpl.setLceAnimator(animator);
        handler.setLceAnimator(animator);
    }

    @Override
    public LifecycleProvider<T> getRxLifecycle() {
        return null;
    }

    @Override
    public T getRxEvent() {
        return null;
    }
}
