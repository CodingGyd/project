package com.codinggyd;

import java.util.List;
import java.util.concurrent.RecursiveAction;

import com.codinggyd.bean.PositionInfo;
import com.codinggyd.mapper.PotisionInfoMapper;

public class TaskSave extends RecursiveAction{

	private PotisionInfoMapper mapper;
	/**
	 * 
	 */
	private static final long serialVersionUID = -2597112800062738137L;

	private List<PositionInfo> list;
	
	private int start;
	private int end;
	
	public TaskSave(List<PositionInfo> list,int start,int end,PotisionInfoMapper mapper){
		this.list = list;
		this.start = start;
		this.end = end;
		this.mapper = mapper;
	}
	
	@Override
	protected void compute() {
		if (end - start <= 1000) {
			list = list.subList(start, end);
			batchSave(list, 100);
		} else{
			int middle = (end+start)/2;

			TaskSave task1 = new TaskSave(list,start,middle,mapper);
			TaskSave task2 = new TaskSave(list,middle,end,mapper);
			invokeAll(task1,task2);
		}
	}

	private Integer batchSave(List<PositionInfo> list,int batchSize){
		int result = 0;
		
		int batchRecordCount = list.size();
		if (batchRecordCount > batchSize) {
			int batchCount = (batchRecordCount + batchSize - 1) / batchSize;
			for (int i = 0; i < batchCount - 1; i++) {
				List<PositionInfo> batchRows = list.subList(i * batchSize, i * batchSize + batchSize);
				mapper.save(batchRows);
			}
			List<PositionInfo> batchRows = list.subList((batchCount - 1) * batchSize, list.size());
			mapper.save(batchRows);
		} else {
			 mapper.save(list);
		}
		return result;
	}
}
