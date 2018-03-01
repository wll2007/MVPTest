package com.mvp.wangll.mvptest.Mvpframework.support;

import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
//这个类获取的P层,V层必须是通过MVPActivity的子类中获取
//绑定、解绑的代理接口类
public class ProxyMvpDelegate<V extends MvpView,P extends MvpPresenter<V>> implements MvpDelegate<V,P> {

    //持有委托类的引用
    //引用的实际是MvpActivity的子类，因为P层，V层的实例化在子类中确定的
    private MvpDelegate<V,P> mvpDelegate;

    public ProxyMvpDelegate(MvpDelegate<V, P> mvpDelegate) {
        this.mvpDelegate = mvpDelegate;
    }

//    @Override
//    public P CreatePresenter() {
//        P presenter = mvpDelegate.GetPresenter();
//        if(presenter == null){
//            presenter = mvpDelegate.CreatePresenter();
//            SetPresenter(presenter);
//        }
//        return presenter;
//    }

    @Override
    public V CreateView() {
        V view = mvpDelegate.GetView();
        if(view == null){
            view = mvpDelegate.CreateView();
            SetView(view);
        }
        return view;
    }

    @Override
    public P GetPresenter() {
//        return CreatePresenter();
        return mvpDelegate.GetPresenter();
    }

    @Override
    public V GetView() {
        return CreateView();
    }

    @Override
    public void SetPresenter(P presenter) {
        mvpDelegate.SetPresenter(presenter);
    }

    @Override
    public void SetView(V view) {
        mvpDelegate.SetView(view);
    }

    public void  attachView(){
        GetPresenter().attachView(GetView());
    }

    public void detachView(){
        GetPresenter().detachView();
    }
}
