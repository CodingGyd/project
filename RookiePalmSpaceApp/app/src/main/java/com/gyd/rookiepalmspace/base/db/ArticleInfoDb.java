package com.gyd.rookiepalmspace.base.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gyd.rookiepalmspace.base.entity.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyading on 2016-01-24.
 */
public class ArticleInfoDb extends BaseDb<ArticleInfo> {

    public ArticleInfoDb(Context context) {
        super(context);
    }

    @Override
    public long insert(ArticleInfo articleInfo) {
        if(null != articleInfo.id ){
            update(articleInfo);
            return articleInfo.id;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArticleInfo.TableInfo.FIELD_LOCATION, articleInfo.location);
        contentValues.put(ArticleInfo.TableInfo.FIELD_CONTENT, articleInfo.content);
        contentValues.put(ArticleInfo.TableInfo.FIELD_TIME, articleInfo.time);
        contentValues.put(ArticleInfo.TableInfo.FIELD_TITLE, articleInfo.title);
        contentValues.put(ArticleInfo.TableInfo.FIELD_TYPE, articleInfo.type);
        contentValues.put(ArticleInfo.TableInfo.FIELD_USER_ID, articleInfo.userId);

        long newId = database.insert(ArticleInfo.TableInfo.TABLE_NAME, null, contentValues);
        return newId;
    }

    @Override
    public int delete(ArticleInfo articleInfo) {
        int result = database.delete(ArticleInfo.TableInfo.TABLE_NAME,
                ArticleInfo.TableInfo.FIELD_ID + "= ?",
                new String[]{articleInfo.id + ""});

        return result;
    }

    @Override
    public void update(ArticleInfo articleInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ArticleInfo.TableInfo.FIELD_LOCATION, articleInfo.location);
        contentValues.put(ArticleInfo.TableInfo.FIELD_CONTENT, articleInfo.content);
        contentValues.put(ArticleInfo.TableInfo.FIELD_TIME, articleInfo.time);
        contentValues.put(ArticleInfo.TableInfo.FIELD_TITLE, articleInfo.title);
        contentValues.put(ArticleInfo.TableInfo.FIELD_TYPE, articleInfo.type);
        contentValues.put(ArticleInfo.TableInfo.FIELD_USER_ID, articleInfo.userId);

        database.update(ArticleInfo.TableInfo.TABLE_NAME,
                contentValues,
                ArticleInfo.TableInfo.FIELD_ID + " = ? ",
                new String[]{articleInfo.id + ""});
    }

    @Override
    public List<ArticleInfo> queryAll() {

        List<ArticleInfo> articleInfos = new ArrayList<>();

        Cursor cursor = database.query(ArticleInfo.TableInfo.TABLE_NAME, new String[]{"*"}, null, null, null, null, ArticleInfo.TableInfo.FIELD_ID + "desc");
        if( null != cursor ){
            while(cursor.moveToNext()){
                articleInfos.add(formBeanFromCursor(cursor));
            }
        }
        return articleInfos;
    }

    public List<ArticleInfo>  queryByType(String type) {

        List<ArticleInfo> articleInfos = new ArrayList<>();

        Cursor cursor = database.query(ArticleInfo.TableInfo.TABLE_NAME,new String[]{"*"},ArticleInfo.TableInfo.FIELD_TYPE+"=?",new String[]{type+""},null,null,null,null);
        if( null != cursor ){
            while(cursor.moveToNext()){
                articleInfos.add(formBeanFromCursor(cursor));
            }
        }
        return articleInfos;
    }

    @Override
    public ArticleInfo queryById(long id) {
        Cursor cursor = database.query(ArticleInfo.TableInfo.TABLE_NAME,new String[]{"*"},ArticleInfo.TableInfo.FIELD_ID+"=?",new String[]{id+""},null,null,null,null);
        if( null != cursor && cursor.moveToNext()){
            return formBeanFromCursor(cursor);
        }
        return null;
    }

    private ArticleInfo formBeanFromCursor(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_ID));
        String location = cursor.getString(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_LOCATION));
        String content = cursor.getString(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_CONTENT));
        String time = cursor.getString(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_TIME));
        String title = cursor.getString(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_TITLE));
        String type = cursor.getString(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_TYPE));
        int userId = cursor.getInt(cursor.getColumnIndex(ArticleInfo.TableInfo.FIELD_USER_ID));

        return new ArticleInfo(id, time, content, type, location, title, userId);
    }


}
