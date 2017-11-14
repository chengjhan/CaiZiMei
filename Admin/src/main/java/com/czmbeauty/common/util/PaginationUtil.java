package com.czmbeauty.common.util;

public class PaginationUtil {

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

		int pageCount = totalRowCount / pageRowCount;

		return (totalRowCount % pageRowCount == 0) ? pageCount : (pageCount + 1);
	}

	/**
	 * 取得總群數
	 * 
	 * @param pageCount
	 *            int --> 總頁數
	 * @param groupRowCount
	 *            int --> 每群最大頁數
	 * @return int
	 */
	public static int getGroupCount(int pageCount, int groupRowCount) {

		int groupCount = pageCount / groupRowCount;

		return (pageCount % groupRowCount == 0) ? groupCount : (groupCount + 1);
	}

	/**
	 * 取得當前群序
	 * 
	 * @param currentPage
	 *            int --> 當前頁碼
	 * @param groupRowCount
	 *            int --> 每群最大頁數
	 * @return int
	 */
	public static int getCurrentGroup(int currentPage, int groupRowCount) {

		int currentGroup = currentPage / groupRowCount;

		return (currentPage % groupRowCount == 0) ? currentGroup : (currentGroup + 1);
	}

	/**
	 * 取得當前群序起始頁碼
	 * 
	 * @param currentPage
	 *            int --> 當前頁碼
	 * @param groupRowCount
	 *            int --> 每群最大頁數
	 * @return int
	 */
	public static int getCurrentGroupBegin(int currentPage, int groupRowCount) {

		return ((getGroupCount(currentPage, groupRowCount) - 1) * groupRowCount) + 1;
	}

	/**
	 * 取得當前群序結束頁碼
	 * 
	 * @param pageCount
	 *            int --> 總頁數
	 * @param currentPage
	 *            int --> 當前頁碼
	 * @param groupRowCount
	 *            int --> 每群最大頁數
	 * @return int
	 */
	public static int getCurrentGroupEnd(int pageCount, int currentPage, int groupRowCount) {

		int currentGroupBegin = getCurrentGroupBegin(currentPage, groupRowCount);

		return ((pageCount - currentGroupBegin) > (groupRowCount - 1)) ? (currentGroupBegin + (groupRowCount - 1))
				: pageCount;
	}

}
