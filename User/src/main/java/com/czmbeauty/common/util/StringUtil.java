package com.czmbeauty.common.util;

public class StringUtil {

	public static String getPath(String servletPath) {

		return servletPath.substring(1, servletPath.length());
	}

	public static String getExtension(String servletPath) {

		String path = getPath(servletPath);

		if (path.split("/")[1].indexOf(".") != -1) {
			return path.split("/")[1].split("\\.")[1];
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
