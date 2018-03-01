package com.mvp.wangll.mvptest.Httpframework;


import android.content.Context;
import android.text.TextUtils;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 *  @作者 wll
 *  @日期 2017/12/5 0005
 *  @desc retrofit的工具类
 */
public class RetrofitUtil {
    private static RfuClient HttpClient;
    private static String baseUrl = "http://apicloud.mob.com/";
    private static Context mContext;

    private static void createrClient(){
        if(HttpClient == null) {
            HttpClient = new RfuClient.Builder(mContext).build();
        }
    }

    /**
     * 利用默认的成员变量生成Retrofit
     * @return
     */
    public static Retrofit retrofit(){
      return  retrofit(baseUrl,HttpClient);
    }


    /**
     * 指定特定的请求Url生成Retrofit
     * @param url  请求Url
     * @return
     */
    public static Retrofit retrofit(String url){
        if(TextUtils.isEmpty(url)){
           return retrofit();
        }
        return  retrofit(url,HttpClient);
    }


    /**
     * 指定特定的请求Url,okHttpClient生成Retrofit
     * okHttpClient中可以设置通用的头部,拦截器等信息
     * @param url     请求Url
     * @param client   封装了的okHttpClient
     * @return
     */
    public static Retrofit retrofit(String url,RfuClient client){
        if(TextUtils.isEmpty(url)){
            url = baseUrl;
        }

        if(client == null){
            client = HttpClient;
        }
        return  new Retrofit.Builder().baseUrl(url).client(client.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    /**
     * 指定特定的okHttpClient生成Retrofit
     * okHttpClient中可以设置通用的头部,拦截器等信息
     * @param client 封装了的okHttpClient
     * @return
     */
    public static Retrofit retrofit(RfuClient client){
        if(client == null){
            client = HttpClient;
        }
        return  retrofit(baseUrl,HttpClient);
    }

    /**
     * 添加拦截器
     * @param interceptor
     * @return
     */
    public static Retrofit retrofit(Interceptor interceptor){
        RfuClient.Builder builder = new RfuClient.Builder(HttpClient);
        builder.addInterceptor(interceptor);
        return  retrofit(baseUrl,builder.build());
    }


    /**
     * 初始化工具类的全局变量
     * @param context
     */
    public static void init(Context context){
        mContext = context;
        createrClient();
    }

}
