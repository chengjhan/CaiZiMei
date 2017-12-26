package com.czmbeauty.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.common.constants.ControllerConstants;
import com.czmbeauty.common.util.StringUtil;
import com.czmbeauty.model.entity.AdminBean;

public class NoSignInInterceptor implements HandlerInterceptor, ControllerConstants {

	private static final Logger logger = Logger.getLogger(NoSignInInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		HttpSession session = request.getSession();
		String next = StringUtil.getRequestPath(request.getServletPath(), request.getQueryString()); // 原請求 path

		AdminBean adminBean = (AdminBean) session.getAttribute(ADMIN);

		if (adminBean == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 未登入，攔截: " + next + "，跳轉至: "
					+ ADMIN_SIGN_IN_PAGE);

			// 將原請求 path，放入 Session
			session.setAttribute(NEXT_PAGE, ADMIN_SIGN_OUT_DO.equals(next) ? INDEX_PAGE : next);

			response.sendRedirect(request.getContextPath() + SLASH + ADMIN_SIGN_IN_PAGE);

			return false;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 已登入，使用者: "
					+ adminBean.getAd_username() + "，放行: " + next);

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
