package com.mvp.wangll.mvptest.demo.list;

import android.os.Handler;

import com.mvp.wangll.mvptest.demo.list.DemoModel;
import com.mvp.wangll.mvptest.demo.list.bean.loginBean;
import com.mvp.wangll.mvptest.framework.base.MvpBasePresenter;
import com.mvp.wangll.mvptest.framework.support.Lce.MvpLceView;
import com.mvp.wangll.mvptest.framework.base.MvpPresenter;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class DemoPresenter<V extends MvpLceView<DemoModel>> extends MvpBasePresenter<V> {
    private DemoModel list;
    private Handler handler = new Handler();

    public DemoPresenter() {
        list = new DemoModel();
    }

    public void login( final boolean  isPullRefresh) {
        //页面显示加载页
        getView().showLoading(isPullRefresh);

        for (int i = 0; i < 20; i++) {
            loginBean bean = new loginBean(i);
            list.AddBean(bean);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //绑定数据
                getView().bindData(list,isPullRefresh);

                getView().showContent(isPullRefresh);

            }
        },10000);
        //显示错误提示
        //getView().showError();
    }
}
