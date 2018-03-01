package com.mvp.wangll.mvptest.demo.http;

import com.mvp.wangll.mvptest.Mvpframework.support.Lce.MvpLceView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc http请求时的加载效果
 */
public class httpLoadingConsumer implements Consumer<Disposable> {
    private MvpLceView view;

    public httpLoadingConsumer(MvpLceView view) {
        this.view = view;
    }

    @Override
    public void accept(Disposable o) throws Exception {
        view.showLoading(false);
    }
}
