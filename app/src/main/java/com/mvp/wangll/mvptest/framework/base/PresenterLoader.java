package com.mvp.wangll.mvptest.framework.base;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 *  @作者 wll
 *  @日期 2017/9/25 0025
 *  @desc
 */
public class PresenterLoader<T extends MvpPresenter> extends Loader<T> {
    private PresentFactory<T> factory;
    private T presenter;
    public PresenterLoader(Context context,PresentFactory<T> factory) {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if(presenter!=null){
            deliverResult(presenter);
            return;
        }
        forceLoad();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        presenter =factory.create();
        deliverResult(presenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        presenter = null;
    }
}
