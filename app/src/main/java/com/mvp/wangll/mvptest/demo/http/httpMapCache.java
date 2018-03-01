package com.mvp.wangll.mvptest.demo.http;

import com.mvp.wangll.mvptest.Httpframework.observer.IHttpCache;
import com.mvp.wangll.mvptest.SampleApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/17 0017.
 */

public class httpMapCache implements IHttpCache<Object,String> {


    public httpMapCache() {
    }

    @Override
    public Object getCache(String key) {
        return SampleApplication.getObject(key);
    }

    @Override
    public void setCache(Object data, String key) {
        SampleApplication.addMap(data,key);
    }
}
