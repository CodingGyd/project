package com.codinggyd.RookiePalmSpaceServer.bean;

import java.io.Serializable;

/**
 * 
 * @author guoyd
 *
 */
public class UserInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
    public Integer id;
 
	/**
	 * 用户登陆密码
	 */
    public String password;
	/**
	 * 电话号码
	 */
    public String phone;
	/**
	 * 用户注册时间
	 */
    public String registerTime;
	/**
	 * 最近登陆时间
	 */
    public String recentLoginTime;
	/**
	 * 登陆次数
	 */
    public int loginCount;
	/**
	 * 性别
	 */
    public String sex;
	/**
	 * 用户头像下载链接
	 */
    public String icon;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getRecentLoginTime() {
		return recentLoginTime;
	}
	public void setRecentLoginTime(String recentLoginTime) {
		this.recentLoginTime = recentLoginTime;
	}
	public int getLoginCount() {
		return loginCount;
	}
	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public UserInfo(Integer id, String password, String phone,
			String registerTime, String recentLoginTime, int loginCount,
			String sex, String icon) {
		super();
		this.id = id;
		this.password = password;
		this.phone = phone;
		this.registerTime = registerTime;
		this.recentLoginTime = recentLoginTime;
		this.loginCount = loginCount;
		this.sex = sex;
		this.icon = icon;
	}
	public UserInfo() {
		super();
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", password=" + password + ", phone="
				+ phone + ", registerTime=" + registerTime
				+ ", recentLoginTime=" + recentLoginTime + ", loginCount="
				+ loginCount + ", sex=" + sex + ", icon=" + icon + "]";
	}
	 
    
    

}
