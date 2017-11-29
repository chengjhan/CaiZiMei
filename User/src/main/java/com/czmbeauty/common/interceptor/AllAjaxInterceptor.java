package com.czmbeauty.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.exception.PageNotFoundException;
import com.czmbeauty.model.service.CategoryUrlService;
import com.czmbeauty.model.service.UserUrlService;

public class AllAjaxInterceptor implements HandlerInterceptor, ControllerConstants {

	private static final Logger logger = Logger.getLogger(AllAjaxInterceptor.class);

	/**
	 * 注入 CategoryUrlService
	 */
	@Autowired
	private CategoryUrlService categoryUrlService;

	/**
	 * 注入 UserUrlService
	 */
	@Autowired
	private UserUrlService userUrlService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath(); // /URL
		String uu_url = servletPath.substring(1, servletPath.length()); // URL
		String cu_code = (uu_url.split("/")[1].indexOf(".") != -1) ? (uu_url.split("/")[1].split("\\.")[1]) : BLANK; // URL類別code
		String queryString = request.getQueryString(); // 參數
		String requestUrl = (queryString != null) ? (uu_url + QUESTION + queryString) : uu_url; // 請求URL

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		try {
			if (userUrlService.selectByUu_url(categoryUrlService.selectByCu_code(cu_code), uu_url) == null) {

				// 有 mapping，但資料庫無此 URL
				throw new PageNotFoundException(requestUrl);
			}
		} catch (PageNotFoundException e) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestUrl);

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

			return false;
		}

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") 放行: " + requestUrl);

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
