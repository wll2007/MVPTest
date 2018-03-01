package com.mvp.wangll.mvptest.demo.Api;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public interface FileApi {
    /**
     * 获取下载文件的大小
     * @return
     */
    @GET
    Observable<ResponseBody> getFileLength(@Url String url);

    /**
     * 下载文件
     * @param range
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Header("RANGE") String range, @Url String url);
}
