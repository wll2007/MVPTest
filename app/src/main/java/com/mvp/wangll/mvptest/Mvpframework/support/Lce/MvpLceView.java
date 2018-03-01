package com.mvp.wangll.mvptest.Mvpframework.support.Lce;

import com.mvp.wangll.mvptest.Mvpframework.base.MvpView;

/**
 * Created by Administrator on 2017/10/19 0019.
 */

public interface MvpLceView extends MvpView {

    /**
     * 显示Loading页面
     * 一个页面分为两种类型
     * 一种：下拉刷新组件类型->自带加载页面
     * 一种：需要添加加载页面
     * @param isPullRefresh 是否是下拉刷新组件
     */
    void showLoading(boolean isPullRefresh);


    /**
     * 显示Content页面
     * 一个页面分为两种类型
     * 一种：下拉刷新组件类型->自带加载页面
     * 一种：需要添加加载页面
     * @param isPullRefresh 是否是下拉刷新组件
     */
    void showContent(boolean isPullRefresh);

    /**
     * 显示Error页面
     * 一个页面分为两种类型
     * 一种：下拉刷新组件类型->自带加载页面
     * 一种：需要添加加载页面
     * @param isPullRefresh 是否是下拉刷新组件
     */
    void showError(boolean isPullRefresh);


    void loadData(boolean isPullRefresh);
}
