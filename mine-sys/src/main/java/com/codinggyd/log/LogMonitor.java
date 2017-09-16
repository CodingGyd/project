package com.codinggyd.log;

import org.slf4j.Logger;

public class LogMonitor extends Log {
	private static final Logger logger =Log.get();
    /**
     * Info等级日志，小于Warn
     * @param log 日志对象
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void info(Logger log, String format, Object... arguments) {
        log.info(format, arguments);
        logger.info("信息 "+format,arguments);
    }
    /**
     * Warn等级日志，小于Error
     * @param log 日志对象
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void warn(Logger log, String format, Object... arguments) {
        log.warn(format, arguments);
        logger.warn("警告 "+format,arguments);
    }
    /**
     * Error等级日志<br>
     * @param log 日志对象
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(Logger log, String format, Object... arguments) {
    	log.error(format, arguments);
    	logger.error("错误 "+format, arguments);
//    	String errorInfo=format(format, arguments);
    	//TODO发送报警
    }
    
    /**
     * Error等级日志<br>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(String format, Object... arguments) {
        error(innerGet(), format, arguments);
    }
     
    /**
     * Error等级日志<br>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @param e 需在日志中堆栈打印的异常
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(Throwable e, String format, Object... arguments) {
        error(innerGet(), e, format(format, arguments));
    }
     
    /**
     * Error等级日志<br>
     * 由于动态获取Logger，效率较低，建议在非频繁调用的情况下使用！！
     * @param log 日志对象
     * @param e 需在日志中堆栈打印的异常
     * @param format 格式文本，{} 代表变量
     * @param arguments 变量对应的参数
     */
    public static void error(Logger log, Throwable e, String format, Object... arguments) {
        log.error(format(format, arguments), e);
        logger.error("错误 "+format, arguments);
    }
}
