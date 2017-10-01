package com.czmbeauty.common.util;

public class RequestViewSplitter {

	public static String getDirectoryName(String requestView) {

		String directoryName = requestView.split("/")[0];

		return directoryName;
	}

	public static String getPageName(String requestView) {

		String pageName;

		if (requestView.indexOf("?") != -1) {
			pageName = requestView.split("\\?")[0];
		} else {
			pageName = requestView;
		}
		return pageName;
	}

	public static String getQueryString(String requestView) {

		String queryString = null;

		if (requestView.indexOf("?") != -1) {
			queryString = requestView.split("\\?")[1];
		}
		return queryString;
	}

}
