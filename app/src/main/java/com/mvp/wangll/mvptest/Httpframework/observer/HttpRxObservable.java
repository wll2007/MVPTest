package com.mvp.wangll.mvptest.Httpframework.observer;


import com.mvp.wangll.mvptest.Httpframework.exception.ExceptionEngine;
import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
/**
 *  @作者 wll
 *  @日期 2017/11/16 0016
 *  @desc   Http请求的Observable
 */
public class HttpRxObservable {

    private static Observer mObserver = new Observer() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(@NonNull Object o) {

        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };
    /**
     * 自动注销的带生命周期的被观察者
     * @param observable
     * @param provider
     * @return
     */
 /*   public static <T,E> void subscribe(Observable<T> observable,Function<T,Object> function,Consumer<Disposable> consumer, LifecycleProvider<E> provider,Observer observer){
        if(observer == null){
            //throw new NullPointerException("Must have Observer!");
            observer = mObserver;
        }

        if(provider != null){
             getObservable(observable,function,consumer).compose(provider.bindToLifecycle()).subscribe(observer);
            return;
        }
        getObservable(observable,function,consumer).subscribe(observer);
        return;
    }*/

    /**
     *  用于Activity的带生命周期的被观察者
     * @param observable
     * @param provider
     * @param event
     * @return
     */
    public static <T,E,R> void subscribe(Observable<T> observable,Function<T,R> function,Consumer<Disposable> consumer, LifecycleProvider<E> provider, E event,Observer observer){
        if(observer == null){
            //throw new NullPointerException("Must have Observer!");
            observer = mObserver;
        }

       if(provider != null) {
           if(event != null) {
               getObservable(observable, function, consumer)
                       .compose(provider.bindUntilEvent(event))
                       .subscribe(observer);
           }else {
               getObservable(observable,function,consumer).compose(provider.bindToLifecycle()).subscribe(observer);
           }
           return;
       }

        getObservable(observable,function,consumer).subscribe(observer);
        return;
    }

    /**
     * 不带生命周期的被观察者
     * @param observable
     * @return
     */
    public static <T,R> Observable<R> getObservable(Observable<T> observable,Function<T,R> function,Consumer<Disposable> consumer){

        if(observable == null){
            throw new NullPointerException("Must have Api observable!");
        }

        Observable newObservable =  observable.subscribeOn(Schedulers.io());
        if(consumer != null){
            newObservable = newObservable.doOnSubscribe(consumer)
                    .subscribeOn(AndroidSchedulers.mainThread());
        }

        if(function != null){
            //throw new NullPointerException("Must have Response transforme!");
            newObservable = newObservable.map(function);
        }


        return newObservable
                    .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                        @Override
                        public ObservableSource<? extends T> apply(@NonNull Throwable throwable) throws Exception {
                            return Observable.error(ExceptionEngine.transform(throwable));
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
       /* return observable.subscribeOn(Schedulers.io())
               .doOnSubscribe(consumer)
                .subscribeOn(AndroidSchedulers.mainThread())
               .map(function)
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends T>>() {
                    @Override
                    public ObservableSource<? extends T> apply(@NonNull Throwable throwable) throws Exception {
                        return Observable.error(ExceptionEngine.transform(throwable));
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());*/
    }

    /**
     * 用于Fragment的带生命周期的被观察者
     * @param observable
     * @param provider
     * @param event
     * @return
     */
   /* public static <T> Observable getObservable(Observable<T> observable,Function<T,Object> function, LifecycleProvider<FragmentEvent> provider,FragmentEvent event){
        if(provider != null) {
            return getObservable(observable,function).compose(provider.bindUntilEvent(event));
        }

        return getObservable(observable,function);
    }*/
}
