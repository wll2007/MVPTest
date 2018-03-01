package com.mvp.wangll.mvptest.Mvpframework.support.Lce.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.view.View;

import com.mvp.wangll.mvptest.R;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class DefaultLceAnimator implements ILceAnimator {

    @Override
    public void showLoadingView(View loadView, View contentView, View errorView) {
        loadView.setVisibility(View.VISIBLE);
        contentView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showContentView(final View loadView, final View contentView,final View errorView) {
        if(contentView.getVisibility()==View.VISIBLE){
            loadView.setVisibility(View.GONE);
            errorView.setVisibility(View.GONE);
        }else {
            errorView.setVisibility(View.GONE);

            AnimatorSet set = new AnimatorSet();
            final Resources resources = loadView.getResources();
            final int translateInPixels = resources.getDimensionPixelSize(R.dimen.lce_content_view_animation_translate_y);
            ObjectAnimator contentFadeIn = ObjectAnimator.ofFloat(contentView,"alpha",0f,1f);
            ObjectAnimator contentTranslateIn = ObjectAnimator.ofFloat(contentView,"translationY",translateInPixels,0f);
            ObjectAnimator loadFadeOut = ObjectAnimator.ofFloat(loadView,"alpha",1f,0f);
            ObjectAnimator loadTranslateIn = ObjectAnimator.ofFloat(loadView,"translationY",0f,-translateInPixels);
            set.playTogether(contentFadeIn,contentTranslateIn,loadFadeOut,loadTranslateIn);
            set.setDuration(resources.getInteger(R.integer.lce_content_view_show_animation_time));
            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(Animator animator) {
                    contentView.setVisibility(View.VISIBLE);
                    loadView.setTranslationY(0);
                    contentView.setTranslationY(0);

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    loadView.setVisibility(View.GONE);
                    loadView.setTranslationY(0);
                    contentView.setTranslationY(0);
                    loadView.setAlpha(1f);
                }
            });
            set.start();
        }

    }

    @Override
    public void showErrorView(final View loadView, View contentView, final View errorView) {
        contentView.setVisibility(View.GONE);
        AnimatorSet set = new AnimatorSet();
        final Resources resources = loadView.getResources();
        ObjectAnimator in = ObjectAnimator.ofFloat(errorView,"alpha",0f,1f);
        ObjectAnimator loadingOut = ObjectAnimator.ofFloat(loadView,
                "alpha",1f, 0f);
        set.playTogether(in,loadingOut);
        set.setDuration(resources.getInteger(R.integer.lce_error_view_show_animation_time));
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                errorView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                loadView.setVisibility(View.GONE);
                loadView.setAlpha(1f);
            }
        });
    }
}
