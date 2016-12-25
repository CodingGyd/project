package com.gyd.rookiepalmspace.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gyd.rookiepalmspace.base.entity.AccountInfo;
import com.gyd.rookiepalmspace.base.entity.ArticleInfo;
import com.gyd.rookiepalmspace.base.entity.SourceInfo;
import com.gyd.rookiepalmspace.base.util.LogUtil;

/**
 * Created by guoyading on 2015-12-24.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "rookiepalmspace.db";
    private static final String TAG = "DbHelper";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "";
        //create AccountInfo
        sql = "CREATE TABLE " + AccountInfo.TableInfo.TABLE_NAME + "(" +
                AccountInfo.TableInfo.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AccountInfo.TableInfo.FIELD_NAME + " TEXT," +
                AccountInfo.TableInfo.FIELD_PASSWORD + " TEXT," +
                AccountInfo.TableInfo.FIELD_PLATFORM + " TEXT," +
                AccountInfo.TableInfo.FIELD_PLATFORM_ADDRESS + " TEXT," +
                AccountInfo.TableInfo.FIELD_REMARK + " TEXT, " +
                AccountInfo.TableInfo.FIELD_DATE + " TEXT," +
                AccountInfo.TableInfo.FIELD_USER_ID + " INTEGER )";
        db.execSQL(sql);

        //create ArticleInfo
        sql = "CREATE TABLE " + ArticleInfo.TableInfo.TABLE_NAME + "(" +
                ArticleInfo.TableInfo.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ArticleInfo.TableInfo.FIELD_CONTENT + " TEXT, " +
                ArticleInfo.TableInfo.FIELD_LOCATION + " TEXT, " +
                ArticleInfo.TableInfo.FIELD_TIME + " TEXT," +
                ArticleInfo.TableInfo.FIELD_TITLE + " TEXT," +
                ArticleInfo.TableInfo.FIELD_TYPE + " TEXT," +
                ArticleInfo.TableInfo.FIELD_USER_ID + " INTEGER )";

        db.execSQL(sql);

        //create SourceInfo
        sql = "CREATE TABLE " + SourceInfo.TableInfo.TABLE_NAME + "(" +
                SourceInfo.TableInfo.FIELD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SourceInfo.TableInfo.FIELD_NAME + " TEXT, " +
                SourceInfo.TableInfo.FIELD_TYPE + " TEXT, " +
                SourceInfo.TableInfo.FIELD_TIME + " TEXT," +
                SourceInfo.TableInfo.FIELD_LOCATION + " TEXT," +
                SourceInfo.TableInfo.FIELD_URL + " TEXT," +
                SourceInfo.TableInfo.FIELD_REMARK + " TEXT," +
                SourceInfo.TableInfo.FIELD_USER_ID + " INTEGER )";

        db.execSQL(sql);

        LogUtil.d(TAG, "onCreate " + sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            this.onCreate(db);
        }
    }
}
