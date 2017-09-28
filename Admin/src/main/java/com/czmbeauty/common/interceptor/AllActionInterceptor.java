package com.czmbeauty.common.interceptor;

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
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.service.AdminLogService;

public class AllActionInterceptor implements HandlerInterceptor, ModelAttributeConstants, PageNameConstants {

	private static final Logger logger = Logger.getLogger(AllActionInterceptor.class);

	/**
	 * 注入 AdminLogService
	 */
	@Autowired
	private AdminLogService adminLogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String requestActionTag = (String) request.getSession().getAttribute(REQUEST_ACTION_TAG);

		String contextPath = request.getContextPath(); // /專案名
		String servletPath = request.getServletPath(); // /頁面名
		String requestAction = servletPath.substring(1, servletPath.length()); // 動作名

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		if (requestActionTag.equals(requestAction) || ADMIN_SIGN_OUT_DO.equals(requestAction)) {

			request.setAttribute(REQUEST_ACTION, requestAction);

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") 執行動作: " + requestAction);

			return true;

		} else {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAction);

			response.sendRedirect(contextPath + SLASH + ERROR_PAGE_NOT_FOUND_PAGE);

			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		AdminBean adminBean = (AdminBean) modelAndView.getModel().get(ADMIN);

		if (adminBean != null) {

			String servletPath = request.getServletPath(); // /頁面名
			String requestAction = servletPath.substring(1, servletPath.length()); // 動作名

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
