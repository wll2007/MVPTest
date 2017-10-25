package com.mvp.wangll.mvptest.demo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.framework.adapter.CommonAdapter;
import com.mvp.wangll.mvptest.framework.support.Lce.fragment.MvpLceFragment;
import com.mvp.wangll.mvptest.framework.base.MvpPresenter;
import com.mvp.wangll.mvptest.framework.base.MvpView;

/**
 * Created by Administrator on 2017/10/23 0023.
 */

public abstract class MvpListViewFragment<D,V extends MvpView,P extends MvpPresenter<V>> extends MvpLceFragment<D,V,P> {

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
