package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;

/**
 * Created by guoyading on 2015-12-21.
 */
public class ImageInfo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**图片编号*/
    public Integer id;
    /**图片标题*/
    public String title;
    /**图片类型*/
    public int type;
    /**上传时间*/
    public String time;
    /**上传地点*/
    public String address;
    /**图片备注*/
    public String remark;
    /**下载链接*/
    public String url;
    /**用户编号*/
    public Integer userId;

    public ImageInfo() {
    }

    public ImageInfo(Integer id, Integer userId, String url, String address, String remark, String time, int type, String title) {
        this.id = id;
        this.userId = userId;
        this.url = url;
        this.address = address;
        this.remark = remark;
        this.time = time;
        this.type = type;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	@Override
	public String toString() {
		return "ImageInfo [id=" + id + ", title=" + title + ", type=" + type
				+ ", time=" + time + ", address=" + address + ", remark="
				+ remark + ", url=" + url + ", userId=" + userId + "]";
	}
    
}
