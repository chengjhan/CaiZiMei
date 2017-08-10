package com.czmbeauty.common.constants;

public interface HqlConstants {

	/**
	 * 搜尋開啟的據點
	 */
	public static final String HQL_SELECT_OPEN_BASE = "from BaseBean where ba_status=1 order by ba_id asc";

	/**
	 * 搜尋開啟的主輪播圖片
	 */
	public static final String HQL_SELECT_OPEN_SLIDER_MAIN_IMAGE = "from ImageBean where im_ca_id=4 and im_status=1 order by im_rank asc";

}
