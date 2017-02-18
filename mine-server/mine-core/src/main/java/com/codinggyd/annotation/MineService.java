package com.codinggyd.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Title:  MineService
 * @Package: com.codinggyd.annotation
 * @Description: service业务类的注解
 *
 * @author: guoyd
 * @Date: 2017年2月18日下午6:44:06
 *
 * Copyright @ 2017 Corpration Name
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MineService {
	
}
