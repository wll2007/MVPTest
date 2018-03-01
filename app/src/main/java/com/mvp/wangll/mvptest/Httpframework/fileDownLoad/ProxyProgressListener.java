package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import com.mvp.wangll.mvptest.Httpframework.exception.ApiException;
/**
 *  @作者 wll
 *  @日期 2018/1/9 0009
 *  @desc 更新UI进度的监听接口的代理类
 */
public class ProxyProgressListener implements FileDownLoadListener {
    private long mProgress;
    private FileDownLoadListener listener;

    public ProxyProgressListener(long mProgress, FileDownLoadListener listener) {
        this.mProgress = mProgress;
        this.listener = listener;
    }

    @Override
    public void preProgressToUI() {
        listener.preProgressToUI();
    }

    @Override
    public void updateProgressToUI(long ready, long FileLength) {
        mProgress += ready;
        listener.updateProgressToUI(mProgress,FileLength);
    }

    @Override
    public void completeProgressToUI(long ready, long total) {
        listener.completeProgressToUI(ready,total);
    }

    @Override
    public void pauseProgressToUI(long ready, long total) {
        listener.pauseProgressToUI(ready,total);
    }

    @Override
    public void cancelProgressToUI() {
        listener.cancelProgressToUI();
    }

    @Override
    public void exceptionDownLoad(ApiException e) {
        listener.exceptionDownLoad(e);
    }
}
