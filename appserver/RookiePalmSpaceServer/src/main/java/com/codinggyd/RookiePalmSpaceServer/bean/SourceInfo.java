package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;

/**
 * Created by guoyd on 2016-03-25.
 * 资料区实体对象 文档或网址信息描述实体
 */
public class SourceInfo implements Serializable{

    /**
     * 资料编号
     */
    public Integer id;
    /**
     * 资料标题
     */
    public String name;

    /**
     * 资料类型
     * 1 文档
     * 2 网址
     */
    public int type;

    /**
     * 资料上传地点
     */
    public String location;

    /**
     * 资料上传时间
     */
    public String time;

    /**
     * 资料下载链接 或者网站地址
     */
    public String url;

    /**
     * 资料所属用户编号
     */
    public int userId;

    /**
     * 资料描述
     */
    public String remark;

    public SourceInfo() {
    }

    public SourceInfo(String remark, int userId, String url, String time, String location, int type, String name, Integer id) {
        this.remark = remark;
        this.userId = userId;
        this.url = url;
        this.time = time;
        this.location = location;
        this.type = type;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "SourceInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                ", userId='" + userId + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public interface TableInfo {
        String TABLE_NAME = "SourceInfo";
        String FIELD_ID = "id";
        String FIELD_NAME = "name";
        String FIELD_TYPE = "type";
        String FIELD_TIME = "time";
        String FIELD_LOCATION = "location";
        String FIELD_URL = "url";
        String FIELD_USER_ID = "userId";
        String FIELD_REMARK = "remark";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
