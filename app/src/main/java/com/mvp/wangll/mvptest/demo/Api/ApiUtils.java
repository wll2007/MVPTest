package com.mvp.wangll.mvptest.demo.Api;


import com.mvp.wangll.mvptest.Httpframework.RetrofitUtil;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class ApiUtils {
    private static UserApi userApi;
    private static FileApi fileApi;

    public static UserApi getUserApi(){
        if(userApi == null){
            userApi = RetrofitUtil.retrofit().create(UserApi.class);
        }
        return userApi;
    }

    public static FileApi getFileApi(){
        if(fileApi == null){
            fileApi = RetrofitUtil.retrofit().create(FileApi.class);
        }
        return fileApi;
    }
}
