package com.czmbeauty.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.util.StringUtil;

public class ResetPasswordInterceptor implements HandlerInterceptor, ControllerConstants {

	private static final Logger logger = Logger.getLogger(ResetPasswordInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		String requestPath = StringUtil.getRequestPath(request.getServletPath(), request.getQueryString()); // 請求 path

		if ((String) request.getSession().getAttribute(ADMIN_EMAIL_SESSION) == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 攔截: " + requestPath);

			response.sendRedirect(request.getContextPath() + SLASH + ADMIN_SIGN_IN_PAGE);

			return false;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 放行: " + requestPath);

			return true;
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
