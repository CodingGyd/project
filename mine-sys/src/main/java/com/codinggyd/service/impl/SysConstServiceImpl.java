package com.codinggyd.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codinggyd.bean.SysConst;
import com.codinggyd.mapper.SysConstMapper;
import com.codinggyd.service.ISysConstService;
import com.codinggyd.utils.DateUtils;

@Service
@Transactional(value="mineTransactionManager",propagation=Propagation.NOT_SUPPORTED,readOnly=true)
public class SysConstServiceImpl implements ISysConstService{

	final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private SysConstMapper mapper;
	@Override
	public List<SysConst> queryConst() {
		List<SysConst> result= mapper.queryConst();
		if (CollectionUtils.isNotEmpty(result)) {
			for (SysConst c : result) {
				c.setUpdatetimestr(DateUtils.dateToString(c.getUpdatetime(), "yyyy-MM-dd HH:mm:ss"));
			}
		}
		return result;
	}
	
	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteConst(Integer id) {
		mapper.deleteSysConst(id);
	}
	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public void updateConst(SysConst data) {
		mapper.updateSysConst(data);
	}
	@Override
	@Transactional(value="mineTransactionManager",propagation=Propagation.REQUIRED,readOnly=false)
	public void insertConst(SysConst data) {
		data.setUpdatetime(new Date());
		mapper.insertSysConst(data);
	}
	
	
	
}
