package com.gyd.main.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gyd.main.bean.Record;
import com.gyd.main.dao.BaseDao;
import com.gyd.main.dao.RecordDao;

 
public class RecordDaoImpl extends BaseDao<Record> implements RecordDao {

	@Override
	public boolean addRecord(Record record) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int result = 0;
		try {

			try {
				conn = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			sql = "insert into mr_records(price,name,remark) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, record.getPrice());
			pstmt.setString(2, record.getName());
			pstmt.setString(3, record.getRemark());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteRecord(Record r) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			try {
				conn = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn
					.prepareStatement("delete from mr_records where id=" + r.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateRecord(Record record) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			try {
				conn = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn  
					.prepareStatement("update mr_records set name=?,price=?,remark=? where id=?");
			pstmt.setString(1, record.getName());
			pstmt.setDouble(2, record.getPrice());
			pstmt.setString(3, record.getRemark());
			pstmt.setInt(4, record.getId());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<Record> getRecords() {
		List<Record> lists = new ArrayList<Record>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				conn = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement("select *from mr_records");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Record c = new Record();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setPrice(rs.getDouble("price"));
				c.setRemark(rs.getString("remark"));
				c.setSpendtime(rs.getDate("spendtime"));
				lists.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public Record getRecordById(Integer id) {
		Record c = null;
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			try {
				conn = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement("select *from mr_records where id="
					+ id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new Record();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setPrice(rs.getDouble("price"));
				c.setRemark(rs.getString("remark"));
				c.setSpendtime(rs.getDate("spendtime"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Record> getRecordByDate(Date startDate, Date endDate) {
		List<Record> lists = new ArrayList<Record>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			try {
				conn = getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			pstmt = conn.prepareStatement("select *from mr_records where spendtime >= ? and spendtime<=?");
			pstmt.setObject(1, startDate);
			pstmt.setObject(2, endDate);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Record c = new Record();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setPrice(rs.getDouble("price"));
				c.setRemark(rs.getString("remark"));
				c.setSpendtime(rs.getDate("spendtime"));
				lists.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}
 
}
