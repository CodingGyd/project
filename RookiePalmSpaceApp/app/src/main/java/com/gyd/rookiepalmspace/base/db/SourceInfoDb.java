package com.gyd.rookiepalmspace.base.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.gyd.rookiepalmspace.base.entity.SourceInfo;
import com.gyd.rookiepalmspace.base.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyd on 2016-03-25.
 */
public class SourceInfoDb extends BaseDb<SourceInfo> {

    public SourceInfoDb(Context context) {
        super(context);
    }

    @Override
    public long insert(SourceInfo sourceInfo) {
        if (null != sourceInfo.id) {
            update(sourceInfo);
            return sourceInfo.id;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(SourceInfo.TableInfo.FIELD_NAME, sourceInfo.name);
        contentValues.put(SourceInfo.TableInfo.FIELD_TYPE, sourceInfo.type);
        contentValues.put(SourceInfo.TableInfo.FIELD_TIME, sourceInfo.time);
        contentValues.put(SourceInfo.TableInfo.FIELD_LOCATION, sourceInfo.location);
        contentValues.put(SourceInfo.TableInfo.FIELD_URL, sourceInfo.url);
        contentValues.put(SourceInfo.TableInfo.FIELD_USER_ID, sourceInfo.userId);
        contentValues.put(SourceInfo.TableInfo.FIELD_REMARK, sourceInfo.remark);

        long newId = database.insert(SourceInfo.TableInfo.TABLE_NAME, null, contentValues);
        return newId;
    }

    @Override
    public int delete(SourceInfo sourceInfo) {
        int result = database.delete(SourceInfo.TableInfo.TABLE_NAME,
                SourceInfo.TableInfo.FIELD_ID + "= ?",
                new String[]{sourceInfo.id + ""});

        return result;
    }

    @Override
    public void update(SourceInfo sourceInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SourceInfo.TableInfo.FIELD_NAME, sourceInfo.name);
        contentValues.put(SourceInfo.TableInfo.FIELD_TYPE, sourceInfo.type);
        contentValues.put(SourceInfo.TableInfo.FIELD_TIME, sourceInfo.time);
        contentValues.put(SourceInfo.TableInfo.FIELD_LOCATION, sourceInfo.location);
        contentValues.put(SourceInfo.TableInfo.FIELD_URL, sourceInfo.url);
        contentValues.put(SourceInfo.TableInfo.FIELD_USER_ID, sourceInfo.userId);
        contentValues.put(SourceInfo.TableInfo.FIELD_REMARK, sourceInfo.remark);

        database.update(SourceInfo.TableInfo.TABLE_NAME,
                contentValues,
                SourceInfo.TableInfo.FIELD_ID + " = ? ",
                new String[]{sourceInfo.id + ""});
    }

    @Override
    public List<SourceInfo> queryAll() {

        List<SourceInfo> sourceInfos = new ArrayList<>();

        Cursor cursor = database.query(SourceInfo.TableInfo.TABLE_NAME, new String[]{"*"}, null, null, null, null, SourceInfo.TableInfo.FIELD_ID + "desc");
        if (null != cursor) {
            while (cursor.moveToNext()) {
                sourceInfos.add(formBeanFromCursor(cursor));
            }
        }
        return sourceInfos;
    }

    public List<SourceInfo> queryByType(String type) {

        List<SourceInfo> sourceInfos = new ArrayList<>();
        Cursor cursor = database.query(SourceInfo.TableInfo.TABLE_NAME, new String[]{"*"}, SourceInfo.TableInfo.FIELD_TYPE + "=?", new String[]{type + ""}, null, null, null, null);
        if (null != cursor) {
            while (cursor.moveToNext()) {
                sourceInfos.add(formBeanFromCursor(cursor));
            }
        }
        LogUtil.e("queryByType",sourceInfos.size()+"===="+sourceInfos);
        return sourceInfos;
    }

    @Override
    public SourceInfo queryById(long id) {
        Cursor cursor = database.query(SourceInfo.TableInfo.TABLE_NAME, new String[]{"*"}, SourceInfo.TableInfo.FIELD_ID + "=?", new String[]{id + ""}, null, null, null, null);
        if (null != cursor && cursor.moveToNext()) {
            return formBeanFromCursor(cursor);
        }
        return null;
    }

    private SourceInfo formBeanFromCursor(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_ID));
        String remark = cursor.getString(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_REMARK));
        int userId = cursor.getInt(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_USER_ID));
        String url = cursor.getString(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_URL));
        String time = cursor.getString(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_TIME));
        String location = cursor.getString(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_LOCATION));
        int type = cursor.getInt(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_TYPE));
        String name = cursor.getString(cursor.getColumnIndex(SourceInfo.TableInfo.FIELD_NAME));

        return new SourceInfo(remark, userId, url, time, location, type, name, id);
    }

}
