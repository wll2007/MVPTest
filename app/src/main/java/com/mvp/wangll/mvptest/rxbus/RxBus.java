package com.mvp.wangll.mvptest.rxbus;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 *  @作者 wll
 *  @日期 2017/11/17 0017
 *  @desc 带粘性的事件总线RxBus(即可以收到订阅前一条数据) 和结合RxLifecycle一起使用,不用手动注销订阅
 */
public class RxBus {
    private static  RxBus mIntance;
    private Subject mSubject;
    private Map<Class<?>,Object> map ;

    private RxBus() {
        mSubject =  PublishSubject.create();
        mSubject = mSubject.toSerialized();
        map = new ConcurrentHashMap<>();
    }

    public static RxBus getDeault(){
        if(mIntance==null){
            synchronized (RxBus.class){
                if(mIntance==null){
                    mIntance =new RxBus();
                }
            }
        }
        return mIntance;
    }

    public void send(Object event){
        mSubject.onNext(event);
    }

    public void sendSticky(Object event){
        synchronized (map){
            map.put(event.getClass(),event);
        }
        send(event);
    }


    public <T> Observable<T> toObservable(Class<T> classType){
        synchronized (map){
            Observable<T> observable = mSubject.ofType(classType);
            Object event = map.get(classType);
            if(event!=null){
                return observable.mergeWith(Observable.just(classType.cast(event)));
            }
            return observable;
        }

    }
}
