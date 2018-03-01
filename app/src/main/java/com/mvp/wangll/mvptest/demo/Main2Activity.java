package com.mvp.wangll.mvptest.demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.customWidget.navigationBar.DefaultNavigationBar;

public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);
        DefaultNavigationBar bar = new DefaultNavigationBar.Builder(this)
                .setLeftIcon(R.drawable.btn_return_bg)
                .setRightText("投稿").setTitle("发表").create();
    }
}
