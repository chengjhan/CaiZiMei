package com.czmbeauty.common.constants;

public interface HqlConstants {

	// admin
	/**
	 * 搜尋所有管理員
	 */
	public static final String HQL_SELECT_ALL_ADMIN = "from AdminBean order by ad_id asc";

	/**
	 * 管理員帳號搜尋
	 */
	public static final String HQL_SELECT_OPEN_ADMIN_BY_USERNAME = "from AdminBean where ad_status=1 and ad_username=:ad_username";

	/**
	 * 管理員信箱搜尋
	 */
	public static final String HQL_SELECT_OPEN_ADMIN_BY_EMAIL = "from AdminBean where ad_status=1 and ad_email=:ad_email";

	/**
	 * 管理員信箱搜尋 (edit) (AJAX)
	 */
	public static final String HQL_SELECT_OPEN_ADMIN_BY_EMAIL_EXCEPT_MYSELF = "from AdminBean where ad_status=1 and ad_email=:ad_email and ad_id!=:ad_id";

	// admin_log
	/**
	 * 搜尋所有管理員日誌
	 */
	public static final String HQL_SELECT_ALL_ADMIN_LOG = "from AdminLogBean order by al_insert_time asc, al_id asc";

	/**
	 * 管理員流水號搜尋
	 */
	public static final String HQL_SELECT_ADMIN_LOG_BY_ADMIN = "from AdminLogBean where al_ad_id=:al_ad_id order by al_insert_time asc, al_id asc";

	// country
	/**
	 * 搜尋所有國家
	 */
	public static final String HQL_SELECT_ALL_COUNTRY = "from CountryBean order by co_rank asc, co_id asc";

	// state
	/**
	 * 國家流水號搜尋
	 */
	public static final String HQL_SELECT_STATE_BY_COUNTRY = "from StateBean where st_co_id=:st_co_id order by st_rank asc, st_id asc";

	// city
	/**
	 * 區域流水號搜尋
	 */
	public static final String HQL_SELECT_CITY_BY_STATE = "from CityBean where ci_st_id=:ci_st_id order by ci_rank asc, ci_id asc";

	// base
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
	 * 搜尋開啟的診所
	 */
	public static final String HQL_SELECT_OPEN_CLINIC = "from BaseBean where ba_ca_id=3 and ba_status=1 order by ba_id asc";

	// image
	/**
	 * 搜尋所有主輪播圖片
	 */
	public static final String HQL_SELECT_ALL_SLIDER_MAIN = "from ImageBean where im_ca_id=4 order by im_status desc, im_rank asc, im_id asc";

	// video
	/**
	 * 搜尋所有主影片
	 */
	public static final String HQL_SELECT_ALL_VIDEO_MAIN = "from VideoBean where vi_ca_id=5 order by vi_rank asc, vi_id asc";

	/**
	 * 搜尋開啟的影片
	 */
	public static final String HQL_SELECT_OPEN_VIDEO = "from VideoBean where vi_status=1 and vi_ca_id=:vi_ca_id";

}
