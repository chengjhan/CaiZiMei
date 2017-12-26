package com.czmbeauty.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.util.StringUtil;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.service.AdminPathService;

public class NoAuthorityInterceptor implements HandlerInterceptor, ControllerConstants {

	private static final Logger logger = Logger.getLogger(NoAuthorityInterceptor.class);

	/**
	 * 注入 AdminPathService
	 */
	@Autowired
	private AdminPathService adminPathService;

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

		if (adminPathService.selectByAp_path(StringUtil.getExtension(servletPath), StringUtil.getPath(servletPath))
				.getAp_authority() == ADMIN_PATH_AUTHORITY_NOT_NEED) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 不須權限，放行: " + requestPath);

			return true;

		} else {

			if (((AdminBean) request.getSession().getAttribute(ADMIN)).getAd_authority() == ADMIN_AUTHORITY_NOT_HAVE) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 沒有權限，攔截: " + requestPath);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;

			} else {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 有權限，放行: " + requestPath);

				return true;
			}
		}
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
