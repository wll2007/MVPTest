package com.mvp.wangll.mvptest.customWidget.navigationBar;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 *  @作者 wll
 *  @日期 2018/1/27 0027
 *  @desc  抽象类
 *  注意：所添加的头部布局外层必须使用LinearLayout且必须是垂直布局
 */
public abstract class AbsNavigationBar <P extends AbsNavigationBar.Builder.NavigationParams> implements INavigation {
    private ViewGroup mView;
    protected P param;

    protected AbsNavigationBar(P param) {
        this.param = param;
        createAndBind();
    }

    protected  void setText(int resId, CharSequence charSequence){
        TextView view = findViewById(resId);
        if(view!=null){
            view.setText(charSequence);
        }
    }
    protected  void setOnClickListener(int resId,View.OnClickListener listener){
        View view = findViewById(resId);
        if(view!=null){
            view.setOnClickListener(listener);
        }
    }
    protected  void setImageResource(int viewId, int resourceId){
        ImageView view = findViewById(viewId);
        if(view!=null){
            view.setImageResource(resourceId);
        }
    }

    protected void setBackgroundColor(int resId,int color){
        View view = findViewById(resId);
        if(view!=null){
            view.setBackgroundColor(color);
        }
    }

    protected void setBackground(int resId, int drawable){
        View view = findViewById(resId);
        if(view!=null){
            view.setBackgroundResource(drawable);
        }
    }

    protected void setVisibility(int resId,int visibility){
        View view = findViewById(resId);
        if(view!=null){
            view.setVisibility(visibility);
        }
    }

    private <T extends View> T findViewById(int viewId) {
        return (T)mView.findViewById(viewId);
    }

    private void createAndBind(){
        if(param ==null){
            return;
        }
        if(param.parent==null){
            param.parent = ((Activity) param.context).findViewById(android.R.id.content);   //先拿到Activity的根布局的content
        }
        mView= (ViewGroup) LayoutInflater.from(param.context).inflate(getLayoutId(),null);
        View child = param.parent.getChildAt(0);                                           //获取根布局的第一个child即Activity的xml布局
        param.parent.removeAllViews();                                                     //将xml布局从根布局中移除,然后添加到我们头布局中,再将头布局添加到根布局中
        mView.addView(child,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        param.parent.addView(mView);
        applyView();
    }

     static abstract class Builder {

        protected Builder() {
        }

        protected abstract AbsNavigationBar create();
         static abstract class NavigationParams{
            public Context context;
            public ViewGroup parent;
            protected NavigationParams(Context context) {
                this.context = context;
            }
        }
    }
}
