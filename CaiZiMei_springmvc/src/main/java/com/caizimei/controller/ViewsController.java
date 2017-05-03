/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * views controller (視圖解析)
 * 
 * @author 詹晟
 */
@Controller
public class ViewsController {

	/**
	 * admin
	 */

	/**
	 * admin/index 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/index.jsp
	 */
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public String admin_index() {

		return "admin/index";
	}

	/**
	 * admin/agent/list 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/agent/list.jsp
	 */
	@RequestMapping(value = "/admin/agent/list", method = RequestMethod.GET)
	public String admin_agent_list() {

		return "admin/agent/list";
	}

	/**
	 * admin/agent/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/agent/update.jsp
	 */
	@RequestMapping(value = "/admin/agent/update", method = RequestMethod.GET)
	public String admin_agent_update() {

		return "admin/agent/update";
	}

	/**
	 * admin/agent-user/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/agent-user/search.jsp
	 */
	@RequestMapping(value = "/admin/agent-user/search", method = RequestMethod.GET)
	public String admin_agentUser_search() {

		return "admin/agent-user/search";
	}

	/**
	 * admin/agent-user/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/agent-user/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/agent-user/sign-up", method = RequestMethod.GET)
	public String admin_agentUser_signUp() {

		return "admin/agent-user/sign-up";
	}

	/**
	 * admin/city/list 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/city/list.jsp
	 */
	@RequestMapping(value = "/admin/city/list", method = RequestMethod.GET)
	public String admin_city_list() {

		return "admin/city/list";
	}

	/**
	 * admin/city/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/city/update.jsp
	 */
	@RequestMapping(value = "/admin/city/update", method = RequestMethod.GET)
	public String admin_city_update() {

		return "admin/city/update";
	}

	/**
	 * admin/clinic/list 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/clinic/list.jsp
	 */
	@RequestMapping(value = "/admin/clinic/list", method = RequestMethod.GET)
	public String admin_clinic_list() {

		return "admin/clinic/list";
	}

	/**
	 * admin/clinic/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/clinic/search.jsp
	 */
	@RequestMapping(value = "/admin/clinic/search", method = RequestMethod.GET)
	public String admin_clinic_search() {

		return "admin/clinic/search";
	}

	/**
	 * admin/clinic/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/clinic/update.jsp
	 */
	@RequestMapping(value = "/admin/clinic/update", method = RequestMethod.GET)
	public String admin_clinic_update() {

		return "admin/clinic/update";
	}

	/**
	 * admin/clinic-user/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/clinic-user/search.jsp
	 */
	@RequestMapping(value = "/admin/clinic-user/search", method = RequestMethod.GET)
	public String admin_clinicUser_search() {

		return "admin/clinic-user/search";
	}

	/**
	 * admin/clinic-user/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/clinic-user/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/clinic-user/sign-up", method = RequestMethod.GET)
	public String admin_clinicUser_signUp() {

		return "admin/clinic-user/sign-up";
	}

	/**
	 * admin/country/list 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/country/list.jsp
	 */
	@RequestMapping(value = "/admin/country/list", method = RequestMethod.GET)
	public String admin_country_list() {

		return "admin/country/list";
	}

	/**
	 * admin/country/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/country/update.jsp
	 */
	@RequestMapping(value = "/admin/country/update", method = RequestMethod.GET)
	public String admin_country_update() {

		return "admin/country/update";
	}

	/**
	 * admin/member/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/member/search.jsp
	 */
	@RequestMapping(value = "/admin/member/search", method = RequestMethod.GET)
	public String admin_member_search() {

		return "admin/member/search";
	}

	/**
	 * admin/region/insert 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/region/insert.jsp
	 */
	@RequestMapping(value = "/admin/region/insert", method = RequestMethod.GET)
	public String admin_region_insert() {

		return "admin/region/insert";
	}

	/**
	 * admin/region/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/region/search.jsp
	 */
	@RequestMapping(value = "/admin/region/search", method = RequestMethod.GET)
	public String admin_region_search() {

		return "admin/region/search";
	}

	/**
	 * admin/region/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/region/update.jsp
	 */
	@RequestMapping(value = "/admin/region/update", method = RequestMethod.GET)
	public String admin_region_update() {

		return "admin/region/update";
	}

