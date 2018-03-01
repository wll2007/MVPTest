package com.mvp.wangll.mvptest.Httpframework.observer;

/**
 *  @作者 wll
 *  @日期 2017/11/17 0017
 *  @desc Cache相关接口
 */
public interface IHttpCache<T,K> {
    T getCache(K key);
    void  setCache(T data,K key);
}
