//package com.codinggyd.service.impl;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.StringUtils;
//
//import com.codinggyd.bean.DailEssays;
//import com.codinggyd.bean.MinePageBean;
//import com.codinggyd.service.IDailyEssayService;
//import com.codinggyd.util.HttpClientUtil;
//import com.codinggyd.util.SysConstant;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * 
// * @Title:  DailyEssaysServiceImpl.java
// * @Package: com.codinggyd.service.impl
// * @Description: 随笔
// *
// * @author: guoyd
// * @Date: 2017年10月15日 下午8:41:38
// *
// * Copyright @ 2017 Corpration Name
// */
//@Service
//public class DailyEssaysServiceImpl implements IDailyEssayService{
//
//	final Logger logger = LoggerFactory.getLogger(getClass());
//	private static final String SERVER_DAILY_ESSAYS="MINE_DAILY_ESSAYS";//数据接口地址-随笔
//	
//	private static ObjectMapper mapper = new ObjectMapper();
//
//	@Override
//	public MinePageBean<DailEssays> getDailyEssays() {
//		//加载最新文章
//		List<DailEssays> DailEssayss = new ArrayList<>();
//		DailEssayss.addAll(getServerDailyEssaysList(null));
//		
//		if (CollectionUtils.isEmpty(DailEssayss)) {
//			logger.error("获取随笔数据为空!");
//			return null;
//		}
//		return new MinePageBean<DailEssays>(null,DailEssayss);
//	}
//	
//	/**
//	 * 加载最新文章
//	 * @return
//	 */
//	private List<DailEssays> getServerDailyEssaysList(Object pageInfos) {
//		List<DailEssays> result = new ArrayList<>();
//
//		String responseData = HttpClientUtil.requestServer(SERVER_DAILY_ESSAYS,pageInfos);
// 	 
//		if (StringUtils.isEmpty(responseData)) {
//			logger.error("接口[{}]返回数据为空",SERVER_DAILY_ESSAYS);
// 		} else {
// 		
// 			try {
//				JsonNode node = mapper.readTree(responseData);
//				String code = node.get("code").asText();
//				if (SysConstant.RESPONSE_CODE_SUCCESS.equals(code)) {
//					JsonNode resultJson = node.get("data").get(0);
//					
//					if (null == resultJson) {
//						logger.error("接口[{}]返回数据为空",SERVER_DAILY_ESSAYS);
//					} else {
//						//解析json格式数据
//						int size = resultJson.size();
//						for (int i=0;i<size;i++) {
//							JsonNode temp = resultJson.get(i);
//							result.add(formatDailEssaysBean(temp));
//						}
//					}
//					
//				} else {
//					logger.error("接口[{}]错误,响应码{}",SERVER_DAILY_ESSAYS,code);
//				}
//			} catch (Exception e) {
//				logger.error("接口[{}]返回数据有误,{},{}",SERVER_DAILY_ESSAYS,responseData,e);
//			}
// 			
// 		}
//		
//		return result;
//	}
//	private DailEssays formatDailEssaysBean(JsonNode jsonNode) {
//		DailEssays daily = new DailEssays();
//		daily.setContent(jsonNode.get("content").asText(""));
//		daily.setUpdatetime(formatDateStr(jsonNode.get("updatetime").asText("")));
//		return daily;
//	}
//	private String formatDateStr(String time) {
//		return !StringUtils.isEmpty(time) && time.length() >= 10 ? time.substring(0,10) : ""; 
//	}
//}
