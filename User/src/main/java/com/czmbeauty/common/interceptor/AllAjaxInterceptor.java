package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.CommonConstants.QUESTION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AllAjaxInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AllAjaxInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath(); // /頁面名
		String ajaxName = servletPath.substring(1, servletPath.length()); // AJAX名
		String queryString = request.getQueryString(); // 參數
		String requestAjax = (queryString != null) ? (ajaxName + QUESTION + queryString) : ajaxName; // 請求AJAX

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		String ca_directory = requestAjax.split("/")[1].split("\\.")[0];

		if ("open-base-list".equals(ca_directory)) {

		}
		if ("slider".equals(ca_directory.split("-")[0])) {

		}
		if ("video".equals(ca_directory.split("-")[0])) {

		}

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") 放行: " + requestAjax);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
