/*
 * CaiZiMei
 * File: AdministratorController.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.caizimei.model.entity.AdministratorBean;
import com.caizimei.model.service.AdministratorService;

import misc.PrimitiveNumberEditor;

/**
 * administrator controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "admin")
public class AdministratorController {

	/**
	 * 注入 AdministratorService
	 */
	@Autowired
	private AdministratorService administratorService;

	/**
	 * 注入 SimpleDateFormat
	 */
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	/**
	 * 提供 form-backing bean 資料轉換
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class, new PrimitiveNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(Double.class, new PrimitiveNumberEditor(Double.class, true));
		webDataBinder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(simpleDateFormat, true));
	}

	/**
	 * 登入
	 * 
	 * @param a_username-->管理員帳號
	 * @param a_password-->管理員密碼(原碼)
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/admin/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "a_username") String a_username,
			@RequestParam(name = "a_password") String a_password, HttpServletRequest request, Model model) {

		AdministratorBean administratorBean = administratorService.selectByA_username(a_username);

		if (administratorBean != null) {

			if (administratorService.signIn(a_username, a_password)) {

				administratorService.updateA_signin_ip(administratorBean.getA_id(), request.getRemoteAddr());
				administratorService.updateA_signin_time(administratorBean.getA_id());
				model.addAttribute("admin", administratorService.selectByA_username(a_username));

				return "redirect:/admin/index";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "帳號或密碼錯誤");

				return "admin/secure/sign-in";
			}
		} else {

			// 帳號錯誤
			model.addAttribute("error", "帳號或密碼錯誤");

			return "admin/secure/sign-in";
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param a_username-->管理員信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/secure/set-password.jsp
	 */
	@RequestMapping(path = "/admin/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "a_username") String a_username, Model model) {

		int random = (int) (Math.random() * 1000000);
		String a_password_random = String.valueOf(random);

		AdministratorBean administratorBean = administratorService.selectByA_username(a_username);
		administratorService.updateA_password(administratorBean.getA_id(), a_password_random,
				administratorBean.getA_salt());

		String to = a_username;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = a_password_random;
		administratorService.sendEmail(to, from, subject, text);

		model.addAttribute("admin", administratorBean);

		return "redirect:/admin/secure/set-password";
	}

	/**
	 * 重設管理員密碼
	 * 
	 * @param a_password-->驗證碼(原碼)
	 * @param a_password_new-->新密碼(原碼)
	 * @param admin-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/admin/secure/sign-in.jsp
	 * @return /WEB-INF/views/admin/secure/set-password.jsp
	 */
	@RequestMapping(path = "/admin/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "a_password") String a_password,
			@RequestParam(name = "a_password_new") String a_password_new,
			@ModelAttribute("admin") AdministratorBean admin, SessionStatus sessionStatus) {

		String oldHashedPassword = administratorService.selectByA_id(admin.getA_id()).getA_password();
		String inputOldHashedPassword = administratorService.getHashedPassword(a_password, admin.getA_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			administratorService.updateA_password(admin.getA_id(), a_password_new, admin.getA_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			return "redirect:/admin/secure/sign-in";
		} else {
			return "admin/secure/set-password";
		}
	}

	/**
	 * 註冊
	 * 
	 * @param administratorBean-->AdministratorBean
	 * @param a_username-->管理員帳號
	 * @param a_password-->管理員密碼(原碼)
	 * @param model-->Model
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/administrator/sign-up.jsp
	 */
	@RequestMapping(path = "/admin/administrator/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(AdministratorBean administratorBean,
			@RequestParam(name = "a_username") String a_username, @RequestParam(name = "a_password") String a_password,
			HttpServletRequest request, Model model) {

		if (administratorService.selectByA_username(a_username) == null) {

			String a_salt = administratorService.getSalt();

			administratorBean.setA_salt(a_salt);
			administratorBean.setA_password(administratorService.getHashedPassword(a_password, a_salt));
			administratorBean.setA_signin_number(1);
			administratorBean.setA_signin_ip(request.getRemoteAddr());
			administratorBean.setA_signin_time(new java.util.Date());
			administratorBean.setA_update_pass_time(new java.util.Date());
			administratorBean.setA_update_info_time(new java.util.Date());
			administratorService.signUp(administratorBean);

			model.addAttribute("admin", administratorBean);

			return "redirect:/admin/index";
		} else {
			return "redirect:/admin/administrator/sign-up";
		}
	}

}
