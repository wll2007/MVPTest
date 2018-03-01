package com.mvp.wangll.mvptest.Httpframework;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
/**
 *  @作者 wll
 *  @日期 2017/12/6 0006
 *  @desc  检查网络状态的工具类
 */
public class NetworkUtils {

    /**
     * 获取正在活动的网络信息
     * @param context
     * @return
     */
    public static NetworkInfo getActiveNetworkInfo(Context context){
       ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    /**
     * 获取网络是否可用
     * @param context
     * @return
     */
    public static boolean isAvailable(Context context){
        NetworkInfo info =getActiveNetworkInfo(context);
        return info!=null?info.isAvailable():false;
    }
}
