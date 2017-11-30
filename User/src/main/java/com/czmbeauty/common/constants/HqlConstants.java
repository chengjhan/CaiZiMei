package com.czmbeauty.common.constants;

public interface HqlConstants {

	// category_path
	/** extension 搜尋 */
	public static final String HQL_SELECT_CATEGORY_PATH_BY_EXTENSION = "from CategoryPathBean where cp_extension=:cp_extension";

	// category
	/** 類別資料夾名稱搜尋 */
	public static final String HQL_SELECT_CATEGORY_BY_DIRECTORY = "from CategoryBean where ca_directory=:ca_directory";

	// base
	/** 搜尋開啟的據點 */
	public static final String HQL_SELECT_OPEN_BASE = "from BaseBean where ba_status=1 order by ba_id asc";

}
