package com.qkby.exception;

import java.io.Writer;

import org.apache.log4j.Logger;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
/**
 * 
 * 重写freemarker的异常处理方法，将异常信息记录到log文件
 */
public class EventTemplateExceptionHandler implements TemplateExceptionHandler {
	private static Logger log = Logger.getLogger(EventTemplateExceptionHandler.class);
	public void handleTemplateException(TemplateException arg0,
			Environment arg1, Writer arg2) {
		
		log.warn(arg0.getMessage());

	}

}
