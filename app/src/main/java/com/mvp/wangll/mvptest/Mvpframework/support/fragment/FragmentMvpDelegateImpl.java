package com.mvp.wangll.mvptest.Mvpframework.support.fragment;

import android.os.Bundle;
import android.view.View;

import com.mvp.wangll.mvptest.Mvpframework.support.MvpDelegate;
import com.mvp.wangll.mvptest.Mvpframework.support.ProxyMvpDelegate;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class FragmentMvpDelegateImpl<V extends MvpView,P extends MvpPresenter<V>> implements FragmentMvpDelegate {

    private MvpDelegate<V,P> mvpDelegate;
    private ProxyMvpDelegate<V,P> proxyMvpDelegate;

    public FragmentMvpDelegateImpl(MvpDelegate<V, P> mvpDelegate) {
        this.mvpDelegate = mvpDelegate;
    }

    public ProxyMvpDelegate<V, P> getProxyMvpDelegate() {
        if(proxyMvpDelegate == null){
            proxyMvpDelegate = new ProxyMvpDelegate<>(mvpDelegate);
        }
        return proxyMvpDelegate;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
//        getProxyMvpDelegate().attachView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public void onStart() {
        getProxyMvpDelegate().attachView();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroyView() {
        getProxyMvpDelegate().detachView();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDetach() {

    }
}
