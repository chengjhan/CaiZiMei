package com.czmbeauty.common.constants;

public interface HqlConstants {

	// category
	/** 類別資料夾名稱搜尋 */
	public static final String HQL_SELECT_CATEGORY_BY_DIRECTORY = "from CategoryBean where ca_directory=:ca_directory";

	// base
	/** 搜尋開啟的據點 */
	public static final String HQL_SELECT_OPEN_BASE = "from BaseBean where ba_status=1 order by ba_id asc";

	// user_view
	/** 視圖名搜尋 */
	public static final String HQL_SELECT_USER_VIEW_BY_VIEW_NAME = "from UserViewBean where uv_view_name=:uv_view_name";

	// category_url
	/** URL 類別 code 搜尋 */
	public static final String HQL_SELECT_CATEGORY_URL_BY_CODE = "from CategoryUrlBean where cu_code=:cu_code";

}
