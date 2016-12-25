package com.gyd.rookiepalmspace.base.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.gyd.rookiepalmspace.base.entity.AccountInfo;
import com.gyd.rookiepalmspace.base.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guoyading on 2015-12-24.
 */
public class AccountInfoDb extends BaseDb<AccountInfo> {
    private static final String TAG = "AccountInfoDb";

    public AccountInfoDb(Context context) {
        super(context);
    }

    @Override
    public long insert(AccountInfo accountInfo) {

        long newId = -1;

        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountInfo.TableInfo.FIELD_NAME, accountInfo.name);
        contentValues.put(AccountInfo.TableInfo.FIELD_PASSWORD, accountInfo.password);
        contentValues.put(AccountInfo.TableInfo.FIELD_PLATFORM, accountInfo.platform);
        contentValues.put(AccountInfo.TableInfo.FIELD_PLATFORM_ADDRESS, accountInfo.platformAddress);
        contentValues.put(AccountInfo.TableInfo.FIELD_REMARK, accountInfo.remark);
        contentValues.put(AccountInfo.TableInfo.FIELD_USER_ID, accountInfo.userId);
        contentValues.put(AccountInfo.TableInfo.FIELD_DATE, accountInfo.date);
        if (null != queryById(accountInfo._id)) {
            newId = database.update(AccountInfo.TableInfo.TABLE_NAME, contentValues, AccountInfo.TableInfo.FIELD_ID + "=?", new String[]{accountInfo._id + ""});
            LogUtil.d(TAG, "insert existed so update");
        } else {
            newId = database.insert(AccountInfo.TableInfo.TABLE_NAME, null, contentValues);
            LogUtil.d(TAG, "insert........" + newId);
        }

        return newId;
    }

    @Override
    public int delete(AccountInfo accountInfo) {
        int rowDelete = database.delete(AccountInfo.TableInfo.TABLE_NAME, AccountInfo.TableInfo.FIELD_ID + "=?", new String[]{accountInfo._id + ""});
        LogUtil.d(TAG, rowDelete + "");
        return rowDelete;
    }

    @Override
    public void update(AccountInfo accountInfo) {

    }

    @Override
    public List<AccountInfo> queryAll() {
        List<AccountInfo> data = new ArrayList<>();
        Cursor cursor = database.query(AccountInfo.TableInfo.TABLE_NAME, new String[]{"*"}, null, null, null, null, AccountInfo.TableInfo.FIELD_ID + " desc");
        if (null != cursor) {
            while (cursor.moveToNext()) {

                AccountInfo accountInfo = formBeanFromCursor(cursor);
                data.add(accountInfo);
            }
            cursor.close();
        }
        LogUtil.d(TAG, "queryAll........");
        return data;
    }

    @Override
    public AccountInfo queryById(long id) {
        AccountInfo accountInfo = null;
        Cursor cursor = database.query(AccountInfo.TableInfo.TABLE_NAME, new String[]{"*"}, AccountInfo.TableInfo.FIELD_ID + " = ?", new String[]{id + ""}, null, null, null);

        if (null != cursor && cursor.moveToNext()) {
            accountInfo = formBeanFromCursor(cursor);
        }
        cursor.close();
        return accountInfo;
    }

    private AccountInfo formBeanFromCursor(Cursor cursor) {
        Integer id = cursor.getInt(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_ID));
        String platformAddress = cursor.getString(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_PLATFORM_ADDRESS));
        String remark = cursor.getString(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_REMARK));
        String platform = cursor.getString(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_PLATFORM));
        String password = cursor.getString(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_PASSWORD));
        Integer userId = cursor.getInt(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_USER_ID));
        String name = cursor.getString(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_NAME));
        String date = cursor.getString(cursor.getColumnIndex(AccountInfo.TableInfo.FIELD_DATE));

        return new AccountInfo(id, platformAddress, remark, platform, userId, date, password, name);

    }

}
