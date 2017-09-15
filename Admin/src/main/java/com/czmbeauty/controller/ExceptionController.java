package com.czmbeauty.controller;

import static com.czmbeauty.common.constants.PageNameConstants.ERROR_PAGE_NOT_FOUND_PAGE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {

	/**
	 * 找不到網頁 - 初期處理
	 * 
	 * @return /WEB-INF/views/error/page-not-found.jsp
	 */
	@RequestMapping(value = "/error/page-not-found", method = RequestMethod.GET)
	public String pageNotFoundView() {

		return ERROR_PAGE_NOT_FOUND_PAGE;
	}

}
