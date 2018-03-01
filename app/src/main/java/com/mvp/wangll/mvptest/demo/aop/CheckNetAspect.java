package com.mvp.wangll.mvptest.demo.aop;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.mvp.wangll.mvptest.Httpframework.NetworkUtils;
import com.mvp.wangll.mvptest.SampleApplication;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

@Aspect
public class CheckNetAspect {

    @Pointcut("execution(@com.mvp.wangll.mvptest.demo.annotation.CheckNet * *(..))")
    public void NetPointcut(){}

    @Around(value = "NetPointcut()")
    public Object checkNetAvailable(ProceedingJoinPoint point) throws Throwable {
        if (!NetworkUtils.isAvailable(SampleApplication.getAppContext())) {
            Toast.makeText(SampleApplication.getAppContext(), "网络异常!", Toast.LENGTH_SHORT).show();
            return null;
        }
        return point.proceed();
    }


    /**
     * 通过对象获取上下文
     *
     * @param object
     * @return
     */
    private Context getContext(Object object) {
        if (object instanceof Activity) {
            return (Activity) object;
        } else if (object instanceof Fragment) {
            Fragment fragment = (Fragment) object;
            return fragment.getActivity();
        } else if (object instanceof View) {
            View view = (View) object;
            return view.getContext();
        }
        return null;
    }


}
