package com.codinggyd.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.codinggyd.bean.LoggerEntity;
import com.codinggyd.job.LogJob;
import com.codinggyd.util.LoggerUtils;
import com.codinggyd.utils.CommonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 
 * @Title: LoggerInterceptor.java
 * @Package: com.codinggyd.interceptor
 * @Description: 网站访问信息记录-拦截器
 * 
 * @Author: guoyd
 * @Date: 2019年3月18日 下午1:22:22
 *
 * Copyright @ 2019 Corpration Name
 */
@Service
public class LoggerInterceptor implements HandlerInterceptor {
    //请求开始时间标记
    private static final String LOGGER_SEND_TIME="_send_time";
    //请求日志实体标记
    private static final String LOGGER_ENTITY="_logger_entity";
	final Logger logger = LoggerFactory.getLogger(getClass());

    private static final ObjectMapper om;
    static {
    	om = CommonUtils.getMappingInstance();
    }
    //调用请求的时候执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //创建请求实体
        LoggerEntity loggerEntity = new LoggerEntity();
        //获取请求的sessionId
        String sessionId = request.getRequestedSessionId();
        //请求的路径
        String path = request.getRequestURI();
        //获取请求参数信息
        String paramData = null;
		try {
			paramData = om.writeValueAsString(request.getParameterMap());
		} catch (JsonProcessingException e) {
			logger.error("出错了,{}",e);
		}
		//设置访问时间
		loggerEntity.setTime(new Date());
        //设置客户端ip
        loggerEntity.setIp(LoggerUtils.getCliectIp(request));
        //设置请求方法
        loggerEntity.setMethod(request.getMethod());
        //设置请求类型
        loggerEntity.setType(LoggerUtils.getRequestType(request));
        //设置请求参数的json字符串
        loggerEntity.setParamData(paramData);
        //设置请求地址
        loggerEntity.setUrl(path);
        //设置sessionId
        loggerEntity.setSessionId(sessionId);
        //设置请求开始时间
        request.setAttribute(LOGGER_SEND_TIME,System.currentTimeMillis());
        //设置请求实体到request中，方便after调用
        request.setAttribute(LOGGER_ENTITY,loggerEntity);
        return true;
    }
    //controller调用之后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
 
    }
    //viewResolve返回view到前台之前执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //获取请求错误码
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //请求开始时间
        long time = Long.parseLong(request.getAttribute(LOGGER_SEND_TIME).toString());
        //获取本次请求日志实体
        LoggerEntity loggerEntity = (LoggerEntity) request.getAttribute(LOGGER_ENTITY);
        //设置请求时间差
        loggerEntity.setTimeConsuming(Integer.valueOf(String.valueOf((currentTime-time))));
        //设置返回时间
        loggerEntity.setReturnTime(String.valueOf(currentTime));
        //设置返回错误码
        loggerEntity.setHttpStatusCode(String.valueOf(status));
        //设置返回值
//        loggerEntity.setReturnData(JSON.toJSONString(LoggerUtils.LOGGER_RETURN,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue));
        //提交后台异步保存数据
        LogJob.addLog(loggerEntity);
    }
   
}