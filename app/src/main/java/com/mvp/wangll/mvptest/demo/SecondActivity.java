package com.mvp.wangll.mvptest.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.mvp.wangll.mvptest.R;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class SecondActivity extends FragmentActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