	/**
	 * admin/secure/forget-password 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/admin/secure/forget-password", method = RequestMethod.GET)
	public String admin_secure_forgetPassword() {

		return "admin/secure/forget-password";
	}

	/**
	 * admin/secure/set-password 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/secure/set-password.jsp
	 */
	@RequestMapping(value = "/admin/secure/set-password", method = RequestMethod.GET)
	public String admin_secure_setPassword() {

		return "admin/secure/set-password";
	}

	/**
	 * admin/secure/sign-in 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/admin/secure/sign-in", method = RequestMethod.GET)
	public String admin_secure_signIn() {

		return "admin/secure/sign-in";
	}

	/**
	 * admin/user/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/user/profile.jsp
	 */
	@RequestMapping(value = "/admin/user/profile", method = RequestMethod.GET)
	public String admin_user_profile() {

		return "admin/user/profile";
	}

	/**
	 * admin/user/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/user/search.jsp
	 */
	@RequestMapping(value = "/admin/user/search", method = RequestMethod.GET)
	public String admin_user_search() {

		return "admin/user/search";
	}

	/**
	 * admin/user/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/user/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/user/sign-up", method = RequestMethod.GET)
	public String admin_user_signUp() {

		return "admin/user/sign-up";
	}

	/**
	 * admin/user/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/user/update.jsp
	 */
	@RequestMapping(value = "/admin/user/update", method = RequestMethod.GET)
	public String admin_user_update() {

		return "admin/user/update";
	}

	/**
	 * admin/user/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/user/update-password.jsp
	 */
	@RequestMapping(value = "/admin/user/update-password", method = RequestMethod.GET)
	public String admin_user_updatePassword() {

		return "admin/user/update-password";
	}

	/**
	 * agent
	 */

	/**
	 * agent/index 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/index.jsp
	 */
	@RequestMapping(value = "/agent/index", method = RequestMethod.GET)
	public String agent_index() {

		return "agent/index";
	}

	/**
	 * agent/member/search 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/member/search.jsp
	 */
	@RequestMapping(value = "/agent/member/search", method = RequestMethod.GET)
	public String agent_member_search() {

		return "agent/member/search";
	}

	/**
	 * agent/secure/forget-password 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/agent/secure/forget-password", method = RequestMethod.GET)
	public String agent_secure_forgetPassword() {

		return "agent/secure/forget-password";
	}

	/**
	 * agent/secure/set-password 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/secure/set-password.jsp
	 */
	@RequestMapping(value = "/agent/secure/set-password", method = RequestMethod.GET)
	public String agent_secure_setPassword() {

		return "agent/secure/set-password";
	}

	/**
	 * agent/secure/sign-in 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/agent/secure/sign-in", method = RequestMethod.GET)
	public String agent_secure_signIn() {

		return "agent/secure/sign-in";
	}

	/**
	 * agent/user/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/user/profile.jsp
	 */
	@RequestMapping(value = "/agent/user/profile", method = RequestMethod.GET)
	public String agent_user_profile() {

		return "agent/user/profile";
	}

	/**
	 * agent/user/update 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/user/update.jsp
	 */
	@RequestMapping(value = "/agent/user/update", method = RequestMethod.GET)
	public String agent_user_update() {

		return "agent/user/update";
	}

	/**
	 * agent/user/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/user/update-password.jsp
	 */
	@RequestMapping(value = "/agent/user/update-password", method = RequestMethod.GET)
	public String agent_user_updatePassword() {

		return "agent/user/update-password";
	}

	/**
	 * clinic
	 */

	/**
	 * clinic/index 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/index.jsp
	 */
	@RequestMapping(value = "/clinic/index", method = RequestMethod.GET)
	public String clinic_index() {

		return "clinic/index";
	}

	/**
	 * clinic/member/search 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/member/search.jsp
	 */
	@RequestMapping(value = "/clinic/member/search", method = RequestMethod.GET)
	public String clinic_member_search() {

		return "clinic/member/search";
	}

