package com.czmbeauty.common.constants;

public interface HqlConstants {

	// category_path
	/** extension 搜尋 */
	public static final String HQL_SELECT_CATEGORY_PATH_BY_EXTENSION = "from CategoryPathBean where cp_extension=:cp_extension";

	// admin_path
	/** path 類別流水號搜尋 */
	public static final String HQL_SELECT_ADMIN_PATH_BY_CATEGORY_PATH = "from AdminPathBean where ap_cp_id=:ap_cp_id order by ap_id asc";

	// category
	/** 類別資料夾名稱搜尋 */
	public static final String HQL_SELECT_CATEGORY_BY_DIRECTORY = "from CategoryBean where ca_directory=:ca_directory";

	// admin
	/** 搜尋所有管理員 */
	public static final String HQL_SELECT_ALL_ADMIN = "from AdminBean order by ad_id asc";

	/** 管理員信箱搜尋 (edit) - AJAX */
	public static final String HQL_SELECT_ADMIN_BY_EMAIL_EXCEPT_MYSELF = "from AdminBean where ad_id!=:ad_id and ad_email=:ad_email";

	// country
	/** 搜尋所有國家 */
	public static final String HQL_SELECT_ALL_COUNTRY = "from CountryBean order by co_rank asc, co_id asc";

	// state
	/** 國家流水號搜尋 */
	public static final String HQL_SELECT_STATE_BY_COUNTRY = "from StateBean where st_co_id=:st_co_id order by st_rank asc, st_id asc";

	// city
	/** 區域流水號搜尋 */
	public static final String HQL_SELECT_CITY_BY_STATE = "from CityBean where ci_st_id=:ci_st_id order by ci_rank asc, ci_id asc";

	// base
	/** 類別流水號搜尋 */
	public static final String HQL_SELECT_BASE_BY_CATEGORY = "from BaseBean where ba_ca_id=:ba_ca_id order by ba_status desc, ba_id asc";

	// image
	/** 類別流水號搜尋 */
	public static final String HQL_SELECT_IMAGE_BY_CATEGORY = "from ImageBean where im_ca_id=:im_ca_id order by im_status desc, im_rank asc, im_id asc";

	// video
	/** 類別流水號搜尋 */
	public static final String HQL_SELECT_VIDEO_BY_CATEGORY = "from VideoBean where vi_ca_id=:vi_ca_id order by vi_rank asc, vi_id asc";

	/** 搜尋開啟的影片 */
	public static final String HQL_SELECT_OPEN_VIDEO = "from VideoBean where vi_ca_id=:vi_ca_id and vi_status=1";

	// html
	/** 類別流水號搜尋 */
	public static final String HQL_SELECT_HTML_BY_CATEGORY = "from HtmlBean where ht_ca_id=:ht_ca_id order by ht_rank asc, ht_id asc";

	/** 搜尋開啟的 html */
	public static final String HQL_SELECT_OPEN_HTML = "from HtmlBean where ht_ca_id=:ht_ca_id and ht_status=1";

}
