package com.mvp.wangll.mvptest.framework.support.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mvp.wangll.mvptest.framework.support.MvpDelegate;
import com.mvp.wangll.mvptest.framework.base.MvpPresenter;
import com.mvp.wangll.mvptest.framework.base.MvpView;


/**
 * Created by Administrator on 2017/10/11 0011.
 */
//Activity生命周期代理接口的代理类
//作为P层、V层绑定与解绑的委托类
public abstract class MvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends Activity implements MvpView,MvpDelegate<V,P> {
    //持有生命周期委托类引用
    private MvpActivityDelegateImpl<V,P> mDelegate;
    private V mView;
    private P mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mDelegate == null){
            mDelegate = new MvpActivityDelegateImpl<V,P>(this);
        }
        mDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDelegate.onDestroy();
    }

    /*@Override
    public P CreatePresenter() {
        return null;
    }*/

    @Override
    public V CreateView() {
        return null;
    }

    @Override
    public P GetPresenter() {
        return mPresenter;
    }

    @Override
    public V GetView() {
        return mView;
    }

    @Override
    public void SetPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void SetView(V view) {
        mView = view;
    }
}
