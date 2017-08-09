package com.czmbeauty.common.constants;

public interface HqlConstants {

	/**
	 * 搜尋所有辦事處 (分頁)
	 */
	public static final String HQL_SELECT_ALL_OFFICE = "from BaseBean where ba_bk_id=1 order by ba_status desc, ba_id asc";

	/**
	 * 搜尋所有加盟店 (分頁)
	 */
	public static final String HQL_SELECT_ALL_FRANCHISEE = "from BaseBean where ba_bk_id=2 order by ba_status desc, ba_id asc";

	/**
	 * 搜尋所有診所 (分頁)
	 */
	public static final String HQL_SELECT_ALL_CLINIC = "from BaseBean where ba_bk_id=3 order by ba_status desc, ba_id asc";

}
