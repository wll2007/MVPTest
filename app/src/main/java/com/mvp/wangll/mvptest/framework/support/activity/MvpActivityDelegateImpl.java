package com.mvp.wangll.mvptest.framework.support.activity;

import android.os.Bundle;

import com.mvp.wangll.mvptest.framework.support.MvpDelegate;
import com.mvp.wangll.mvptest.framework.support.ProxyMvpDelegate;
import com.mvp.wangll.mvptest.framework.base.MvpPresenter;
import com.mvp.wangll.mvptest.framework.base.MvpView;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

//Activity生命周期的代理类
//再此类中要实现P层的绑定与解绑
public class MvpActivityDelegateImpl<V extends MvpView,P extends MvpPresenter<V>> implements MvpActivityDelegate {

    private ProxyMvpDelegate<V,P> mProxy;
    private MvpDelegate<V,P> mDelegate;
    public MvpActivityDelegateImpl(MvpDelegate<V,P> mDelegate) {
        this.mDelegate = mDelegate;
        if(mDelegate != null){
            mProxy = new ProxyMvpDelegate<>(mDelegate);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mProxy.attachView();
    }

    @Override
    public void onDestroy() {
        mProxy.detachView();
    }

    @Override
    public void onStart() {

    }
}
