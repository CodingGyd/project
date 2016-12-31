package com.gyd.main.service;
import java.util.Date;
import java.util.List;

import com.gyd.main.bean.Record;

public interface RecordService {
 
	public boolean addRecord(Record record);

	public boolean deleteRecord(Record id);

 
	public boolean updateRecord(Record record);
 
	public List<Record> getRecords();
	
	public List<Record> getRecordsByDate(Date startDate,Date endDate);

 
	public Record getRecordById(Integer id);
	 

}
