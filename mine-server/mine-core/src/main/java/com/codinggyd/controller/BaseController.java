package com.codinggyd.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseController {
	final Logger logger = LoggerFactory.getLogger(getClass());
	
	ObjectMapper objectMapper = new ObjectMapper();
 
}
	
