package com.codinggyd.log;

import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;


public class LoggerFilter extends ThresholdFilter {

	private boolean enable;

	@Override
	public FilterReply decide(ILoggingEvent event) {
		if (enable) {
			return super.decide(event);
		}
		return FilterReply.DENY;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

}
