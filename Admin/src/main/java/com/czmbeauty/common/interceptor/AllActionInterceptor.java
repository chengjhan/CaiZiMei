package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
import static com.czmbeauty.common.constants.ModelAttributeConstants.ADMIN;
import static com.czmbeauty.common.constants.ModelAttributeConstants.REQUEST_ACTION;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;

public class AllActionInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(AllActionInterceptor.class);

	/**
	 * 注入 AdminLogService
	 */
	@Autowired
	private AdminLogService adminLogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath(); // /頁面名
		String pageName = servletPath.substring(1, servletPath.length()); // 頁面名
		String queryString = request.getQueryString(); // 參數
		String requestAction;
		if (queryString != null) {
			requestAction = pageName + QUESTION + queryString; // 頁面名?參數
		} else {
			requestAction = pageName; // 頁面名
		}

		request.setAttribute(REQUEST_ACTION, requestAction);

		HandlerMethod handlerMethod = (HandlerMethod) handler;

		logger.info("(" + handlerMethod.getBeanType().getSimpleName() + "." + handlerMethod.getMethod().getName()
				+ ") 執行動作: " + requestAction);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		AdminBean adminBean = (AdminBean) modelAndView.getModel().get(ADMIN);

		if (adminBean != null) {

			String servletPath = request.getServletPath(); // /頁面名
			String pageName = servletPath.substring(1, servletPath.length()); // 頁面名
			String queryString = request.getQueryString(); // 參數
			String requestAction;
			if (queryString != null) {
				requestAction = pageName + QUESTION + queryString; // 頁面名?參數
			} else {
				requestAction = pageName; // 頁面名
			}

			AdminLogBean adminLogBean = new AdminLogBean();
			adminLogBean.setAl_AdminBean(adminBean);
			adminLogBean.setAl_operation(requestAction);
			adminLogBean.setAl_ip(request.getRemoteAddr());
			adminLogService.insert(adminLogBean);

			HandlerMethod handlerMethod = (HandlerMethod) handler;

			logger.info("(" + handlerMethod.getBeanType().getSimpleName() + "." + handlerMethod.getMethod().getName()
					+ ") 寫入日誌: " + requestAction);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
