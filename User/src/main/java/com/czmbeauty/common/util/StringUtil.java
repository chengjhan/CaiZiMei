package com.czmbeauty.common.util;

public class StringUtil {

	public static String getPath(String servletPath) {

		return servletPath.substring(1, servletPath.length());
	}

	public static String getExtension(String servletPath) {

		String lastPath = servletPath.split("/")[servletPath.split("/").length - 1];
		int indexOfDot = lastPath.lastIndexOf(".");

		if (indexOfDot != -1) {
			return lastPath.substring(indexOfDot + 1, lastPath.length());
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

	public static String getDirectory(String servletPath) {

		return servletPath.split("/")[1] + "-" + servletPath.split("/")[2].split("\\.")[0];
	}

}
