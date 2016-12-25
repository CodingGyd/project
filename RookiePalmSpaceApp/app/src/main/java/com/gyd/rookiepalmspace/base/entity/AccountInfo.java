package com.gyd.rookiepalmspace.base.entity;

import java.io.Serializable;

/**
 * Created by guoyading on 2015-12-24.
 * 账号信息表
 */
public class AccountInfo implements Serializable {

    /**
     * 账号编号
     */
    public long _id;
    /**
     * 账号名称
     */
    public String name;
    /**
     * 账号密码
     */
    public String password;
    /**
     * 添加时间
     */
    public String date;
    /**
     * 用户编号
     */
    public Integer userId;
    /**
     * 备注
     */
    public String remark;
    /**
     * 平台名称
     */
    public String platform;
    /**
     * 平台网址
     */
    public String platformAddress;

    public AccountInfo(long id, String platformAddress, String remark, String platform, Integer userId, String date, String password, String name) {
        this._id = id;
        this.platformAddress = platformAddress;
        this.remark = remark;
        this.platform = platform;
        this.userId = userId;
        this.date = date;
        this.password = password;
        this.name = name;
    }

    public AccountInfo() {

    }

    public interface TableInfo {
        String TABLE_NAME = "AccountInfo";
        String FIELD_ID = "_id";
        String FIELD_NAME = "name";
        String FIELD_PASSWORD = "password";
        String FIELD_DATE = "date";
        String FIELD_USER_ID = "userId";
        String FIELD_REMARK = "remark";
        String FIELD_PLATFORM = "platform";
        String FIELD_PLATFORM_ADDRESS = "platformAddress";
    }
}
