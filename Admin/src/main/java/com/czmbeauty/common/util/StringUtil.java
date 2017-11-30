package com.czmbeauty.common.util;

public class StringUtil {

	public static String getPath(String servletPath) {

		return servletPath.substring(1, servletPath.length());
	}

	public static String getExtension(String servletPath) {

		String lastPath = servletPath.split("/")[servletPath.split("/").length - 1];
		int indexOfDot = lastPath.lastIndexOf(".");

		if (indexOfDot != -1) {
			return servletPath.substring(indexOfDot, lastPath.length());
		} else {
			return "";
		}
	}

	public static String getRequestPath(String servletPath, String queryString) {

		String path = getPath(servletPath);

		if (queryString != null) {
			return path + "?" + queryString;
		} else {
			return path;
		}
	}

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
