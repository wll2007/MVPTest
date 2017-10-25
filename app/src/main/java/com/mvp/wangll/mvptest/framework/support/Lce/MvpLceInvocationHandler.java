package com.mvp.wangll.mvptest.framework.support.Lce;

import android.view.View;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.framework.support.Lce.animator.ILceAnimator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/10/24 0024.
 */

public class MvpLceInvocationHandler implements InvocationHandler {

    private ILceAnimator animator;
    private View loadView;
    private View errorView;
    private View contentView;
    private View rootView;
    private boolean isinit = false;

    public MvpLceInvocationHandler(View rootView) {
        this.rootView = rootView;
    }

    public void setLceAnimator(ILceAnimator animator ){
        this.animator = animator;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if(!isinit)
            initView(rootView);
        if(method.getName().equals("showLoading")){
            if(!(boolean)objects[0]) {
                animator.showLoadingView(loadView, contentView, errorView);
            }
        }else if (method.getName().equals("showContent")){
            if(!(boolean)objects[0]) {
                animator.showContentView(loadView, contentView, errorView);
            }
        }else if(method.getName().equals("showError")){
            if(!(boolean)objects[0]) {
                animator.showErrorView(loadView, contentView, errorView);
            }
        }
        return null;
    }

    private void initView(View view) {
        if (view == null){
            throw new NullPointerException("rootView不能够为空");
        }
        if (this.loadView == null){
            this.loadView = view.findViewById(R.id.loadingView);
        }
        if (this.contentView == null){
            this.contentView = view.findViewById(R.id.contentView);
        }
        if (this.errorView == null){
            this.errorView = view.findViewById(R.id.errorView);
        }
        if (loadView == null){
            throw new NullPointerException("loadingView不能够为空");
        }
        if (contentView == null){
            throw new NullPointerException("contentView不能够为空");
        }
        if (errorView == null){
            throw new NullPointerException("errorView不能够为空");
        }

        isinit = true;
    }
}
