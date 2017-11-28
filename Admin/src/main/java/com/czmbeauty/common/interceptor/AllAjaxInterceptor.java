package com.czmbeauty.common.interceptor;

import static com.czmbeauty.common.constants.CommonConstants.QUESTION;
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
import com.czmbeauty.common.constants.ParameterConstants;
import com.czmbeauty.model.service.AdminService;
import com.czmbeauty.model.service.BaseService;
import com.czmbeauty.model.service.CityService;
import com.czmbeauty.model.service.CountryService;
import com.czmbeauty.model.service.ImageService;
import com.czmbeauty.model.service.StateService;
import com.czmbeauty.model.service.VideoService;

public class AllAjaxInterceptor
		implements HandlerInterceptor, ModelAttributeConstants, PageNameConstants, ParameterConstants {

	private static final Logger logger = Logger.getLogger(AllAjaxInterceptor.class);

	/**
	 * 注入 AdminService
	 */
	@Autowired
	private AdminService adminService;

	/**
	 * 注入 CountryService
	 */
	@Autowired
	private CountryService countryService;

	/**
	 * 注入 StateService
	 */
	@Autowired
	private StateService stateService;

	/**
	 * 注入 CityService
	 */
	@Autowired
	private CityService cityService;

	/**
	 * 注入 BaseService
	 */
	@Autowired
	private BaseService baseService;

	/**
	 * 注入 ImageService
	 */
	@Autowired
	private ImageService imageService;

	/**
	 * 注入 VideoService
	 */
	@Autowired
	private VideoService videoService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String servletPath = request.getServletPath(); // /頁面名
		String ajaxName = servletPath.substring(1, servletPath.length()); // AJAX名
		String queryString = request.getQueryString(); // 參數
		String requestAjax = (queryString != null) ? (ajaxName + QUESTION + queryString) : ajaxName; // 請求AJAX

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		String handlerClassName = handlerMethod.getBeanType().getSimpleName();
		String handlerMethodName = handlerMethod.getMethod().getName();

		if (ADMIN_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(ADMIN_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| adminService.selectByAd_id(Integer.valueOf(parameter)) == null
					|| adminService.selectByAd_id(Integer.valueOf(parameter)).getAd_authority().intValue() == 1) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (AREA_COUNTRY_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(COUNTRY_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| countryService.selectByCo_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (AREA_STATE_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(STATE_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| stateService.selectBySt_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (AREA_CITY_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(CITY_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| cityService.selectByCi_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (BASE_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(BASE_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| baseService.selectByBa_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (IMAGE_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(IMAGE_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| imageService.selectByIm_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (VIDEO_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(VIDEO_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| videoService.selectByVi_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}
		if (HTML_SWITCH_AJAX.equals(ajaxName)) {

			String parameter = request.getParameter(HTML_ID);

			if (parameter == null || parameter.isEmpty() || !parameter.matches("[0-9]+")
					|| videoService.selectByVi_id(Integer.valueOf(parameter)) == null) {

				logger.info("(" + handlerClassName + "." + handlerMethodName + ") 攔截: " + requestAjax);

				request.getRequestDispatcher(SLASH + ERROR_PAGE_NOT_FOUND_PAGE).forward(request, response);

				return false;
			}
		}

		logger.info("(" + handlerClassName + "." + handlerMethodName + ") 放行: " + requestAjax);

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
