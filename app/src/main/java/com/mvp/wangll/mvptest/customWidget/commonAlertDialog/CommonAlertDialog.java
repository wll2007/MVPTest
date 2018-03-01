package com.mvp.wangll.mvptest.customWidget.commonAlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.wangll.mvptest.R;
import com.mvp.wangll.mvptest.customWidget.commonAlertDialog.CommonAlertController.*;

/**
 * Created by Administrator on 2018/1/24 0024.
 */

public class CommonAlertDialog extends Dialog {
    private final CommonAlertController mAlert;

    private CommonAlertDialog(@NonNull Context context) {
        this(context,0);
    }


    private CommonAlertDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, resolveDialogTheme(context,themeResId));
        this.mAlert = new CommonAlertController(this,getWindow());
    }


    private CommonAlertDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        this(context,0);
        this.setCancelable(cancelable);
        this.setOnCancelListener(cancelListener);
    }

    static int resolveDialogTheme(@NonNull Context context, @StyleRes int resid) {
        if(resid >= 16777216) {
            return resid;
        } else {
            TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.alertDialogTheme, outValue, true);
            return outValue.resourceId;
        }
    }

    public void setText(int resId,CharSequence charSequence){
        mAlert.setText(resId,charSequence);
    }

    public void setOnClickListener(int resId,View.OnClickListener listener){
        mAlert.setOnClickListener(resId,listener);
    }


    public static class Builder{
        private final CommonAlertParams P;
        private final int mTheme;

        public Builder(@NonNull Context context) {
            this(context, CommonAlertDialog.resolveDialogTheme(context, 0));
        }

        public Builder(@NonNull Context context, @StyleRes int themeResId) {
            this.P = new CommonAlertParams(context);
            this.mTheme = themeResId;
        }


        public Builder setContentView(int layoutResId){
            this.P.layoutResID = layoutResId;
            return this;
        }

        public Builder setContentView(View view){
            this.P.mContentView = view;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.P.mCancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener onCancelListener) {
            this.P.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder setOnDismissListener(OnDismissListener onDismissListener) {
            this.P.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder setOnKeyListener(OnKeyListener onKeyListener) {
            this.P.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder setText(int resId,CharSequence charSequence){
            this.P.mTextArray.put(resId,charSequence);
            return this;
        }

        public Builder setOnClickListener(int resId,View.OnClickListener listener){
            this.P.mOnClickListenerArray.put(resId,listener);
            return this;
        }

        public Builder fullWith(){
            this.P.mWindowWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        public Builder fromButtom(boolean isAnim){
            if(isAnim){
                this.P.mAnimation = R.style.commonDialog_fromButtom_anim ;
            }
            this.P.mGravity = Gravity.BOTTOM;
            return this;
        }

        public Builder addDefaultAnimation(){
            this.P.mAnimation = R.style.commonDialog_default_anim ;
            return this;
        }

        public Builder setAnimation(int animation){
            this.P.mAnimation = animation;
            return this;
        }

        public Builder setWidthAndHeight(int width,int height){
            this.P.mWindowWidth = width;
            this.P.mWindowHeight = height;
            return this;
        }

        public CommonAlertDialog create() {
            CommonAlertDialog dialog = new CommonAlertDialog(this.P.mContext, this.mTheme);
            this.P.apply(dialog.mAlert);
            dialog.setCancelable(this.P.mCancelable);
            if(this.P.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }

            dialog.setOnCancelListener(this.P.mOnCancelListener);
            dialog.setOnDismissListener(this.P.mOnDismissListener);
            if(this.P.mOnKeyListener != null) {
                dialog.setOnKeyListener(this.P.mOnKeyListener);
            }

            return dialog;
        }

        public CommonAlertDialog show() {
            CommonAlertDialog dialog = this.create();
            dialog.show();
            return dialog;
        }


    }
}
