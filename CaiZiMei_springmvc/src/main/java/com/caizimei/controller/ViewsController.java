/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/5/1
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
	 * admin/administrator/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/administrator/profile.jsp
	 */
	@RequestMapping(value = "/admin/administrator/profile", method = RequestMethod.GET)
	public String admin_administrator_profile() {

		return "admin/administrator/profile";
	}

	/**
	 * admin/administrator/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/administrator/search.jsp
	 */
	@RequestMapping(value = "/admin/administrator/search", method = RequestMethod.GET)
	public String admin_administrator_search() {

		return "admin/administrator/search";
	}

	/**
	 * admin/administrator/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/administrator/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/administrator/sign-up", method = RequestMethod.GET)
	public String admin_administrator_signUp() {

		return "admin/administrator/sign-up";
	}

	/**
	 * admin/administrator/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/administrator/update.jsp
	 */
	@RequestMapping(value = "/admin/administrator/update", method = RequestMethod.GET)
	public String admin_administrator_update() {

		return "admin/administrator/update";
	}

	/**
	 * admin/administrator/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/administrator/update-password.jsp
	 */
	@RequestMapping(value = "/admin/administrator/update-password", method = RequestMethod.GET)
	public String admin_administrator_updatePassword() {

		return "admin/administrator/update-password";
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
	 * admin/company/list 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/company/list.jsp
	 */
	@RequestMapping(value = "/admin/company/list", method = RequestMethod.GET)
	public String admin_company_list() {

		return "admin/company/list";
	}

	/**
	 * admin/company/update 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/company/update.jsp
	 */
	@RequestMapping(value = "/admin/company/update", method = RequestMethod.GET)
	public String admin_company_update() {

		return "admin/company/update";
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
	 * admin/employee/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/employee/search.jsp
	 */
	@RequestMapping(value = "/admin/employee/search", method = RequestMethod.GET)
	public String admin_employee_search() {

		return "admin/employee/search";
	}

	/**
	 * admin/employee/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/employee/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/employee/sign-up", method = RequestMethod.GET)
	public String admin_employee_signUp() {

		return "admin/employee/sign-up";
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
	 * admin/specialist/search 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/specialist/search.jsp
	 */
	@RequestMapping(value = "/admin/specialist/search", method = RequestMethod.GET)
	public String admin_specialist_search() {

		return "admin/specialist/search";
	}

	/**
	 * admin/specialist/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/specialist/sign-up.jsp
	 */
	@RequestMapping(value = "/admin/specialist/sign-up", method = RequestMethod.GET)
	public String admin_specialist_signUp() {

		return "admin/specialist/sign-up";
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
	 * agent/employee/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/employee/profile.jsp
	 */
	@RequestMapping(value = "/agent/employee/profile", method = RequestMethod.GET)
	public String agent_employee_profile() {

		return "agent/employee/profile";
	}

	/**
	 * agent/employee/update 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/employee/update.jsp
	 */
	@RequestMapping(value = "/agent/employee/update", method = RequestMethod.GET)
	public String agent_employee_update() {

		return "agent/employee/update";
	}

	/**
	 * agent/employee/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/agent/employee/update-password.jsp
	 */
	@RequestMapping(value = "/agent/employee/update-password", method = RequestMethod.GET)
	public String agent_employee_updatePassword() {

		return "agent/employee/update-password";
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
	 * service
	 */

	/**
	 * service/index 視圖解析
	 * 
	 * @return /WEB-INF/views/service/index.jsp
	 */
	@RequestMapping(value = "/service/index", method = RequestMethod.GET)
	public String service_index() {

		return "service/index";
	}

	/**
	 * service/member/search 視圖解析
	 * 
	 * @return /WEB-INF/views/service/member/search.jsp
	 */
	@RequestMapping(value = "/service/member/search", method = RequestMethod.GET)
	public String service_member_search() {

		return "service/member/search";
	}

	/**
	 * service/purchase/search 視圖解析
	 * 
	 * @return /WEB-INF/views/service/purchase/search.jsp
	 */
	@RequestMapping(value = "/service/purchase/search", method = RequestMethod.GET)
	public String service_purchase_search() {

		return "service/purchase/search";
	}

	/**
	 * service/secure/forget-password 視圖解析
	 * 
	 * @return /WEB-INF/views/service/secure/forget-password.jsp
	 */
	@RequestMapping(value = "/service/secure/forget-password", method = RequestMethod.GET)
	public String service_secure_forgetPassword() {

		return "service/secure/forget-password";
	}

	/**
	 * service/secure/set-password 視圖解析
	 * 
	 * @return /WEB-INF/views/service/secure/set-password.jsp
	 */
	@RequestMapping(value = "/service/secure/set-password", method = RequestMethod.GET)
	public String service_secure_setPassword() {

		return "service/secure/set-password";
	}

	/**
	 * service/secure/sign-in 視圖解析
	 * 
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 */
	@RequestMapping(value = "/service/secure/sign-in", method = RequestMethod.GET)
	public String service_secure_signIn() {

		return "service/secure/sign-in";
	}

	/**
	 * service/specialist/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/service/specialist/profile.jsp
	 */
	@RequestMapping(value = "/service/specialist/profile", method = RequestMethod.GET)
	public String service_specialist_profile() {

		return "service/specialist/profile";
	}

	/**
	 * service/specialist/update 視圖解析
	 * 
	 * @return /WEB-INF/views/service/specialist/update.jsp
	 */
	@RequestMapping(value = "/service/specialist/update", method = RequestMethod.GET)
	public String service_specialist_update() {

		return "service/specialist/update";
	}

	/**
	 * service/specialist/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/service/specialist/update-password.jsp
	 */
	@RequestMapping(value = "/service/specialist/update-password", method = RequestMethod.GET)
	public String service_specialist_updatePassword() {

		return "service/specialist/update-password";
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
