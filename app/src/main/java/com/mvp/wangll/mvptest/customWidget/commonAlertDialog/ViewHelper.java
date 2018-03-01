package com.mvp.wangll.mvptest.customWidget.commonAlertDialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/1/24 0024.
 */

public class ViewHelper {

    private View mContentView;
    private SparseArray<WeakReference<View>> mViewArray;

    public ViewHelper() {
        mViewArray = new SparseArray<>();
    }

    public ViewHelper(Context context, int layoutResID) {
        mContentView = LayoutInflater.from(context).inflate(layoutResID,null);
        mViewArray = new SparseArray<>();
    }

    public void   setContentView(View view){
         mContentView = view;
    }

    public View  getContentView(){
        return mContentView;
    }

    public void setText(int resId,CharSequence charSequence){
        TextView textView = getView(resId);
        textView.setText(charSequence);
    }

    public void setOnClickListener(int resId,View.OnClickListener listener){
        View view = getView(resId);
        view.setOnClickListener(listener);
    }

    private <T extends View> T getView(int resId) {
        WeakReference<View> weakReference = mViewArray.get(resId);
        View view = null;
        if(null!=weakReference){
            view = weakReference.get();
        }

        if(null==view){
            view = mContentView.findViewById(resId);
        }

        return (T) view;
    }


}