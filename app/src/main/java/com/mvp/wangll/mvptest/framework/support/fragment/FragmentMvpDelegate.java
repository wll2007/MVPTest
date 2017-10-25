package com.mvp.wangll.mvptest.framework.support.fragment;

import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public interface FragmentMvpDelegate {

    public void onCreate(Bundle savedInstanceState);

    public void onActivityCreated(Bundle savedInstanceState);

    public void onViewCreated(View view, Bundle savedInstanceState);

    public void onStart();

    public void onPause();

    public void onResume();

    public void onStop();

    public void onDestroyView();

    public void onDestroy();

    public void onDetach();
}
