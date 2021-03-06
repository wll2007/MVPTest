package com.mvp.wangll.mvptest.greenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.mvp.wangll.mvptest.Httpframework.fileDownLoad.DownInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DOWN_INFO".
*/
public class DownInfoDao extends AbstractDao<DownInfo, Long> {

    public static final String TABLENAME = "DOWN_INFO";

    /**
     * Properties of entity DownInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Url = new Property(1, String.class, "url", false, "URL");
        public final static Property CountLength = new Property(2, long.class, "countLength", false, "COUNT_LENGTH");
        public final static Property StartIndex = new Property(3, long.class, "startIndex", false, "START_INDEX");
        public final static Property EndIndex = new Property(4, long.class, "endIndex", false, "END_INDEX");
        public final static Property ThreadId = new Property(5, String.class, "threadId", false, "THREAD_ID");
        public final static Property ReadLength = new Property(6, long.class, "readLength", false, "READ_LENGTH");
        public final static Property TotalLength = new Property(7, long.class, "totalLength", false, "TOTAL_LENGTH");
        public final static Property StateInte = new Property(8, int.class, "stateInte", false, "STATE_INTE");
    }


    public DownInfoDao(DaoConfig config) {
        super(config);
    }
    
    public DownInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DOWN_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"URL\" TEXT," + // 1: url
                "\"COUNT_LENGTH\" INTEGER NOT NULL ," + // 2: countLength
                "\"START_INDEX\" INTEGER NOT NULL ," + // 3: startIndex
                "\"END_INDEX\" INTEGER NOT NULL ," + // 4: endIndex
                "\"THREAD_ID\" TEXT," + // 5: threadId
                "\"READ_LENGTH\" INTEGER NOT NULL ," + // 6: readLength
                "\"TOTAL_LENGTH\" INTEGER NOT NULL ," + // 7: totalLength
                "\"STATE_INTE\" INTEGER NOT NULL );"); // 8: stateInte
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_DOWN_INFO_URL_THREAD_ID ON \"DOWN_INFO\"" +
                " (\"URL\" ASC,\"THREAD_ID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DOWN_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DownInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
        stmt.bindLong(3, entity.getCountLength());
        stmt.bindLong(4, entity.getStartIndex());
        stmt.bindLong(5, entity.getEndIndex());
 
        String threadId = entity.getThreadId();
        if (threadId != null) {
            stmt.bindString(6, threadId);
        }
        stmt.bindLong(7, entity.getReadLength());
        stmt.bindLong(8, entity.getTotalLength());
        stmt.bindLong(9, entity.getStateInte());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DownInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(2, url);
        }
        stmt.bindLong(3, entity.getCountLength());
        stmt.bindLong(4, entity.getStartIndex());
        stmt.bindLong(5, entity.getEndIndex());
 
        String threadId = entity.getThreadId();
        if (threadId != null) {
            stmt.bindString(6, threadId);
        }
        stmt.bindLong(7, entity.getReadLength());
        stmt.bindLong(8, entity.getTotalLength());
        stmt.bindLong(9, entity.getStateInte());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DownInfo readEntity(Cursor cursor, int offset) {
        DownInfo entity = new DownInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // url
            cursor.getLong(offset + 2), // countLength
            cursor.getLong(offset + 3), // startIndex
            cursor.getLong(offset + 4), // endIndex
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // threadId
            cursor.getLong(offset + 6), // readLength
            cursor.getLong(offset + 7), // totalLength
            cursor.getInt(offset + 8) // stateInte
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DownInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUrl(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCountLength(cursor.getLong(offset + 2));
        entity.setStartIndex(cursor.getLong(offset + 3));
        entity.setEndIndex(cursor.getLong(offset + 4));
        entity.setThreadId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setReadLength(cursor.getLong(offset + 6));
        entity.setTotalLength(cursor.getLong(offset + 7));
        entity.setStateInte(cursor.getInt(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DownInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DownInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DownInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
