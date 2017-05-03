/*
 * CaiZiMei
 * File: ClinicUserController.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.caizimei.model.entity.ClinicUserBean;
import com.caizimei.model.service.ClinicService;
import com.caizimei.model.service.ClinicUserService;

import misc.PrimitiveNumberEditor;

/**
 * clinic_user controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "clinic")
public class ClinicUserController {

	/**
	 * 注入 ClinicUserService
	 */
	@Autowired
	private ClinicUserService clinicUserService;

	/**
	 * 注入 ClinicService
	 */
	@Autowired
	private ClinicService clinicService;

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
	 * @param cu_username-->診所使用者帳號
	 * @param cu_password-->診所使用者密碼(原碼)
	 * @param model-->Model
	 * @param request-->HttpServletRequest
	 * @return /WEB-INF/views/service/index.jsp
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/service/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "cu_username") String cu_username,
			@RequestParam(name = "cu_password") String cu_password, HttpServletRequest request, Model model) {

		ClinicUserBean clinicUserBean = clinicUserService.selectByCu_username(cu_username);

		if (clinicUserBean != null) {

			if (clinicUserService.signIn(cu_username, cu_password)) {

				clinicUserService.updateCu_signin_ip(clinicUserBean.getCu_id(), request.getRemoteAddr());
				clinicUserService.updateCu_signin_time(clinicUserBean.getCu_id());
				model.addAttribute("clinic", clinicUserService.selectByCu_username(cu_username));

				return "redirect:/service/index";
			} else {

				// 密碼錯誤
				model.addAttribute("error", "帳號或密碼錯誤");

				return "service/secure/sign-in";
			}
		} else {

			// 帳號錯誤
			model.addAttribute("error", "帳號或密碼錯誤");

			return "service/secure/sign-in";
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param cu_username-->診所使用者信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/service/secure/set-password.jsp
	 */
	@RequestMapping(path = "/service/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "cu_username") String cu_username, Model model) {

		int random = (int) (Math.random() * 1000000);
		String cu_password_random = String.valueOf(random);

		ClinicUserBean clinicUserBean = clinicUserService.selectByCu_username(cu_username);
		clinicUserService.updateCu_password(clinicUserBean.getCu_id(), cu_password_random, clinicUserBean.getCu_salt());

		String to = cu_username;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = cu_password_random;
		clinicUserService.sendEmail(to, from, subject, text);

		model.addAttribute("clinic", clinicUserBean);

		return "redirect:/service/secure/set-password";
	}

	/**
	 * 重設診所使用者密碼
	 * 
	 * @param cu_password-->驗證碼(原碼)
	 * @param cu_password_new-->新密碼(原碼)
	 * @param service-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 * @return /WEB-INF/views/service/secure/set-password.jsp
	 */
	@RequestMapping(path = "/service/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "cu_password") String cu_password,
			@RequestParam(name = "cu_password_new") String cu_password_new,
			@ModelAttribute("clinic") ClinicUserBean clinic, SessionStatus sessionStatus) {

		String oldHashedPassword = clinicUserService.selectByCu_id(clinic.getCu_id()).getCu_password();
		String inputOldHashedPassword = clinicUserService.getHashedPassword(cu_password, clinic.getCu_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			clinicUserService.updateCu_password(clinic.getCu_id(), cu_password_new, clinic.getCu_salt());

			// 清除 @SessionAttributes
			sessionStatus.setComplete();

			return "redirect:/service/secure/sign-in";
		} else {
			return "service/secure/set-password";
		}
	}

	/**
	 * 登出
	 * 
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/service/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(HttpSession session, SessionStatus sessionStatus) {

		// 清除 HttpSession
		if (session.getAttribute("clinic") != null) {
			session.removeAttribute("clinic"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/service/index";
	}

	/**
	 * 註冊
	 * 
	 * @param clinicUserBean-->ClinicUserBean
	 * @param cu_username-->診所使用者帳號
	 * @param cu_password-->診所使用者密碼(原碼)
	 * @return /WEB-INF/views/admin/index.jsp
	 * @return /WEB-INF/views/admin/clinicUser/sign-up.jsp
	 */
	@RequestMapping(path = "/admin/clinicUser/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(ClinicUserBean clinicUserBean, @RequestParam(name = "cu_username") String cu_username,
			@RequestParam(name = "cu_password") String cu_password, @RequestParam(name = "cu_c_id") Integer cu_c_id) {

		if (clinicUserService.selectByCu_username(cu_username) == null) {

			String cu_salt = clinicUserService.getSalt();

			clinicUserBean.setCu_salt(cu_salt);
			clinicUserBean.setCu_password(clinicUserService.getHashedPassword(cu_password, cu_salt));
			clinicUserBean.setCu_signin_number(0);
			clinicUserBean.setCu_update_pass_time(new java.util.Date());
			clinicUserBean.setCu_update_info_time(new java.util.Date());
			clinicUserBean.setCu_ClinicBean(clinicService.selectByC_id(cu_c_id));
			clinicUserService.signUp(clinicUserBean);

			return "redirect:/admin/index";
		} else {
			return "redirect:/admin/clinicUser/sign-up";
		}
	}

	/**
	 * 修改資料
	 * 
	 * @param service-->Session
	 * @param clinicUserBean-->ClinicUserBean
	 * @return /WEB-INF/views/service/index.jsp
	 */
	@RequestMapping(path = "/service/clinicUser/update.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("clinic") ClinicUserBean clinic, ClinicUserBean clinicUserBean) {

		clinicUserBean.setCu_id(clinic.getCu_id());
		clinicUserService.update(clinicUserBean);

		return "redirect:/service/index";
	}

	/**
	 * 修改密碼
	 * 
	 * @param cu_password-->舊密碼(原碼)
	 * @param cu_password_new-->新密碼(原碼)
	 * @param service-->Session
	 * @return /WEB-INF/views/service/index.jsp
	 * @return /WEB-INF/views/service/clinicUser/update-password.jsp
	 */
	@RequestMapping(path = "/service/clinicUser/update-password.do", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "cu_password") String cu_password,
			@RequestParam(name = "cu_password_new") String cu_password_new,
			@ModelAttribute("clinic") ClinicUserBean clinic) {

		String oldHashedPassword = clinicUserService.selectByCu_id(clinic.getCu_id()).getCu_password();
		String inputOldHashedPassword = clinicUserService.getHashedPassword(cu_password, clinic.getCu_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			clinicUserService.updateCu_password(clinic.getCu_id(), cu_password_new, clinic.getCu_salt());

			return "redirect:/service/index";
		} else {
			return "service/clinicUser/update-password";
		}
	}

}
