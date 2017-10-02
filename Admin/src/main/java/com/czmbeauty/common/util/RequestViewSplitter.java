package com.czmbeauty.common.util;

public class RequestViewSplitter {

	public static String getDirectoryName(String requestView) {

		String directoryName = requestView.split("/")[0];

		return directoryName;
	}

	public static String getView(String requestView) {

		String view;

		if (requestView.indexOf("?") != -1) {
			view = requestView.split("\\?")[0];
		} else {
			view = requestView;
		}
		return view;
	}

	public static String getQueryString(String requestView) {

		String queryString = null;

		if (requestView.indexOf("?") != -1) {
			queryString = requestView.split("\\?")[1];
		}
		return queryString;
	}

}
