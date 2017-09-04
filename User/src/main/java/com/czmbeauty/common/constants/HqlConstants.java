package com.czmbeauty.common.constants;

public interface HqlConstants {

	// base
	/**
	 * 搜尋開啟的據點
	 */
	public static final String HQL_SELECT_OPEN_BASE = "from BaseBean where ba_status=1 order by ba_id asc";

	// image
	/**
	 * 搜尋開啟的主輪播圖片
	 */
	public static final String HQL_SELECT_OPEN_SLIDER_MAIN = "from ImageBean where im_ca_id=4 and im_status=1 order by im_rank asc";

	// video
	/**
	 * 搜尋開啟的主影片
	 */
	public static final String HQL_SELECT_OPEN_VIDEO_MAIN = "from VideoBean where vi_ca_id=5 and vi_status=1 order by vi_rank asc";

}
