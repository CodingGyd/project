package com.codinggyd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title:  MineFuncData
 * @Package: com.codinggyd.annotation
 * @Description: 结果集的注解
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午8:08:14
 *
 * Copyright @ 2017 Corpration Name
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MineFuncData {
	String name();
	String[] fieldNames();
}
