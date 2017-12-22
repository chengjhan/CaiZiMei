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
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;
import com.czmbeauty.model.service.AdminLogService;
import com.czmbeauty.model.service.AdminPathService;

public class AllActionInterceptor implements HandlerInterceptor, ControllerConstants {

	private static final Logger logger = Logger.getLogger(AllActionInterceptor.class);

	/**
	 * 注入 AdminPathService
	 */
	@Autowired
	private AdminPathService adminPathService;

	/**
	 * 注入 AdminLogService
	 */
	@Autowired
	private AdminLogService adminLogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		String servletPath = request.getServletPath(); // /path
		String path = StringUtil.getPath(servletPath); // path
		String queryString = request.getQueryString(); // query
		String requestPath = StringUtil.getRequestPath(servletPath, queryString); // 請求 path

		if (ADMIN_LOG_LIST_DO.equals(path)) {

			request.setAttribute(REQUEST_PATH, requestPath);

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 執行動作: " + requestPath);

			return true;

		} else if (ADMIN_SIGN_OUT_DO.equals(requestPath)) {

			request.setAttribute(REQUEST_PATH, requestPath);

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 執行動作: " + requestPath);

			return true;

		} else if (handlerMethodName.indexOf("Action") == -1) { // 經過 AllViewInterceptor (GET)

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 攔截: " + requestPath);

			request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

			return false;

		} else {

			request.setAttribute(REQUEST_PATH, requestPath);

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 執行動作: " + requestPath);

			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") start");

		if (!OK.equals((String) request.getAttribute(ADMIN_LOG_KEY))) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 不寫入日誌");

			return;
		}

		AdminBean sessionAdminBean = (AdminBean) request.getSession().getAttribute(ADMIN);
		AdminBean modelAndViewAdminBean = (AdminBean) modelAndView.getModel().get(ADMIN);

		if (sessionAdminBean == null && modelAndViewAdminBean == null) {

			logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 不寫入日誌");

			return;
		}

		String servletPath = request.getServletPath(); // /path

		AdminPathBean adminPathBean = adminPathService.selectByAp_path(StringUtil.getExtension(servletPath),
				StringUtil.getPath(servletPath));

		AdminLogBean adminLogBean = new AdminLogBean();
		adminLogBean.setAl_AdminBean((sessionAdminBean == null) ? modelAndViewAdminBean : sessionAdminBean);
		adminLogBean.setAl_AdminPathBean(adminPathBean);
		adminLogBean.setAl_ip(request.getRemoteAddr());
		adminLogService.insert(adminLogBean);

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") end, 寫入日誌: " + adminPathBean.getAp_name());
	}

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

}
