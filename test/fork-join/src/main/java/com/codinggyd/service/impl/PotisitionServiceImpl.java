package com.codinggyd.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codinggyd.TaskSave;
import com.codinggyd.bean.PositionInfo;
import com.codinggyd.mapper.PotisionInfoMapper;
import com.codinggyd.service.IPotisitionService;
 
/**
 * 
 * @Title:  PotisitionServiceImpl.java
 * @Package: com.codinggyd.service.impl
 * @Description: 测试大批量数据导入数据库
 *
 * @author: guoyd
 * @Date: 2017年2月16日下午4:09:47
 *
 * Copyright @ 2017 Corpration Name
 */
@Service
public class PotisitionServiceImpl implements IPotisitionService{
	@Autowired
	private PotisionInfoMapper mapper;
	final Logger logger = LoggerFactory.getLogger(getClass());

	
	@Override
	public Integer save() {
		long start = System.currentTimeMillis();
//
		List<PositionInfo> list = createList(1000000);
		batchSave(list, 100);
		logger.info("=====入库时间2====="+(System.currentTimeMillis()-start));

		forkJoin();
		return 0;
	}
	
	private void forkJoin(){
		long start = System.currentTimeMillis();
		List<PositionInfo> list = createList(1000000);
		
		//使用fork-join框架的并行计算方式进行入库////////
		TaskSave task = new TaskSave(list, 0, list.size(),mapper);
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.execute(task);
		/////////////////////////////
		
		do {
//			System.out.printf("Main: Thread Count: %d\n",forkJoinPool.getActiveThreadCount());
//			System.out.printf("Main: Thread Steal: %d\n",forkJoinPool.getStealCount());
//			System.out.printf("Main: Parallelism: %d\n",forkJoinPool.getParallelism());
//			try {
//				TimeUnit.MICROSECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		} while (!task.isDone());
		
		forkJoinPool.shutdown();
		
		if (task.isCompletedNormally()) {
			System.out.printf("Main: The process has completed normally.\n");
		}
		logger.info("=====入库时间====="+(System.currentTimeMillis()-start));
	
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
	
	/**
	 * 造数据
	 * @param size
	 * @return
	 */
	private List<PositionInfo> createList(int size){
		List<PositionInfo> list = new ArrayList<>();
		for (int i = 1; i <= size; i++) {
			PositionInfo info = new PositionInfo();
			info.setBufFee(1.2);
			info.setBuyAmout(1000D);
			info.setBuyBalance(89D);
			info.setFlag(1);
			info.setFilename("测试数据大批量导入"+i);
			info.setPrice("1");
			info.setPositionFlag("1");
			info.setTurnInvest(100D);
			info.setOnlineAmount(100000D);
			info.setOfflineAmout(100000D);
			info.setSaleFee(1D);
			info.setBufFee(2D);
			info.setSaleAmout(2345D);
			info.setMarketValue(88D);
			info.setCurrentAmount(2333D);
			info.setMarketId("1");
			info.setSecucode("100001");
			info.setCombiId(2);
			info.setFundcode("163810");
			info.setlDate(1233D);
			list.add(info);
		}
		return list;
	}
}
