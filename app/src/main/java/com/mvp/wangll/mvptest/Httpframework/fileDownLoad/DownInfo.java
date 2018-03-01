package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Unique;

/**
 *  @作者 Administrator
 *  @日期 2018/1/10 0010
 *  @desc 下载任务的bean(很遗憾,没有将数据从DB中解耦出来)
 */
@Entity(indexes = {@Index (value = "url,threadId" ,unique = true)})
public class DownInfo {
    @Id(autoincrement = true)
    private Long id;

    private String url = "";

    private long countLength;

    private long startIndex;

    private long endIndex;

    private String threadId ="";

    private long readLength;

    private long totalLength;
    private int stateInte;

    public DownInfo(String url) {
        this.url = url;
    }


    @Keep
    public DownInfo(long id, String url, long countLength, long startIndex,
            long endIndex, String threadId,long readLength,long totalLenth) {
        this.id = id;
        this.url = url;
        this.countLength = countLength;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
        this.readLength =readLength;
        this.totalLength = totalLenth;
    }

    @Generated(hash = 1964293517)
    public DownInfo(Long id, String url, long countLength, long startIndex,
            long endIndex, String threadId, long readLength, long totalLength,
            int stateInte) {
        this.id = id;
        this.url = url;
        this.countLength = countLength;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.threadId = threadId;
        this.readLength = readLength;
        this.totalLength = totalLength;
        this.stateInte = stateInte;
    }


    @Generated(hash = 928324469)
    public DownInfo() {
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCountLength() {
        return countLength;
    }

    public void setCountLength(long countLength) {
        this.countLength = countLength;
    }

    public long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public long getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(long endIndex) {
        this.endIndex = endIndex;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getReadLength() {
        return readLength;
    }

    public void setReadLength(long readLength) {
        this.readLength = readLength;
    }


    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }


    public DownState getState() {
        switch (getStateInte()){
            case 1:
                return DownState.FINISH;
            default:
                return DownState.UNFINISH;
        }
    }

    public void setState(DownState state) {
        setStateInte(state.getState());
    }

    public int getStateInte() {
        return stateInte;
    }

    public void setStateInte(int stateInte) {
        this.stateInte = stateInte;
    }


    @Override
    public String toString() {
        return "Id:"+id+"->"+
                "url:"+url+"->"+
                "countLength:"+countLength+"->"+
                "startIndex:"+startIndex+"->"+
                "endIndex:"+endIndex+"->"+
                "threadId:"+threadId+"->"+
                "readLength:"+readLength+"->"+
                "totalLength:"+totalLength+"->"+
                "stateInte:"+stateInte;
    }
}
