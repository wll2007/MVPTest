package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

/**
 * 下载状态
 * Created by WZG on 2016/10/21.
 */

public enum DownState {
    UNFINISH(0),
    FINISH(1);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownState(int state) {
        this.state = state;
    }
}
