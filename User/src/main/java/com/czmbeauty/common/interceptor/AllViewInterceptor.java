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
import com.czmbeauty.common.util.StringUtil;
import com.czmbeauty.model.service.UserPathService;

public class AllViewInterceptor implements HandlerInterceptor, ControllerConstants {

	private static final Logger logger = Logger.getLogger(AllViewInterceptor.class);

	/**
	 * 注入 UserPathService
	 */
	@Autowired
	private UserPathService userPathService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		String servletPath = request.getServletPath(); // /path
		String queryString = request.getQueryString(); // query
		String requestPath = StringUtil.getRequestPath(servletPath, queryString); // 請求 path

		try {
			if (userPathService.selectByUp_path(StringUtil.getExtension(servletPath),
					StringUtil.getPath(servletPath)) == null) {

				// 有 mapping，但資料庫無此 path
				throw new PageNotFoundException(requestPath);
			}
		} catch (PageNotFoundException e) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestPath);

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

			return false;
		}

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") 進入頁面: " + requestPath);

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
