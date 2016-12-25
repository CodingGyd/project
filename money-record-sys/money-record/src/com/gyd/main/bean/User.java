package com.gyd.main.bean;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 用户类
 * @author Administrator
 *
 */
public class User  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;  //用户id
	private String name;  //用户账号
	private String password;  //用户账号的密码
	private String address; //用户的默认收件地址
	private String tel;  //用户的默认联系方式
	private Timestamp regDate;  //用户注册的时间

	public User() {
		super();
	}

	/**
	 * 构造方法
	 * @param id 用户注册时的id
	 * @param name  用户注册时的昵称
	 * @param password  用户注册时设置的密码
	 * @param address  用户注册时填写的默认地址
	 * @param tel  默认联系方式
	 * @param regDate  注册时间
	 */
	public User(int id, String name, String password, String address,
			String tel, Timestamp regDate) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.tel = tel;
		this.regDate = regDate;
	}

	/**
	 * 得到用户id
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置用户id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 得到用户昵称
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置用户昵称
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 得到用户的密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置用户的密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 得到用户的默认收件地址
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置用户的默认收件地址
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 得到用户默认的联系方式
	 * @return
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * 设置用户默认的联系方式
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * 得到用户注册的时间
	 * @return
	 */
	public Timestamp getRegDate() {
		return regDate;
	}

	/**
	 * 设置用户注册的时间
	 * @param regDate
	 */
	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

}