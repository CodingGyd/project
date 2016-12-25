package com.gyd.rookiepalmspace.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by guoyading on 2015-12-24.
 * 基础数据库
 */
public abstract class BaseDb<T> {
    public DbHelper mDbHelper;
    public SQLiteDatabase database;

    public BaseDb(Context context) {
        this.mDbHelper = new DbHelper(context);
        this.database = mDbHelper.getWritableDatabase();
    }

    public abstract long insert(T t);

    public abstract int delete(T t);

    public abstract void update(T t);

    public abstract List<T> queryAll();

    public abstract  T queryById(long id);
}
