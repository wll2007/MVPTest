package com.mvp.wangll.mvptest.customWidget.navigationBar;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.mvp.wangll.mvptest.R;
/**
 *  @作者 wll
 *  @日期 2018/1/27 0027
 *  @desc 默认布局 此布局还得优化
 */
public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder.DefaultNavigationParams> {

    private DefaultNavigationBar(Builder.DefaultNavigationParams param) {
        super(param);
    }

    @Override
    public int getLayoutId() {
        return R.layout.navigation_default;
    }

    @Override
    public void applyView() {
        setText(R.id.tv_left, param.leftTv);
        setText(R.id.tv_right, param.rightTv);
        setText(R.id.tv_title, param.title);
        setImageResource(R.id.iv_left, param.leftIconRes);
        setImageResource(R.id.iv_right, param.rightIconRes);
        setOnClickListener(R.id.layout_left, param.leftOnClickListener);
        setOnClickListener(R.id.layout_right, param.rightOnClickListener);
        setBackgroundColor(R.id.title_bar, param.bgColor);
        setBackground(R.id.title_bar, param.bgDrawable);
        setVisibility(R.id.tv_left, param.leftTvVis);
        setVisibility(R.id.iv_left, param.leftImgVis);
        setVisibility(R.id.tv_right, param.rightTvVis);
        setVisibility(R.id.iv_right, param.rightImgVis);
    }

    public static class Builder extends AbsNavigationBar.Builder {
        private DefaultNavigationParams params;

        public Builder(Context context) {
            params = new DefaultNavigationParams(context);
        }

        @Override
        public DefaultNavigationBar create() {
            DefaultNavigationBar bar = new DefaultNavigationBar(params);
            return bar;
        }

        public Builder setTitle(String title){
            this.params.title = title;
            return this;
        }

        public Builder setLeftIcon(int icon){
            this.params.leftIconRes = icon;
            return this;
        }

        public Builder setRightIcon(int icon){
            this.params.rightIconRes = icon;
            return this;
        }

        public Builder setRightOnClickListener(View.OnClickListener  listener){
            this.params.rightOnClickListener = listener;
            return this;
        }

        public Builder setLeftOnClickListener(View.OnClickListener  listener){
            this.params.leftOnClickListener = listener;
            return this;
        }

        public Builder setLeftText(String text){
            this.params.leftTv = text;
            return this;
        }

        public Builder setRightText(String text){
            this.params.rightTv = text;
            return this;
        }

        public Builder setBarBackgroundColor(int color){
            this.params.bgColor = color;
            return this;
        }

        public Builder setBarBackgroundDrawable(int drawable){
            this.params.bgDrawable = drawable;
            return this;
        }

        public Builder setLeftTvVisibility(int visibility){
            this.params.leftTvVis = visibility;
            return this;
        }

        public Builder setRightTvVisibility(int visibility){
            this.params.rightTvVis = visibility;
            return this;
        }

        public Builder setRightImgVisibility(int visibility){
            this.params.rightImgVis = visibility;
            return this;
        }

        public Builder setLeftImgVisibility(int visibility){
            this.params.leftImgVis = visibility;
            return this;
        }

        public static class DefaultNavigationParams extends NavigationParams{
            //标题
            public String title;
            //左边图片资源
            public int leftIconRes;
            //右边图片资源
            public int rightIconRes;
            //左边的点击事件
            public View.OnClickListener leftOnClickListener;
            //右边的点击事件
            public View.OnClickListener rightOnClickListener;
            public String leftTv;
            public String rightTv;
            public int bgColor;
            public int bgDrawable;
            public int leftImgVis=View.VISIBLE;
            public int rightImgVis=View.INVISIBLE;
            public int leftTvVis=View.INVISIBLE;
            public int rightTvVis=View.VISIBLE;

            public DefaultNavigationParams(final Context context) {
                super(context);
                leftOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((Activity)context).finish();
                    }
                };
            }
        }
    }
}
