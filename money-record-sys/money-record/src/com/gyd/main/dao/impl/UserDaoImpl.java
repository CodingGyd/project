package com.gyd.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gyd.main.bean.User;
import com.gyd.main.dao.BaseDao;
import com.gyd.main.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	/**
	 * 新增一个用户
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public boolean addUser(User user) {
		boolean flag = false;
		try {
			Connection connection = getConnection();
			String sql = "insert into mr_userinfo(name,password,tel,address,regdate)"
					+ "values(?,?,?,?,getdate())";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getTel());
			ps.setString(4, user.getAddress());
			int count = ps.executeUpdate();
			if (count > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 通过用户名和密码返回一个匹配的用户对象
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@Override
	public User getUser(String tel, String password) {
		Connection connection;
		User user = null;
		try {
			connection = getConnection();
			String sql = "select * from mr_userinfo "
					+ "where tel =? and password=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, tel);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				user = new User(-1, rs.getString("name"),
						rs.getString("password"), rs.getString("address"),
						rs.getString("tel"), rs.getTimestamp("regdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}


	/**
	 * 通过id得到一个用户对象
	 * 
	 * @param id
	 *            用户id
	 * @return
	 */
	@Override
	public User getUserByTel(String tel) {
		Connection connection;
		User user = null;
		try {
			connection = getConnection();
			String sql = "select * from mr_userinfo where tel=" + tel;
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				user = new User(-1, rs.getString("name"),
						rs.getString("password"), rs.getString("address"),
						rs.getString("tel"), rs.getTimestamp("regdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	

	@Override
	public User getUser(String name) {
		Connection connection;
		User user = null;
		try {
			connection = getConnection();
			String sql = "select * from mr_userinfo "
					+ "where name =?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {

				user = new User(-1, rs.getString("name"),
						rs.getString("password"), rs.getString("address"),
						rs.getString("tel"), rs.getTimestamp("regdate"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
