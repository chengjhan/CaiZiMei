package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.CommonConstants.SLASH;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN_EMAIL_SESSION;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_SIGN_IN_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ResetPasswordInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(ResetPasswordInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		String ad_email = (String) session.getAttribute(ADMIN_EMAIL_SESSION);

		String servletPath = request.getServletPath(); // /頁面名
		String pageName = servletPath.substring(1, servletPath.length()); // 頁面名

		if (ad_email == null) {

			logger.info("攔截: " + pageName);

			response.sendRedirect(request.getContextPath() + SLASH + ADMIN_SIGN_IN_PAGE);

			return false;

		} else {

			logger.info("放行: " + pageName);

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
