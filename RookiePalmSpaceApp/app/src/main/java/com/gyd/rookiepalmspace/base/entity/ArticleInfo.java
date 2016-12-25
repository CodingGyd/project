package com.gyd.rookiepalmspace.base.entity;

import java.io.Serializable;

/**
 * Created by guoyading on 2015-12-21.
 */

public class ArticleInfo implements Serializable {

    /**
     * 文章编号
     */
    public Integer id;
    /**
     * 用户编号
     */
    public Integer userId;
    /**
     * 文章标题
     * 1.技术
     * 2.生活
     */
    public String title;
    /**
     * 发表地址
     */
    public String location;
    /**
     * 文章类型
     */
    public String type;
    /**
     * 文章内容
     */
    public String content;
    /**
     * 发表时间
     */
    public String time;

    /**
     * 下载链接
     */
    public String url;

    public ArticleInfo(Integer id, String time, String content, String type, String location, String title, Integer userId) {
        this.id = id;
        this.time = time;
        this.content = content;
        this.type = type;
        this.location = location;
        this.title = title;
        this.userId = userId;
    }

    public ArticleInfo() {
    }

    public interface TableInfo {
        String TABLE_NAME = "ArticleInfo";
        String FIELD_ID = "_id";
        String FIELD_USER_ID = "userId";
        String FIELD_TIME = "time";
        String FIELD_CONTENT = "content";
        String FIELD_TYPE = "type";
        String FIELD_LOCATION = "location";
        String FIELD_TITLE = "title";
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getUrl() {
        return url;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
