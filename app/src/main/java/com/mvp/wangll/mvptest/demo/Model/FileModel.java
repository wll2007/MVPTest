package com.mvp.wangll.mvptest.demo.Model;

import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownloadInterceptor;
import com.mvp.wangll.mvptest.Httpframework.RetrofitUtil;
import com.mvp.wangll.mvptest.Httpframework.observer.HttpRxObservable;
import com.mvp.wangll.mvptest.demo.Api.FileApi;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.FileDownLoadObserver;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.FileWriteFunction;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.GetFileLengthCallback;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class FileModel<T> {
    protected T mEvent;
    protected LifecycleProvider<T> mLifecycleProvider;

    public FileModel(LifecycleProvider<T> mLifecycleProvider, T mEvent) {
        this.mEvent = mEvent;
        this.mLifecycleProvider = mLifecycleProvider;
    }

    public void getFileLength(String url, GetFileLengthCallback mCallback, Consumer<Disposable> consumer){
        HttpRxObservable.subscribe(RetrofitUtil.retrofit().create(FileApi.class).getFileLength(url),null,consumer,mLifecycleProvider,mEvent,mCallback);
    }

    public void downLoadFile(long start, long end, String url, FileDownLoadObserver mCallback, DownInfo info, File file, Consumer<Disposable> consumer){
        FileWriteFunction fileWriteFunction = new FileWriteFunction(file,info,mCallback);
        HttpRxObservable.subscribe(RetrofitUtil.retrofit(new DownloadInterceptor(mCallback,info)).create(FileApi.class).downloadFile("bytes=" + start + "-" + end,url),fileWriteFunction,consumer,mLifecycleProvider,mEvent,mCallback);
    }
}
