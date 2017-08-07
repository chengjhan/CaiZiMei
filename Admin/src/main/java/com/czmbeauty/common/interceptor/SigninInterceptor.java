package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_SIGN_IN_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.model.entity.AdminBean;

public class SigninInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(SigninInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute(ADMIN);

		String contextPath = request.getContextPath();
		String servletPath = request.getServletPath();
		String pageName = servletPath.substring(1, servletPath.length());
//		String queryString = request.getQueryString();
//		String next = pageName + "?" + queryString;

		if (adminBean == null) {

			logger.info("未登入，攔截: " + pageName);

			response.sendRedirect(contextPath + "/" + ADMIN_SIGN_IN_PAGE + "?next=" + pageName);

			return false;
		} else {

			logger.info("已登入，放行: " + pageName);

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
