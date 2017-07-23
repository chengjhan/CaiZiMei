package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN;
import static com.czmbeauty.common.constants.PageNameConstants.ADMIN_SIGN_IN_PAGE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.model.entity.AdminBean;

public class SigninInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		AdminBean adminBean = (AdminBean) session.getAttribute(ADMIN);

		if (adminBean == null) {

			System.out.println("未登入攔截");

			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/" + ADMIN_SIGN_IN_PAGE);

			return false;
		} else {

			System.out.println("已登入放行");

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
