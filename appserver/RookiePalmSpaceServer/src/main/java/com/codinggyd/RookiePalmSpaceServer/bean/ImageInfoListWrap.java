package com.codinggyd.RookiePalmSpaceServer.bean;

import java.util.List;

public class ImageInfoListWrap {

	 
	public String status ;
	public List<ImageInfo> data;
	@Override
	public String toString() {
		return "ImageInfoListWrap [status=" + status + ", data=" + data + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ImageInfo> getData() {
		return data;
	}
	public void setData(List<ImageInfo> data) {
		this.data = data;
	}
}
