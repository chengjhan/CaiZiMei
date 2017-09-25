package com.czmbeauty.common.util;

public class Pagination {

	/**
	 * 取得總頁數
	 * 
	 * @param totalRowCount
	 *            int --> 總筆數
	 * @param pageRowCount
	 *            int --> 每頁最大筆數
	 * @return int
	 */
	public static int getPageCount(int totalRowCount, int pageRowCount) {

		int pageCount = 0;

		if (totalRowCount % pageRowCount == 0) {

			pageCount = totalRowCount / pageRowCount;

		} else {

			pageCount = totalRowCount / pageRowCount + 1;
		}
		return pageCount;
	}

}
