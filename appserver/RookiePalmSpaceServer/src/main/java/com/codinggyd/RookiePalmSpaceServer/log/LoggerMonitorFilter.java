package com.codinggyd.RookiePalmSpaceServer.log;

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

	public void addInfo(String msg) {
		addStatus(new InfoStatus(msg, getDeclaredOrigin()));
	}

	public void addInfo(String msg, Throwable ex) {
		addStatus(new InfoStatus(msg, getDeclaredOrigin(), ex));
	}

	public void addWarn(String msg) {
		addStatus(new WarnStatus(msg, getDeclaredOrigin()));
	}

	public void addWarn(String msg, Throwable ex) {
		addStatus(new WarnStatus(msg, getDeclaredOrigin(), ex));
	}

	public void addError(String msg) {
		addStatus(new ErrorStatus(msg, getDeclaredOrigin()));
	}

	public void addError(String msg, Throwable ex) {
		addStatus(new ErrorStatus(msg, getDeclaredOrigin(), ex));
	}
}

