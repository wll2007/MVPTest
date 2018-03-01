package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownloadProgressListener;
import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 *  @作者 wll
 *  @日期 2018/1/9 0009
 *  @desc 读取网络字节写入到文件中,更新下载字节数到Observer
 */
public class FileWriteFunction implements Function<ResponseBody, DownInfo> {
    private File mDownloadFile;
    private DownInfo mInfo;
    private long mReadLength =0;
    private DownloadProgressListener progressListener;

    public FileWriteFunction(File mDownloadFile, DownInfo mInfo,DownloadProgressListener listener) {
        this.mDownloadFile = mDownloadFile;
        this.mInfo = mInfo;
        mReadLength = mInfo.getReadLength();
        progressListener = listener;
    }

    @Override
    public DownInfo apply(@NonNull ResponseBody o) throws Exception {
        writeCache(o,mDownloadFile,mInfo);
        return mInfo;
    }

    private void writeCache(ResponseBody responseBody, File file, DownInfo info) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        long allLength = info.getCountLength()-mReadLength;
        FileChannel channelOut = null;
        RandomAccessFile randomAccessFile = null;
        randomAccessFile = new RandomAccessFile(file, "rwd");
        channelOut = randomAccessFile.getChannel();
        MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                info.getStartIndex()+mReadLength,allLength);
        byte[] buffer = new byte[1024*8];
        int len;
        int record = 0;
        while ((len=getlen(responseBody,buffer))!= -1) {
            mappedBuffer.put(buffer, 0, len);
            record += len;
            if(null!=progressListener){
                progressListener.update(len,record);
            }
        }

        responseBody.byteStream().close();
        if (channelOut != null) {
            channelOut.close();
        }
        if (randomAccessFile != null) {
            randomAccessFile.close();
        }
    }

    private int getlen(ResponseBody responseBody,byte[] buffer) throws IOException {
        int len = 0;
        len = responseBody.byteStream().read(buffer);
        return len;
    }
}
