package com.czmbeauty.common.util;

public class RequestPageSplitter {

	public static String getDirectoryName(String requestPage) {

		return requestPage.split("/")[0];
	}

	public static String getPageName(String requestPage) {

		return (requestPage.indexOf("?") != -1) ? requestPage.split("\\?")[0] : requestPage;
	}

	public static String getQueryString(String requestPage) {

		return (requestPage.indexOf("?") != -1) ? requestPage.split("\\?")[1] : "";
	}

}
