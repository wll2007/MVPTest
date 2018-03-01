package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import com.mvp.wangll.mvptest.Httpframework.exception.ApiException;

/**
 *  @作者 wll
 *  @日期 2018/1/9 0009
 *  @desc 更新UI进度的接口
 */
public interface FileDownLoadListener {
    void preProgressToUI();                              //预处理
    void updateProgressToUI(long ready,long FileLength); //更新
    void completeProgressToUI(long ready,long total);    //完成
    void pauseProgressToUI(long ready,long total);       //暂停
    void cancelProgressToUI();                           //取消
    void exceptionDownLoad(ApiException e);              //出现异常
}
