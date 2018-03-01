package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;


import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownloadProgressListener;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.FileDownLoadManager;
import com.mvp.wangll.mvptest.Httpframework.observer.HttpRxObserver;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 *  @作者 wll
 *  @日期 2018/1/9 0009
 *  @desc 文件下载的Observer,同时实时更新下载进度到UI
 */
public abstract class FileDownLoadObserver extends HttpRxObserver<DownInfo> implements DownloadProgressListener {
    private DownInfo info;
    private long mAlready ;

    public FileDownLoadObserver(DownInfo info) {
        this.info = info;
        mAlready = info.getReadLength();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if(d!=null){
            FileDownLoadManager.getInstance().addDisposable(info,d);
        }

    }

    @Override
    public void update(final long read,final long count) {
            info.setReadLength(count+mAlready);
            Observable.just(read).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                        FileDownLoadManager.getInstance().updateProgress(info,aLong);
                        }
                    });

    }

    @Override
    public void complete() {
        OnComplete(info);
    }

    public abstract void OnComplete(DownInfo info);

}
