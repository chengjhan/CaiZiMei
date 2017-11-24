package com.czmbeauty.common.constants;

public interface ErrorMessages {

	// 登入
	public static final String ADMIN_USERNAME_REQUIRE_MSG = "請填入帳號。";

	public static final String ADMIN_PASSWORD_REQUIRE_MSG = "請填入密碼。";

	public static final String ADMIN_USERNAME_OR_PASSWORD_MISTAKE_MSG = "帳號或密碼錯誤。";

	// 忘記密碼
	public static final String ADMIN_EMAIL_REQUIRE_MSG = "請填入信箱。";

	public static final String ADMIN_EMAIL_MISTAKE_MSG = "信箱錯誤。";

	// 重設密碼
	public static final String ADMIN_RANDOM_MISTAKE_MSG = "驗證碼錯誤。";

	// 註冊
	public static final String ADMIN_USERNAME_REPEAT_MSG = "這個帳號已經被使用了";

	public static final String ADMIN_EMAIL_REPEAT_MSG = "這個信箱已經被使用了";

	// 變更密碼
	public static final String ADMIN_PASSWORD_OLD_MISTAKE_MSG = "密碼錯誤";

	// 新增據點/編輯據點
	public static final String BASE_ADDRESS_MISTAKE_MSG = "找不到經緯度，請再次確認地址";

}
