package com.codinggyd.core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codinggyd.bean.MineServiceBean;
import com.codinggyd.bean.requ.MineRequestBean;
import com.codinggyd.bean.resp.MineResponseBean;
import com.codinggyd.constant.MineResponseCode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

/**
 * 
 * @Title:  MineServiceExecuter
 * @Package: com.codinggyd.core
 * @Description: 解析请求参数,动态执行service业务类方法,封装结果集
 *
 * @author: guoyd
 * @Date: 2017年2月19日下午1:59:09
 *
 * Copyright @ 2017 Corpration Name
 */
public abstract class MineServiceExecuter {
	final static Logger logger = LoggerFactory.getLogger(MineServiceExecuter.class);

	static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 解析json请求参数，动态执行业务方法
	 * @param requestJson
	 * @throws Exception
	 */
	public static String invoke(String requestJson) throws Exception{
//		JsonNode jsonNode = objectMapper.readTree(requestJson);
//		String serviceId = jsonNode.get("serviceId").asText();//解析要执行的业务类
//		JsonNode params = jsonNode.get("params");//解析业务类方法的参数
		
		
		MineRequestBean mineRequestBean = objectMapper.readValue(requestJson, MineRequestBean.class);
		String serviceId = mineRequestBean.getServiceId();//解析要执行的业务类
		JsonNode[] params = mineRequestBean.getParams();//解析业务类方法的参数
		
		MineServiceBean mineServiceBean = MineServiceContext.getMineServiceBean(serviceId);
		if (null == mineServiceBean) {
			logger.error("不存在的接口地址{}",serviceId);
			return wrapResult(MineResponseCode.ERROR_CODE, "不存在的接口地址");
		}
		Method method = mineServiceBean.getMethod();
		Class<?>[] paramsType = method.getParameterTypes();
		 
		int paramsSize = params.length;
		if (paramsType.length != paramsSize) {
			logger.error("9999,提交参数个数不一致!");
			return wrapResult(MineResponseCode.ERROR_CODE, "提交参数个数不一致");
		}
		//解析请求参数,与业务方法参数类型进行一一转换
		Object[] args = new Object[paramsType.length];
		for (int i = 0;i < paramsType.length;i++) {
			Class<?> cs = paramsType[i];
			JsonNode node = params[i];
			args[i] = objectMapper.reader().treeToValue(node, cs);
		}
		Object result = method.invoke(mineServiceBean.getService(), args);
		return wrapResult(MineResponseCode.SUCCESS_CODE, result);
	 
	}
	
	/**
	 * 将业务方法执行的结果集封装为json字符串
	 * @param result
	 * @return
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	private static String wrapResult(String responseCode, Object data) throws Exception{
		MineResponseBean mineResponseBean = new MineResponseBean();
		mineResponseBean.setCode(responseCode);
		
		List<Object> result = new ArrayList<>();
		List<Object> extras = new ArrayList<>();
		if (data instanceof PageList) {
			PageList temp = (PageList) data;
			Paginator pageInfo = temp.getPaginator();
			result.add(temp);
			extras.add(pageInfo);
		} else {
			result.add(data);
		}
		
		mineResponseBean.setData(result);
		mineResponseBean.setExtras(extras);
		
		
		return objectMapper.writeValueAsString(mineResponseBean);
	}
	
}
