package com.mvp.wangll.mvptest.demo.Model;

import com.mvp.wangll.mvptest.Httpframework.RetrofitUtil;
import com.mvp.wangll.mvptest.Httpframework.observer.HttpRxObservable;
import com.mvp.wangll.mvptest.demo.Api.ApiUtils;
import com.mvp.wangll.mvptest.demo.Api.UserApi;
import com.mvp.wangll.mvptest.demo.http.HttpModel;
import com.mvp.wangll.mvptest.demo.http.ServerResultFunction;
import com.mvp.wangll.mvptest.demo.http.httpCallback;
import com.mvp.wangll.mvptest.demo.list.bean.UserBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import java.util.HashMap;
import java.util.Map;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class LoginModel<T> extends HttpModel<T> {

    public LoginModel(LifecycleProvider<T> mLifecycleProvider,T event) {
        super(mLifecycleProvider,event);
    }

    public void  login(String username, String password, Consumer<Disposable> consumer,httpCallback<UserBean> mCallback){
        Map<String,Object> request = new HashMap<>();
        request.put("username",username);
        request.put("password",password);
        request.put("key","1889b37351288");

        HttpRxObservable.subscribe(RetrofitUtil.retrofit().create(UserApi.class).login(request), ServerResultFunction.getInstance(), consumer,mLifecycleProvider, mEvent,mCallback);
    }
}
