package com.gyd.main.service.impl;

import java.util.Date;
import java.util.List;

import com.gyd.main.bean.Record;
import com.gyd.main.dao.RecordDao;
import com.gyd.main.dao.impl.RecordDaoImpl;
import com.gyd.main.service.RecordService;

public class RecordServiceImpl implements RecordService {

	private RecordDao recordDao = new RecordDaoImpl();

	@Override
	public boolean addRecord(Record record) {
		return recordDao.addRecord(record);
	}

	@Override
	public boolean deleteRecord(Record id) {
		return recordDao.deleteRecord(id);
	}

	@Override
	public boolean updateRecord(Record record) {
		return recordDao.updateRecord(record);
	}

	@Override
	public List<Record> getRecords() {
		return recordDao.getRecords();
	}

	@Override
	public Record getRecordById(Integer id) {
		return recordDao.getRecordById(id);
	}

	@Override
	public List<Record> getRecordsByDate(Date startDate, Date endDate) {
		return recordDao.getRecordByDate(startDate,endDate);
	}
	 
}
