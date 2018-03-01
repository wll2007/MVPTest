package com.mvp.wangll.mvptest;

import android.app.Application;
import android.content.Context;

import com.mvp.wangll.mvptest.Httpframework.RetrofitUtil;
import com.mvp.wangll.mvptest.demo.annotation.CheckNet;
import com.mvp.wangll.mvptest.greenDao.DbManager;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class SampleApplication extends Application {
    private static Map<String,Object> map = new HashMap<>();
    private static Context ApplicationContext;
    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitUtil.init(getApplicationContext());
        DbManager.init(this);
        ApplicationContext = this;
    }

    public static Context getAppContext() {
        return ApplicationContext;
    }

    public static Object getObject(String key){
        return map.get(key);
    }

    public static void addMap(Object data,String key){
        map.put(key,data);
    }
}
