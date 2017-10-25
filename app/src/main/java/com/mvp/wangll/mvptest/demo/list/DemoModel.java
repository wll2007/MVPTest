package com.mvp.wangll.mvptest.demo.list;

import com.mvp.wangll.mvptest.demo.list.bean.loginBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class DemoModel {
    private List<loginBean> mData;

    public DemoModel(){
        mData = new ArrayList<>();
    }


    public List<loginBean> getmData() {
        return mData;
    }

    public void AddBean(loginBean bean){
        if(!mData.contains(bean)){
            mData.add(bean);
        }
    }
}
