package com.codinggyd.log;

import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.spi.FilterReply;
import ch.qos.logback.core.status.ErrorStatus;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.WarnStatus;


public class LoggerMonitorFilter extends ThresholdFilter {

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
	@Override
	public void addInfo(String msg) {
		addStatus(new InfoStatus(msg, getDeclaredOrigin()));
	}
	@Override
	public void addInfo(String msg, Throwable ex) {
		addStatus(new InfoStatus(msg, getDeclaredOrigin(), ex));
	}
	@Override
	public void addWarn(String msg) {
		addStatus(new WarnStatus(msg, getDeclaredOrigin()));
	}
	@Override
	public void addWarn(String msg, Throwable ex) {
		addStatus(new WarnStatus(msg, getDeclaredOrigin(), ex));
	}
	@Override
	public void addError(String msg) {
		addStatus(new ErrorStatus(msg, getDeclaredOrigin()));
	}
	@Override
	public void addError(String msg, Throwable ex) {
		addStatus(new ErrorStatus(msg, getDeclaredOrigin(), ex));
	}
}

