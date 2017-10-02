package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.CommonConstants.SLASH;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.common.constants.ModelAttributeConstants;
import com.czmbeauty.common.constants.PageNameConstants;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.service.AdminViewService;

public class AllViewInterceptor implements HandlerInterceptor, ModelAttributeConstants, PageNameConstants {

	private static final Logger logger = Logger.getLogger(AllViewInterceptor.class);

	/**
	 * 注入 AdminViewService
	 */
	@Autowired
	private AdminViewService adminViewService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath(); // /頁面名
		String viewName = servletPath.substring(1, servletPath.length()); // 視圖名
		String queryString = request.getQueryString(); // 參數
		String requestView; // 請求視圖
		if (queryString != null) {
			requestView = viewName + QUESTION + queryString;
		} else {
			requestView = viewName;
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		try {
			if (adminViewService.selectByAv_view_name(viewName) == null) {

				throw new PageNotFoundException(requestView);
			}
		} catch (PageNotFoundException e) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestView);

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

			return false;
		}

		request.setAttribute(REQUEST_VIEW, requestView);

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") 進入頁面: " + requestView);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		String servletPath = request.getServletPath(); // /頁面名
		String viewName = servletPath.substring(1, servletPath.length()); // 視圖名
		String requestActionTag = viewName + ".do"; // 請求動作標籤

		request.getSession().setAttribute(REQUEST_ACTION_TAG, requestActionTag);
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
