package com.mvp.wangll.mvptest.demo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.Mvpframework.adapter.CommonAdapter;
import com.mvp.wangll.mvptest.Mvpframework.support.Lce.fragment.MvpLceFragment;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpPresenter;
import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public abstract class MvpListViewFragment<V extends MvpView,P extends MvpPresenter<V>,T> extends MvpLceFragment<V,P,T> {

    private ListView listView;
    private CommonAdapter mAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.contentView);
        mAdapter = CreateAdapter();
        listView.setAdapter(mAdapter);
    }

    public abstract CommonAdapter CreateAdapter();

    public  CommonAdapter getAdapter(){
        return mAdapter;
    }
}
