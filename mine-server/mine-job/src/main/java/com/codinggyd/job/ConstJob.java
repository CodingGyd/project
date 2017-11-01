package com.codinggyd.job;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.codinggyd.bean.SysConst2;
import com.codinggyd.redis.ICacheKey;
import com.codinggyd.redis.RedisClientUtils;
import com.codinggyd.service.impl.SysConstServiceImpl2;

@Service
public class ConstJob {
	final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysConstServiceImpl2 service;
	
	private static final String TABLE_CONST = "SYSCONST";
	
	@Scheduled(cron = "0 0/50 4-8 * * ?") 
	public void job() {
		logger.debug("===开始缓存常量===");
		List<SysConst2> sysConstList = service.getAllConst();
		if (CollectionUtils.isNotEmpty(sysConstList)) {
//			Map<String,List<SysConst>> map = new HashMap<String,List<SysConst>>();
//			for (SysConst sysConst : sysConstList) {
//				List<SysConst> temp = map.get(sysConst.getLb());
//				if (null == temp) {
//					temp = new ArrayList<SysConst>();
//				}
//				temp.add(sysConst);
//				map.put(sysConst.getLb(), temp);
//			}
			
			RedisClientUtils.cache2(TABLE_CONST, new ICacheKey<SysConst2>() {
				@Override
				public String getCacheKey(SysConst2 t) {
					return t.getLb();
				}
				
			}, sysConstList);
		
		}
		logger.debug("===完成缓存常量===");

	}
	
	
}
