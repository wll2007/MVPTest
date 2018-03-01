package com.mvp.wangll.mvptest.Httpframework.fileDownLoad;

import android.os.Environment;

import com.mvp.wangll.mvptest.Httpframework.exception.ApiException;
import com.mvp.wangll.mvptest.demo.Model.FileModel;
import com.mvp.wangll.mvptest.greenDao.DbManager;
import com.mvp.wangll.mvptest.greenDao.DownInfoDao;
import com.trello.rxlifecycle2.LifecycleProvider;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/12/20 0020.
 */

public class FileDownLoadManager {
    private static String TAG = FileDownLoadManager.class.getName();
    private static FileDownLoadManager INSTANCE;
    private ConcurrentHashMap<String, ConcurrentHashMap<String,Disposable>> subMap;
    private ConcurrentHashMap<String,FileDownLoadListener> listenerHashMap;
    private ConcurrentHashMap<String,Vector<DownInfo>> downInfoMap;
    private ConcurrentHashMap<String,Disposable> getFileLengthMap;
    private ConcurrentHashMap<String,Integer> threadMap;
    private ConcurrentHashMap<String,File> FileMap;
    private static String mDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    private int THREAD_COUNT = 4;//下载线程数量

    private FileDownLoadManager() {
        this.subMap = new ConcurrentHashMap<>();
        listenerHashMap = new ConcurrentHashMap<>();
        downInfoMap = new ConcurrentHashMap<>();
        getFileLengthMap = new ConcurrentHashMap<>();
        threadMap = new ConcurrentHashMap<>();
        FileMap = new ConcurrentHashMap<>();
    }

    public static FileDownLoadManager getInstance(){
        if(INSTANCE==null){
            synchronized (FileDownLoadManager.class){
                if(INSTANCE==null){
                    INSTANCE = new FileDownLoadManager();
                }
            }
        }
        return INSTANCE;
    }

    private void addFile(String url,File file){
        FileMap.put(url,file);
    }

    private void  addThread(String url){
        Integer integer = threadMap.get(url);
        if(integer==null){
            integer = 1;
            threadMap.put(url,integer);
        }else {
            threadMap.put(url, integer+1);
        }
    }

    public void addDisposable(DownInfo info,Disposable d){
        ConcurrentHashMap<String,Disposable> vector = subMap.get(info.getUrl());
        if(vector == null){
            vector = new ConcurrentHashMap<>();
        }
        vector.put(info.getThreadId(),d);
        subMap.put(info.getUrl(),vector);
    }

    public void addGetFileLengthDisposable(String url,Disposable d){
        getFileLengthMap.put(url,d);
    }

    public void removeGetFileLengthDisposable(String url){
        getFileLengthMap.remove(url);
    }


    public void updateProgress(DownInfo info,long count){
        FileDownLoadListener hashMap = listenerHashMap.get(info.getUrl());
        if(hashMap!=null){
            hashMap.updateProgressToUI(count,info.getTotalLength());
        }
    }

    private void addDownloadProgressListener(String url,FileDownLoadListener listener){
        listenerHashMap.put(url,listener);
    }

    private void addDownInfo(DownInfo info){
        Vector<DownInfo> list = downInfoMap.get(info.getUrl());
        if(list == null){
            list = new Vector<>();
        }
        list.add(info);
        downInfoMap.put(info.getUrl(),list);
    }


    public void pause(String url){
        Disposable disposable = getFileLengthMap.get(url);
        if(disposable!=null){
            disposable.dispose();
            getFileLengthMap.remove(url);
            return;
        }

        ConcurrentHashMap<String,Disposable> vector = subMap.get(url);
        if(null!=vector) {
            for (Map.Entry<String, Disposable> entry : vector.entrySet()) {
                entry.getValue().dispose();
            }
        }

        long readLength = 0;
        long TotalLength =0;
        Vector<DownInfo> list = downInfoMap.get(url);
        if(null!=list) {
            for (DownInfo info : list) {
                //更新数据库 Todo
                readLength += info.getReadLength();
                TotalLength = info.getTotalLength();
                if(info.getCountLength()==info.getReadLength()) {
                    info.setState(DownState.FINISH);
                }
                DbManager.getDaoSession().getDownInfoDao().update(info);
            }
        }
        FileDownLoadListener listener = listenerHashMap.get(url);
        if(null!=listener){
            listener.pauseProgressToUI(readLength,TotalLength);
        }
        subMap.remove(url);
        downInfoMap.remove(url);
        listenerHashMap.remove(url);
        getFileLengthMap.remove(url);
        threadMap.remove(url);
    }

    public void remove(String url){

        Disposable disposable = getFileLengthMap.get(url);
        if(disposable!=null){
            disposable.dispose();
            getFileLengthMap.remove(url);
            return;
        }

        ConcurrentHashMap<String,Disposable> vector = subMap.get(url);
        if(null!=vector) {
            for (Map.Entry<String, Disposable> entry : vector.entrySet()) {
                entry.getValue().dispose();
            }
        }


        Vector<DownInfo> list = downInfoMap.get(url);
        if(list!=null&&list.size()>0){
            for (DownInfo downInfo:list) {
                DbManager.getDaoSession().getDownInfoDao().delete(downInfo);
            }
        }

        File localFile = FileMap.get(url);
        if(null!=localFile){
            localFile.delete();
        }


        FileDownLoadListener listener = listenerHashMap.get(url);
        if(null!=listener){
            listener.cancelProgressToUI();
        }

        subMap.remove(url);
        downInfoMap.remove(url);
        listenerHashMap.remove(url);
        getFileLengthMap.remove(url);
        threadMap.remove(url);
        FileMap.remove(url);
    }

