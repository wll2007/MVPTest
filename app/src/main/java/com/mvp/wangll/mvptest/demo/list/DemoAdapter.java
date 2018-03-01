package com.mvp.wangll.mvptest.demo.list;

import android.content.Context;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.Mvpframework.adapter.BaseViewHolder;
import com.mvp.wangll.mvptest.Mvpframework.adapter.CommonAdapter;
import com.mvp.wangll.mvptest.demo.list.bean.loginBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public class DemoAdapter extends CommonAdapter<loginBean> {


    public DemoAdapter(List<loginBean> mLists, Context context, int laytouId) {
        super(mLists, context, laytouId);
    }

    @Override
    public void convert(BaseViewHolder holder, loginBean data) {
        holder.setText(R.id.ll,""+data.getId());
    }
}
