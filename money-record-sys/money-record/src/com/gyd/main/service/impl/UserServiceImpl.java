package com.gyd.main.service.impl;

import com.gyd.main.bean.User;
import com.gyd.main.dao.UserDao;
import com.gyd.main.dao.impl.UserDaoImpl;
import com.gyd.main.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao = new UserDaoImpl();
	@Override
	public User checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		User user=userDao.getUser(username, password);
		return user;
	}

	@Override
	public boolean regService(User user) {
		boolean flag=userDao.addUser(user);
		return flag;
	}

	@Override
	public User checkUser(String username) {
		// TODO Auto-generated method stub
		User user=userDao.getUser(username);
		return user;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}

	@Override
	public User getUser(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.getUser(name, password);
	}

	 
	@Override
	public User getUserByTel(String tel) {
		// TODO Auto-generated method stub
		return userDao.getUserByTel(tel);
	}

}
