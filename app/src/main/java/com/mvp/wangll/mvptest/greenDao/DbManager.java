package com.mvp.wangll.mvptest.greenDao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class DbManager {

    // 是否加密
    public static final boolean ENCRYPTED = true;

    private static final String DB_NAME = "test.db";
    private static DbManager mDbManager;
    private static DaoMaster.DevOpenHelper mDevOpenHelper;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;


    private DbManager(Context context) {
        // 初始化数据库信息
        mDevOpenHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        createrDaoMaster(context);
        createrDaoSession(context);
    }

    public static void init(Context context) {
        if (null == mDbManager) {
            synchronized (DbManager.class) {
                if (null == mDbManager) {
                    mDbManager = new DbManager(context);
                }
            }
        }
    }

    /**
     * 获取可读数据库
     *
     * @return
     */
    public static SQLiteDatabase getReadableDatabase() {
        return mDevOpenHelper.getReadableDatabase();
    }

    /**
     * 获取可写数据库
     *
     * @return
     */
    public static SQLiteDatabase getWritableDatabase() {
        return mDevOpenHelper.getWritableDatabase();
    }

    /**
     * 创建DaoMaster
     *
     * 判断是否存在数据库，如果没有则创建数据库
     * @param context
     * @return
     */
    private static void createrDaoMaster(Context context) {
        if (null == mDaoMaster) {
            synchronized (DbManager.class) {
                if (null == mDaoMaster) {
                    MyOpenHelper helper = new MyOpenHelper(context, DB_NAME, null);
                    mDaoMaster = new DaoMaster(helper.getWritableDatabase());
                }
            }
        }
    }

    /**
     * 创建DaoSession
     *
     * @param context
     * @return
     */
    private static void createrDaoSession(Context context) {
        if (null == mDaoSession) {
            synchronized (DbManager.class) {
                mDaoSession = getDaoMaster().newSession();
            }
        }
    }

    public static DaoMaster getDaoMaster(){
        return mDaoMaster;
    }

    public static DaoSession getDaoSession(){
        return mDaoSession;
    }

}