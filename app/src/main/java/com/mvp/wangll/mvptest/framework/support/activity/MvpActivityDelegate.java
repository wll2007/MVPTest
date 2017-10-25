package com.mvp.wangll.mvptest.framework.support.activity;

import android.os.Bundle;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
//Activity生命周期的代理接口，此接口不仅适用于Activity还适合于Fragment等...
public interface MvpActivityDelegate {

    void onCreate(Bundle savedInstanceState);
    void onDestroy();

    void onStart();
}
