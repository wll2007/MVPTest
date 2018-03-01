package com.mvp.wangll.mvptest.demo.list;

import android.util.Log;

import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.FileDownLoadManager;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.FileDownLoadListener;
import com.mvp.wangll.mvptest.Httpframework.exception.ApiException;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpBasePresenter;
import com.mvp.wangll.mvptest.demo.IView.ILoginView;
import com.mvp.wangll.mvptest.demo.Model.FileModel;
import com.mvp.wangll.mvptest.demo.Model.LoginModel;
import com.mvp.wangll.mvptest.demo.annotation.CheckNet;
import com.mvp.wangll.mvptest.demo.http.httpCallback;
import com.mvp.wangll.mvptest.demo.http.httpLoadingConsumer;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;
import com.mvp.wangll.mvptest.demo.list.bean.UserBean;
import com.mvp.wangll.mvptest.greenDao.DbManager;
import com.mvp.wangll.mvptest.greenDao.DownInfoDao;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class loginPresenter<E,V extends ILoginView<E>> extends MvpBasePresenter<V> {
    private String TAG = loginPresenter.class.getName();
    private LoginModel<E> model;
    private FileDownLoadListener listener = new FileDownLoadListener() {
        @Override
        public void updateProgressToUI(long read,long filelength) {
            getView().updateProgress(read,filelength);
        }

        @Override
        public void completeProgressToUI(long read,long total) {
            getView().updateProgress(read,total);
            getView().completeProgress(total);
        }

        @Override
        public void pauseProgressToUI(long ready, long total) {
            getView().updateProgress(ready,total);
        }

        @Override
        public void cancelProgressToUI() {
            getView().updateProgress(0,0);
        }

        @Override
        public void exceptionDownLoad(ApiException e) {
            getView().errorOfFileDown(e.getMsg());
        }

        @Override
        public void preProgressToUI() {
            getView().preFileDown();
        }
    };


    private httpCallback<UserBean> MvpCallback = new httpCallback<UserBean>() {
        @Override
        public void complete() {
            Log.i(TAG,"complete");
        }

        @Override
        public void CallbackSuccess(UserBean o) {
            Log.i(TAG,"CallbackSuccess");
            DbManager.getDaoSession().getUserBeanDao().insertOrReplace(o);
            getView().showContent(false);
        }

        @Override
        public void OnError(ApiException e) {
            getView().showError(false);
        }


    };

    public loginPresenter() {
    }

    public void login(String username, String password) {
        if(model == null){
            model = new LoginModel<E>(getView().getRxLifecycle(),getView().getRxEvent());
        }

        model.login(username,password,new httpLoadingConsumer(getView()),MvpCallback);
    }

    @CheckNet
    public void down(String url) {
        FileDownLoadManager.getInstance().down(url,listener,getView().getRxLifecycle(),getView().getRxEvent());
    }

    @CheckNet
    public void down(int url) {
       // FileDownLoadManager.getInstance().down(url,listener,getView().getRxLifecycle(),getView().getRxEvent());
    }

    public void look(String url){
        List<DownInfo> list= DbManager.getDaoSession().getDownInfoDao().queryBuilder().where(DownInfoDao.Properties.Url.eq(url)).list();
        if(list.size()>0){
            getView().bindData(list.get(0));
        }else {
            getView().bindData(null);
        }
    }

    public void stop(String url){
        FileDownLoadManager.getInstance().pause(url);
    }

    public void cancel(String url){
        FileDownLoadManager.getInstance().remove(url);
    }

}
