package com.czmbeauty.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class StopwatchInterceptor implements HandlerInterceptor {

	private static final Logger logger = Logger.getLogger(StopwatchInterceptor.class);

	private long startTime;
	private long endTime;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		startTime = System.currentTimeMillis();

		logger.info("time start...");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
			throws Exception {

		endTime = System.currentTimeMillis();

		logger.info("time stop, time: " + (endTime - startTime) + "ms\n");
	}

}
