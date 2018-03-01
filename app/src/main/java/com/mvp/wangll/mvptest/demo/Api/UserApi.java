package com.mvp.wangll.mvptest.demo.Api;


import com.mvp.wangll.mvptest.demo.list.bean.HttpResponse;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public interface UserApi {
    @GET("user/login")
    Observable<HttpResponse> login(@QueryMap Map<String, Object> request);
}
