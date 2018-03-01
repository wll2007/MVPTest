package com.mvp.wangll.mvptest.Mvpframework.support.activity;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.mvp.wangll.mvptest.Mvpframework.support.MvpDelegate;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * Created by Administrator on 2017/10/11 0011.
 */
//Activity生命周期代理接口的代理类
//作为P层、V层绑定与解绑的委托类
public abstract class MvpActivity<V extends MvpView,P extends MvpPresenter<V>> extends RxAppCompatActivity implements MvpDelegate<V,P>,LoaderManager.LoaderCallbacks<P>{
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
        getSupportLoaderManager().initLoader(100,null,this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDelegate.onStart();
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
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

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }
}