    public List<DownInfo> getDownInfosFromDB(String url){
        //从数据库中获取DownInfo Todo
        List<DownInfo> list = DbManager.getDaoSession().getDownInfoDao().queryBuilder().where(DownInfoDao.Properties.Url.eq(url)).list();
        return list;
    }

    public void onComplete(DownInfo info){
        info.setState(DownState.FINISH);
        DbManager.getDaoSession().getDownInfoDao().update(info);
        //删除相关的downInfo
        ConcurrentHashMap<String,Disposable> vector = subMap.get(info.getUrl());
        if(vector!=null){
            vector.remove(info.getThreadId());
        }

        if(vector!=null&&vector.size()==0){
            subMap.remove(info.getUrl());
        }


        Integer integer = threadMap.get(info.getUrl());
        if(integer!=null&&integer!=0){
            integer = integer-1;
            threadMap.put(info.getUrl(),integer);
        }

        if(integer!=null&&integer == 0){
            //最后一个完成
            //删除数据库
            Vector<DownInfo> list = downInfoMap.get(info.getUrl());
            if(list!=null&&list.size()>0){
                for (DownInfo downInfo:list) {
                    DbManager.getDaoSession().getDownInfoDao().delete(downInfo);
                }
                downInfoMap.remove(info.getUrl());
            }
            listenerHashMap.get(info.getUrl()).completeProgressToUI(info.getTotalLength(),info.getTotalLength());
            listenerHashMap.remove(info.getUrl());
            threadMap.remove(info.getUrl());
        }
    }

    private void insertDb(DownInfo info){
        DbManager.getDaoSession().getDownInfoDao().insert(info);
    }

    private void deleteDb(DownInfo info){
        DbManager.getDaoSession().getDownInfoDao().delete(info);
    }



    public <T> void down(final String url, final FileDownLoadListener listener, LifecycleProvider<T> mLifecycleProvider, T mEvent) {
        final FileModel   fileModel = new FileModel<>(mLifecycleProvider, mEvent);

        String[] splt = url.split("/");
        final File mTmpFile = new File(mDir, splt[splt.length - 1]);

        final List<DownInfo> list = FileDownLoadManager.getInstance().getDownInfosFromDB(url);
        if (list != null&&list.size()>0) {
            handelFileDown(list,url,mTmpFile,listener,fileModel);
        } else{

            GetFileLengthCallback callback = new GetFileLengthCallback(url) {
                @Override
                public void complete() {

                }

                @Override
                public void error(ApiException ex) {
                    listener.exceptionDownLoad(ex);
                    FileDownLoadManager.getInstance().removeGetFileLengthDisposable(url);
                }

                @Override
                public void onSuccess(ResponseBody o) {
                    long blockSize = o.contentLength() / THREAD_COUNT;// 计算每个线程理论上下载的数量.
                        /*为每个线程配置并分配任务*/
                    for (int threadId = 0; threadId < THREAD_COUNT; threadId++) {
                        long startIndex = threadId * blockSize; // 线程开始下载的位置
                        long endIndex = (threadId + 1) * blockSize - 1; // 线程结束下载的位置
                        if (threadId == (THREAD_COUNT - 1)) { // 如果是最后一个线程,将剩下的文件全部交给这个线程完成
                            endIndex = o.contentLength() - 1;
                        }

                        DownInfo info = new DownInfo();
                        info.setCountLength(endIndex - startIndex + 1);
                        info.setEndIndex(endIndex);
                        info.setStartIndex(startIndex);
                        info.setThreadId(String.valueOf(threadId));
                        info.setReadLength(0);
                        info.setUrl(url);
                        info.setTotalLength(o.contentLength());
                        info.setStateInte(0);

                        FileDownLoadManager.getInstance().insertDb(info);
                    }

                    List<DownInfo> infoList = FileDownLoadManager.getInstance().getDownInfosFromDB(url);
                    handelFileDown(infoList,url,mTmpFile,listener,fileModel);
                }
            };

            fileModel.getFileLength(url, callback, new Consumer<Disposable>() {
                @Override
                public void accept(Disposable disposable) throws Exception {
                    listener.preProgressToUI();
                }
            });
        }
    }


    private void handelFileDown(List<DownInfo> list, String url, File file, final FileDownLoadListener listener, FileModel fileModel){
        long alreadyProgress =0;

        for (final DownInfo info : list) {
            if (info.getState().equals(DownState.UNFINISH) && info.getReadLength() < info.getCountLength()) {
                alreadyProgress += info.getReadLength();
            }else {
                alreadyProgress += info.getCountLength();
            }
        }

        FileDownLoadManager.getInstance().addFile(url,file);
        FileDownLoadManager.getInstance().addDownloadProgressListener(url, new ProxyProgressListener(alreadyProgress,listener));

        for (final DownInfo info : list) {
            if(info.getState().equals(DownState.UNFINISH) && info.getReadLength()<info.getCountLength()) {
                FileDownLoadManager.getInstance().addDownInfo(info);
                FileDownLoadObserver fileCallback = new FileDownLoadObserver(info) {

                    @Override
                    public void OnComplete(DownInfo info) {
                    }

                    @Override
                    public void error(ApiException ex) {
                        listener.exceptionDownLoad(ex);
                        remove(info.getUrl());
                    }

                    @Override
                    public void success(DownInfo o) {
                        FileDownLoadManager.getInstance().onComplete(o);
                    }

                };
                fileModel.downLoadFile(info.getStartIndex()+info.getReadLength(), info.getEndIndex(), url, fileCallback, info, file, null);
                FileDownLoadManager.getInstance().addThread(url);
            }else {
                FileDownLoadManager.getInstance().deleteDb(info);
            }
        }

    }

}
