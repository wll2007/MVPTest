package com.mvp.wangll.mvptest.framework.base;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V view;

    public V getView() {
        return view;
    }

    public void attachView(V view){
        this.view = view;
    }

    public void detachView(){
        this.view = null;
    }
}
