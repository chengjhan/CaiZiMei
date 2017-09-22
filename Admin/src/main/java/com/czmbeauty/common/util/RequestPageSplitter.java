package com.czmbeauty.common.util;

public class RequestPageSplitter {

	public static String getDirectoryName(String requestPage) {

		String directoryName = requestPage.split("/")[0];

		return directoryName;
	}

	public static String getPageName(String requestPage) {

		String pageName;

		if (requestPage.indexOf("?") != -1) {
			pageName = requestPage.split("\\?")[0];
		} else {
			pageName = requestPage;
		}
		return pageName;
	}

	public static String getQueryString(String requestPage) {

		String queryString = null;

		if (requestPage.indexOf("?") != -1) {
			queryString = requestPage.split("\\?")[1];
		}
		return queryString;
	}

}
