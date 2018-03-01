package com.mvp.wangll.mvptest.Httpframework.observer;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/11/15 0015.
 */
/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc  带缓存机制的Http请求Observable
 */
public class HttpCacheRxObservable {
    private static HttpCacheRxObservable mInstance;
    private IHttpCache  mCache;
    public static HttpCacheRxObservable getInstance(IHttpCache cache){
        synchronized (HttpCacheRxObservable.class){
            if(mInstance == null){
                mInstance = new HttpCacheRxObservable(cache);
            }
        }
        return mInstance;
    }

    private HttpCacheRxObservable(IHttpCache Cache) {
        mCache = Cache;
    }

    public  <T> Observable getHttpCacheRxObservable(final String key, Observable<T> observable, Boolean isForceReFresh){
        if(mCache==null){
            return observable.observeOn(AndroidSchedulers.mainThread());
        }

        Observable<Object> cacheOb = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                Object response = mCache.getCache(key);
                if(response != null){
                    Log.i("HttpCacheObservable","用了缓存!");
                    e.onNext(response);
                }
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<Object>  resultObservable = observable.map(new Function<T, Object>() {
            @Override
            public Object apply(@NonNull T httpResponse) throws Exception {
                mCache.setCache(httpResponse,key);
                return httpResponse;
            }
        });

        if(!isForceReFresh) {
            return Observable.concat(cacheOb, resultObservable).filter(new Predicate<Object>() {
                @Override
                public boolean test(@NonNull Object httpResponse) throws Exception {
                    return httpResponse != null;
                }
            }).take(1).observeOn(AndroidSchedulers.mainThread());
        }else {
            return resultObservable.observeOn(AndroidSchedulers.mainThread());
        }
    }
}
