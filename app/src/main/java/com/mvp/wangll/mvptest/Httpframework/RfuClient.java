package com.mvp.wangll.mvptest.Httpframework;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.mvp.wangll.mvptest.BuildConfig;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BASIC;
import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;
import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;
import static okhttp3.logging.HttpLoggingInterceptor.Level.NONE;

/**
 *  @作者 wll
 *  @日期 2017/12/5 0005
 *  @desc  进行封装,利用builder模式设计可以添加拦截器,通用的头部等参数的OkHttpClient
 */
public final class RfuClient {
    OkHttpClient okHttpClient;
    Map<String,String> requestHead;
    List<Interceptor>  interceptors;
    File cacheFile;
    Context context;

    public RfuClient(OkHttpClient okHttpClient, Map<String, String> requestHead, List<Interceptor> interceptors, File cacheFile, Context context) {
        this.okHttpClient = okHttpClient;
        this.requestHead = requestHead;
        this.interceptors = interceptors;
        this.cacheFile = cacheFile;
        this.context = context;
    }

    public static final class  Builder{
        private  OkHttpClient okHttpClient;
        private  Map<String,String> requestHead = new HashMap<String, String>();
        private  List<Interceptor>  interceptors = new ArrayList<>();
        private  int cacheSize = 1024*1024;
        private File cacheFile;
        Context context;

        public Builder(Context context){
            okHttpClient = new OkHttpClient.Builder().connectTimeout(100, TimeUnit.SECONDS).build();
            cacheFile = context.getCacheDir();
            this.context = context;
        }

        public Builder(RfuClient client){
            okHttpClient = client.okHttpClient;
            requestHead = client.requestHead;
            interceptors = client.interceptors;
            cacheFile = client.cacheFile;
            context = client.context;
        }

        public Builder client(OkHttpClient client){
            this.okHttpClient = client;
            return this;
        }

        public Builder addRequestHead(Map<String, String> HeadMap){
            requestHead.putAll(HeadMap);
            return this;
        }

        public Builder  addRequestHead(String key,String value) {
            requestHead.put(key,value);
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor){
            interceptors.add(interceptor);
            return this;
        }

        public RfuClient build(){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(BuildConfig.DEBUG ? BODY : NONE);
            OkHttpClient.Builder builder = this.okHttpClient.newBuilder();
            builder.addInterceptor(httpLoggingInterceptor);
            builder.addInterceptor(cacheInterceptor);
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    if(requestHead.size()>0){
                        for (Map.Entry<String,String> entry:requestHead.entrySet()) {
                            request.newBuilder().addHeader(entry.getKey(),entry.getValue());
                        }
                    }
                    return  chain.proceed(request);
                }
            });

            if(this.interceptors.size()>0){
                for (Interceptor interceptor:interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }
            builder.cache(new Cache(cacheFile,cacheSize));
            return new RfuClient(builder.build(),requestHead,interceptors,cacheFile,context);
        }

        private Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Log.d("CacheInterceptor","entry this CacheInterceptor");
                Request request = chain.request();//获取请求
                //这里就是说判读我们的网络条件，要是有网络的话我么就直接获取网络上面的数据，要是没有网络的话我么就去缓存里面取数据
                if(!NetworkUtils.isAvailable(context)){
                    request = request.newBuilder()
                            //这个的话内容有点多啊，大家记住这么写就是只从缓存取，想要了解这个东西我等下在
                            // 给大家写连接吧。大家可以去看下，获取大家去找拦截器资料的时候就可以看到这个方面的东西反正也就是缓存策略。
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Log.d("CacheInterceptor","request no network");
                }
                Response originalResponse = chain.proceed(request);
                if(NetworkUtils.isAvailable(context)){
                    Log.d("CacheInterceptor","Response have network");
                    //这里大家看点开源码看看.header .removeHeader做了什么操作很简答，就是的加字段和减字段的。
                    return originalResponse.newBuilder()
                            //这里设置的为0就是说不进行缓存，我们也可以设置缓存时间
                            .header("Cache-Control", "public, max-age=" + 60)
                            .removeHeader("Pragma")
                            .build();
                }else{
                    Log.d("CacheInterceptor","Response no network");
                    int maxTime = 4*24*60*60;
                    return originalResponse.newBuilder()
                            //这里的设置的是我们的没有网络的缓存时间，想设置多少就是多少。
                            .header("Cache-Control", "public, only-if-cached, max-stale="+maxTime)
                            .removeHeader("Pragma")
                            .build();
                }
            }
        };
    }


}
