package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 成功回调处理
 * Created by WZG on 2016/10/20.
 */
public class DownloadInterceptor implements Interceptor {

    private DownloadProgressListener listener;
    private DownInfo info;

    public DownloadInterceptor(DownloadProgressListener listener,DownInfo info) {
        this.listener = listener;
        this.info = info;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), listener,info))
                .build();
    }
}
