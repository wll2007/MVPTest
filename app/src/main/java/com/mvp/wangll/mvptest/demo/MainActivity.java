package com.mvp.wangll.mvptest.demo;

import android.os.Bundle;
import android.view.View;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.framework.support.activity.MvpActivity;
import com.mvp.wangll.mvptest.demo.list.loginPresenter;

public class MainActivity extends MvpActivity<MainActivity,loginPresenter<MainActivity>> implements View.OnClickListener {
    /*private TextView name;
    private TextView password;
    private Button login;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  name = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);*/

    }

    @Override
    public void onClick(View view) {
        GetPresenter().login();
    }

/*
    @Override
    public loginPresenter CreatePresenter() {
        return new loginPresenter();
    }*/

    @Override
    public MainActivity CreateView() {
        return this;
    }

}
