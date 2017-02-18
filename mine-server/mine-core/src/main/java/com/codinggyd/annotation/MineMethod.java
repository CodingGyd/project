package com.codinggyd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title:  MineMethod
 * @Package: com.codinggyd.annotation
 * @Description: 方法的注解
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午7:46:26
 *
 * Copyright @ 2017 Corpration Name
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MineMethod {
	String value();
}
