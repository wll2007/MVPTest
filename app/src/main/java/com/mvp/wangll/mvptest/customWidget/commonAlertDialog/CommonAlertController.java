package com.mvp.wangll.mvptest.customWidget.commonAlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/1/24 0024.
 */
class CommonAlertController {
    private final CommonAlertDialog mDialog;
    private final Window mWindow;
    private ViewHelper mViewHelper;

    public CommonAlertController(CommonAlertDialog mDialog, Window mWindow) {
        this.mDialog = mDialog;
        this.mWindow = mWindow;
    }

    public CommonAlertDialog getDialog() {
        return mDialog;
    }

    public Window getWindow() {
        return mWindow;
    }

    public void setViewHelper(ViewHelper mViewHelper) {
        this.mViewHelper = mViewHelper;
    }

    public void setText(int resId,CharSequence charSequence){
        mViewHelper.setText(resId,charSequence);
    }

    public void setOnClickListener(int resId,View.OnClickListener listener){
        mViewHelper.setOnClickListener(resId,listener);
    }


    public static class CommonAlertParams {

        public final Context mContext;
        public boolean mCancelable = true;
        public View mContentView;
        public int layoutResID;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public SparseArray<CharSequence> mTextArray;
        public SparseArray<View.OnClickListener> mOnClickListenerArray;
        public int mWindowWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mWindowHeight= ViewGroup.LayoutParams.WRAP_CONTENT;
        public int mGravity  = Gravity.CENTER;
        public int mAnimation;


        public CommonAlertParams(Context mContext) {
            this.mContext = mContext;
            this.mOnClickListenerArray = new SparseArray<>();
            this.mTextArray = new SparseArray<>();
        }

        public void apply(CommonAlertController mAlert) {

            ViewHelper viewHelper = null;
            if(layoutResID!=0){
                viewHelper = new ViewHelper(mContext,layoutResID);
            }

            if(mContentView!=null){
                viewHelper = new ViewHelper();
                viewHelper.setContentView(mContentView);
            }

            if(null==viewHelper){
                throw new RuntimeException("请设置viewHelper的contentView!");
            }

            mAlert.getDialog().setContentView(viewHelper.getContentView());

            for (int i = 0; i < mTextArray.size(); i++) {
                viewHelper.setText(mTextArray.keyAt(i),mTextArray.valueAt(i));
            }

            for (int i = 0; i < mOnClickListenerArray.size(); i++) {
                viewHelper.setOnClickListener(mOnClickListenerArray.keyAt(i),mOnClickListenerArray.valueAt(i));
            }

            mAlert.setViewHelper(viewHelper);

            Window window = mAlert.getWindow();
            window.setGravity(mGravity);
            if(mAnimation != 0) {
                window.setWindowAnimations(mAnimation);
            }

            WindowManager.LayoutParams params =window.getAttributes();
            params.height = mWindowHeight;
            params.width = mWindowWidth;
            window.setAttributes(params);

        }
    }
}
