package com.czmbeauty.common.constants;

public interface HqlConstants {

	// category
	/** 類別資料夾名稱搜尋 */
	public static final String HQL_SELECT_CATEGORY_BY_DIRECTORY = "from CategoryBean where ca_directory=:ca_directory";

	// base
	/** 搜尋開啟的據點 */
	public static final String HQL_SELECT_OPEN_BASE = "from BaseBean where ba_status=1 order by ba_id asc";

	// image
	/** 類別流水號搜尋開啟的圖片 */
	public static final String HQL_SELECT_OPEN_IMAGE_BY_CATEGORY = "from ImageBean where im_ca_id=:im_ca_id and im_status=1 order by im_rank asc";

	// video
	/** 搜尋開啟的相關影音 */
	public static final String HQL_SELECT_OPEN_VIDEO_RELATED = "from VideoBean where vi_ca_id=5 and vi_status=1 order by vi_rank asc";

}
