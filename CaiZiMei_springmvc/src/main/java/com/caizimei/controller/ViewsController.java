/*
 * CaiZiMei
 * File: ViewsController.java
 * Author: 詹晟
 * Date: 2017/4/6
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
	 * user
	 */

	/**
	 * index 視圖解析
	 * 
	 * @return /WEB-INF/views/index.jsp
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {

		return "index";
	}

	/**
	 * member/forget-password 視圖解析
	 * 
	 * @return /WEB-INF/views/member/forget-password.jsp
	 */
	@RequestMapping(value = "/member/forget-password", method = RequestMethod.GET)
	public String member_forgetPassword() {

		return "member/forget-password";
	}

	/**
	 * member/profile 視圖解析
	 * 
	 * @return /WEB-INF/views/member/profile.jsp
	 */
	@RequestMapping(value = "/member/profile", method = RequestMethod.GET)
	public String member_profile() {

		return "member/profile";
	}

	/**
	 * member/set-password 視圖解析
	 * 
	 * @return /WEB-INF/views/member/set-password.jsp
	 */
	@RequestMapping(value = "/member/set-password", method = RequestMethod.GET)
	public String member_setPassword() {

		return "member/set-password";
	}

	/**
	 * member/sign-in 視圖解析
	 * 
	 * @return /WEB-INF/views/member/sign-in.jsp
	 */
	@RequestMapping(value = "/member/sign-in", method = RequestMethod.GET)
	public String member_signIn() {

		return "member/sign-in";
	}

	/**
	 * member/sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/member/sign-up.jsp
	 */
	@RequestMapping(value = "/member/sign-up", method = RequestMethod.GET)
	public String member_signUp() {

		return "member/sign-up";
	}

	/**
	 * member/update 視圖解析
	 * 
	 * @return /WEB-INF/views/member/update.jsp
	 */
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String member_update() {

		return "member/update";
	}

	/**
	 * member/update-password 視圖解析
	 * 
	 * @return /WEB-INF/views/member/update-password.jsp
	 */
	@RequestMapping(value = "/member/update-password", method = RequestMethod.GET)
	public String member_updatePassword() {

		return "member/update-password";
	}

	/**
	 * purchase/order 視圖解析
	 * 
	 * @return /WEB-INF/views/purchase/order.jsp
	 */
	@RequestMapping(value = "/purchase/order", method = RequestMethod.GET)
	public String purchase_order() {

		return "purchase/order";
	}

	/**
	 * admin
	 */

	/**
	 * admin/back 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/back.jsp
	 */
	@RequestMapping(value = "/admin/back", method = RequestMethod.GET)
	public String admin_back() {

		return "admin/back";
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
	 * admin/member/admin-sign-up 視圖解析
	 * 
	 * @return /WEB-INF/views/admin/member/admin-sign-up.jsp
	 */
	@RequestMapping(value = "/admin/member/admin-sign-up", method = RequestMethod.GET)
	public String admin_member_adminSignUp() {

		return "admin/member/admin-sign-up";
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

}
