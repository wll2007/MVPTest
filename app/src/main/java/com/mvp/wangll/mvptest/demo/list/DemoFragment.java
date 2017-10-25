package com.mvp.wangll.mvptest.demo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.framework.base.PresentFactory;
import com.mvp.wangll.mvptest.framework.base.PresenterLoader;


/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class DemoFragment extends MvpListViewFragment<DemoModel,DemoFragment,DemoPresenter<DemoFragment>> {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_demo, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setLceAnimator(new LoadLcAnimator());
    }

    @Override
    public void onResume() {
        super.onResume();
        GetPresenter().login(false);
    }

    @Override
    public DemoAdapter CreateAdapter() {
        return new DemoAdapter(null,getContext(),R.layout.list_item);
    }

    @Override
    public DemoFragment CreateView() {
        return this;
    }
/*

    @Override
    public DemoPresenter<DemoFragment> CreatePresenter() {
        return new DemoPresenter<>();
    }
*/

    @Override
    public void bindData(DemoModel data, boolean isPullRefresh) {
        getAdapter().setData(data.getmData());
        getAdapter().notifyDataSetChanged();
    }

    @Override
    public Loader<DemoPresenter<DemoFragment>> onCreateLoader(int id, Bundle args) {
        return new PresenterLoader<>(getContext(), new PresentFactory<DemoPresenter<DemoFragment>>() {
            @Override
            public DemoPresenter<DemoFragment> create() {
                return new DemoPresenter<>();
            }
        });
    }
}
