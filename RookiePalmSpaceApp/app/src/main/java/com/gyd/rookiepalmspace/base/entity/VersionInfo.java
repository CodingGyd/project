package com.gyd.rookiepalmspace.base.entity;

import java.io.Serializable;

/**
 * Created by guoyading on 2016/5/13.
 */
public class VersionInfo implements Serializable{
	
	public int id;
	
	public String time;
	
    /**新版本号*/
	public int versionCode;

    /**新版本名称*/
	public String versionName;
    /**新版本描述*/
	public String  content;
    /**新版本下载地址*/
	public String downloadUrl;

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	 
    public VersionInfo(int id, String time, int versionCode,
			String versionName, String content, String downloadUrl) {
		super();
		this.id = id;
		this.time = time;
		this.versionCode = versionCode;
		this.versionName = versionName;
		this.content = content;
		this.downloadUrl = downloadUrl;
	}

	@Override
	public String toString() {
		return "VersionInfo [id=" + id + ", time=" + time + ", versionCode="
				+ versionCode + ", versionName=" + versionName + ", content="
				+ content + ", downloadUrl=" + downloadUrl + "]";
	}

	public VersionInfo() {

    }
}
