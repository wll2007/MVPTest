package com.mvp.wangll.mvptest.Mvpframework.support.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;

import com.mvp.wangll.mvptest.Mvpframework.support.MvpDelegate;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;
import com.trello.rxlifecycle2.components.support.RxFragment;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class MvpFragment<V extends MvpView,P extends MvpPresenter<V>> extends RxFragment implements FragmentMvpDelegate,MvpDelegate<V,P>,LoaderManager.LoaderCallbacks<P> {

    private V mView;
    private P mPresenter;
    private FragmentMvpDelegate mFragmentMvpDelegateImpl;

    public FragmentMvpDelegate getFragmentMvpDelegate() {
        if(mFragmentMvpDelegateImpl == null){
            mFragmentMvpDelegateImpl = new FragmentMvpDelegateImpl<>(this);
        }
        return mFragmentMvpDelegateImpl;

    }

    /*@Override
    public P CreatePresenter() {
        return null;
    }
*/
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFragmentMvpDelegate().onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(100,null,this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getFragmentMvpDelegate().onViewCreated(view,savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        getFragmentMvpDelegate().onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        getFragmentMvpDelegate().onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        getFragmentMvpDelegate().onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        getFragmentMvpDelegate().onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getFragmentMvpDelegate().onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentMvpDelegate().onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getFragmentMvpDelegate().onDetach();
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