	/**
	 * clinic/purchase/search 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/purchase/search.jsp
	 */
	@RequestMapping(value = "/clinic/purchase/search", method = RequestMethod.GET)
	public String clinic_purchase_search() {

		return "clinic/purchase/search";
	}

	/**
	 * clinic/secure/forget-password 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/clinic/secure/forget-password", method = RequestMethod.GET)
	public String clinic_secure_forgetPassword() {

		return "clinic/secure/forget-password";
	}

	/**
	 * clinic/secure/set-password 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/secure/set-password.jsp
	 */
	@RequestMapping(value = "/clinic/secure/set-password", method = RequestMethod.GET)
	public String clinic_secure_setPassword() {

		return "clinic/secure/set-password";
	}

	/**
	 * clinic/secure/sign-in 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/clinic/secure/sign-in", method = RequestMethod.GET)
	public String clinic_secure_signIn() {

		return "clinic/secure/sign-in";
	}

	/**
	 * clinic/user/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/user/profile.jsp
	 */
	@RequestMapping(value = "/clinic/user/profile", method = RequestMethod.GET)
	public String clinic_user_profile() {

		return "clinic/user/profile";
	}

	/**
	 * clinic/user/update 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/user/update.jsp
	 */
	@RequestMapping(value = "/clinic/user/update", method = RequestMethod.GET)
	public String clinic_user_update() {

		return "clinic/user/update";
	}

	/**
	 * clinic/user/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/clinic/user/update-password.jsp
	 */
	@RequestMapping(value = "/clinic/user/update-password", method = RequestMethod.GET)
	public String clinic_user_updatePassword() {

		return "clinic/user/update-password";
	}

	/**
	 * user
	 */

	/**
	 * user/index 視圖解析
	 * 
	 * @return /WEB-INF/views/user/index.jsp
	 */
	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String user_index() {

		return "user/index";
	}

	/**
	 * user/member/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/user/member/profile.jsp
	 */
	@RequestMapping(value = "/user/member/profile", method = RequestMethod.GET)
	public String user_member_profile() {

		return "user/member/profile";
	}

	/**
	 * user/member/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/user/member/sign-up.jsp
	 */
	@RequestMapping(value = "/user/member/sign-up", method = RequestMethod.GET)
	public String user_member_signUp() {

		return "user/member/sign-up";
	}

	/**
	 * user/member/update 視圖解析
	 * 
	 * @return /WEB-INF/views/user/member/update.jsp
	 */
	@RequestMapping(value = "/user/member/update", method = RequestMethod.GET)
	public String user_member_update() {

		return "user/member/update";
	}

	/**
	 * user/member/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/user/member/update-password.jsp
	 */
	@RequestMapping(value = "/user/member/update-password", method = RequestMethod.GET)
	public String user_member_updatePassword() {

		return "user/member/update-password";
	}

	/**
	 * user/purchase/order 視圖解析
	 * 
	 * @return /WEB-INF/views/user/purchase/order.jsp
	 */
	@RequestMapping(value = "/user/purchase/order", method = RequestMethod.GET)
	public String user_purchase_order() {

		return "user/purchase/order";
	}

	/**
	 * user/purchase/search 視圖解析
	 * 
	 * @return /WEB-INF/views/user/purchase/search.jsp
	 */
	@RequestMapping(value = "/user/purchase/search", method = RequestMethod.GET)
	public String user_purchase_search() {

		return "user/purchase/search";
	}

	/**
	 * user/secure/forget-password 視圖解析
	 * 
	 * @return /WEB-INF/views/user/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/user/secure/forget-password", method = RequestMethod.GET)
	public String user_secure_forgetPassword() {

		return "user/secure/forget-password";
	}

	/**
	 * user/secure/set-password 視圖解析
	 * 
	 * @return /WEB-INF/views/user/secure/set-password.jsp
	 */
	@RequestMapping(value = "/user/secure/set-password", method = RequestMethod.GET)
	public String user_secure_setPassword() {

		return "user/secure/set-password";
	}

	/**
	 * user/secure/sign-in 視圖解析
	 * 
	 * @return /WEB-INF/views/user/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/user/secure/sign-in", method = RequestMethod.GET)
	public String user_secure_signIn() {

		return "user/secure/sign-in";
	}

}
