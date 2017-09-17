package com.github.miemiedev.mybatis.paginator.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**
 * @author miemiedev
 */
public class PageListJsonMapper extends ObjectMapper{

    /**
	 * 
	 */
	private static final long serialVersionUID = 3736881414296390143L;

	public PageListJsonMapper() {
        SimpleModule module = new SimpleModule("PageListJSONModule", new Version(1, 0, 0, null, null, null));
        module.addSerializer(PageList.class, new PageListJsonSerializer(this));
        registerModule(module);
    }
}
