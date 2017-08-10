package com.czmbeauty.common.constants;

public interface HqlConstants {

	/**
	 * 搜尋所有辦事處
	 */
	public static final String HQL_SELECT_ALL_OFFICE = "from BaseBean where ba_ca_id=1 order by ba_status desc, ba_id asc";

	/**
	 * 搜尋所有加盟店
	 */
	public static final String HQL_SELECT_ALL_FRANCHISEE = "from BaseBean where ba_ca_id=2 order by ba_status desc, ba_id asc";

	/**
	 * 搜尋所有診所
	 */
	public static final String HQL_SELECT_ALL_CLINIC = "from BaseBean where ba_ca_id=3 order by ba_status desc, ba_id asc";

	/**
	 * 搜尋所有主輪播圖片
	 */
	public static final String HQL_SELECT_ALL_SLIDER_MAIN = "from ImageBean where im_ca_id=4 order by im_status desc, im_id asc";

}
