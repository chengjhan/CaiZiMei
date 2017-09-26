package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.ModelAttributeConstants.REQUEST_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AllPageInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AllPageInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath(); // /頁面名
		String pageName = servletPath.substring(1, servletPath.length()); // 頁面名
		String queryString = request.getQueryString(); // 參數
		String requestPage;
		if (queryString != null) {
			requestPage = pageName + QUESTION + queryString; // 頁面名?參數
		} else {
			requestPage = pageName; // 頁面名
		}

		request.setAttribute(REQUEST_PAGE, requestPage);

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		logger.info("(" + handlerMethod.getBeanType().getSimpleName() + "." + handlerMethod.getMethod().getName()
				+ ") 進入頁面: " + requestPage);

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
