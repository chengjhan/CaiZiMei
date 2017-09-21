package com.czmbeauty.common.exception;

import org.apache.log4j.Logger;

public class PageNotFoundException extends NullPointerException {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PageNotFoundException.class);

	public PageNotFoundException() {

		super("找不到這個頁面");

		logger.error("找不到這個頁面");
	}

	public PageNotFoundException(String pageName) {

		super("找不到這個頁面: " + pageName);

		logger.error("找不到這個頁面: " + pageName);
	}

}
