package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.FileDownLoadManager;
import com.mvp.wangll.mvptest.Httpframework.observer.HttpRxObserver;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public abstract class GetFileLengthCallback extends HttpRxObserver<ResponseBody> {
    private String url;

    public GetFileLengthCallback(String url) {
        this.url = url;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        FileDownLoadManager.getInstance().addGetFileLengthDisposable(url,d);
    }

    @Override
    public void success(ResponseBody o) {
        FileDownLoadManager.getInstance().removeGetFileLengthDisposable(url);
        onSuccess(o);
    }

    public abstract void onSuccess(ResponseBody o);

}
