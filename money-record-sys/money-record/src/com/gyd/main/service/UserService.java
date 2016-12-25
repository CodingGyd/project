package com.gyd.main.service;
import com.gyd.main.bean.User;

public interface UserService {
	public User checkLogin(String tel, String password);
	public boolean regService(User user);
	public User checkUser(String username);

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
	public User getUser(String name, String password);

	 
	/**
	 * 通过tel得到一个用户对象
	 * 
	 * @param tel
	 *            用户tel
	 * @return
	 */
	public User getUserByTel(String tel);

	 
}
