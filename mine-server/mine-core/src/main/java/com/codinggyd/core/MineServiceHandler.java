package com.codinggyd.core;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.codinggyd.annotation.MineFuncData;
import com.codinggyd.annotation.MineMethod;
import com.codinggyd.annotation.MineService;
import com.codinggyd.func.MineField;
import com.codinggyd.func.MineFuncBean;
import com.codinggyd.func.MineServiceBean;
import com.codinggyd.util.MineFieldTypeTool;

/**
 * 应用程序数据初始化加载类
 * @Title:  MineContext
 * @Package: com.codinggyd.core
 * @Description: 
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午7:14:12
 *
 * Copyright @ 2017 Corpration Name
 */
@Component
public class MineServiceHandler implements ApplicationContextAware, InitializingBean{
	
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ApplicationContext applicationContext;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.debug("=========afterPropertiesSet");
		preParserMineServiceBean();
		preParserMineFuncData();
	}

	/**
	 * 初始化所有service类对象
	 */
	private void preParserMineServiceBean(){
		Map<String, Object> serviceBeansMap = applicationContext.getBeansWithAnnotation(MineService.class);
		if (null == serviceBeansMap) {
			return;
		}
		Collection<Object> objects = serviceBeansMap.values();
		for (Object object : objects) {
			final Class<? extends Object> mineService = object.getClass();
			Method[] methods = mineService.getInterfaces()[0].getMethods();
			for (Method method : methods) {
				MineMethod mineMethod = method.getAnnotation(MineMethod.class);
				if (null != mineMethod) {
					MineServiceBean mineServiceBean = new MineServiceBean();
					mineServiceBean.setMethod(method);
					mineServiceBean.setService(object);
					MineServiceContext.addMineServiceBean(mineMethod.value(), mineServiceBean);
				}
			}
		}
	}
	
	/**
	 * 初始化所有service结果集对象
	 */
	private void preParserMineFuncData(){
		Map<String, Object> funcDatasMap = applicationContext.getBeansWithAnnotation(MineFuncData.class);
		if (null == funcDatasMap) {
			return;
		}
		Collection<Object> objects = funcDatasMap.values();
		for (Object object : objects) {
			Class<?> classes = object.getClass();
			MineFuncData mineFuncData = classes.getAnnotation(MineFuncData.class);
			String key = mineFuncData.name();
			String[] fieldNames = mineFuncData.fieldNames();//字段列表
			
			MineFuncBean funcBean = new MineFuncBean();
			funcBean.setFundId(key);
			
			for (String fieldName : fieldNames) {
				
				MineField mineField = new MineField();
				
				//获得字段名
				mineField.setName(fieldName);
				
				//获得方法名
				String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.replaceFirst("\\w", "");
				
				try {
					Method method = classes.getMethod(methodName);
					//获得字段类型
					Class<?> fieldTypeClass = method.getReturnType();
					int fieldType = MineFieldTypeTool.getFieldType(fieldTypeClass);
					mineField.setType(fieldType);
					
					funcBean.addField(mineField);
					funcBean.addMethod(method);
				} catch (Exception e) {
					logger.error("解析结果集出错{}",e);
				}
			}
			MineServiceContext.addMineFuncBean(key, funcBean);
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
