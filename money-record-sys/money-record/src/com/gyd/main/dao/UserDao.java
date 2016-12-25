package com.gyd.main.dao;

import com.gyd.main.bean.User;

public interface UserDao {

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user);

	/**
	 * 通过用户名和密码返回一个匹配的用户对象
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	public User getUser(String tel, String password);

	/**
	 * 根据用户账号返回一个匹配的用户对象
	 * @param name
	 * @return
	 */
	public User getUser(String tel);
	 
	/**
	 * 通过id得到一个用户对象
	 * 
	 * @param id
	 *            用户id
	 * @return
	 */
	public User getUserByTel(String tel);

 

	 
}
