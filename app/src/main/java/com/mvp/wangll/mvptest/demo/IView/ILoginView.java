package com.mvp.wangll.mvptest.demo.IView;

import com.mvp.wangll.mvptest.Mvpframework.support.RxLifecycle.MvpRxLifecycle;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public interface ILoginView<T> extends MvpRxLifecycle<T>{
    void bindData(DownInfo bean);
    void updateProgress(long ready,long total);
    void completeProgress(long total);
    void errorOfFileDown(String Msg);
    void preFileDown();
}
