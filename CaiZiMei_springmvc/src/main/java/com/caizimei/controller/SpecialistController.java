/*
 * CaiZiMei
 * File: SpecialistController.java
 * Author: 詹晟
 * Date: 2017/4/30
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

import com.caizimei.model.entity.SpecialistBean;
import com.caizimei.model.service.ClinicService;
import com.caizimei.model.service.SpecialistService;

import misc.PrimitiveNumberEditor;

/**
 * specialist controller
 * 
 * @author 詹晟
 */
@Controller
@SessionAttributes(value = "service")
public class SpecialistController {

	/**
	 * 注入 SpecialistService
	 */
	@Autowired
	private SpecialistService specialistService;

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
	 * @param s_username-->專員帳號
	 * @param s_password-->專員密碼(原碼)
	 * @param model-->Model
	 * @param request-->HttpServletRequest
	 * @return /WEB-INF/views/service/index.jsp
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/service/secure/sign-in.do", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "s_username") String s_username,
			@RequestParam(name = "s_password") String s_password, HttpServletRequest request, Model model) {

		SpecialistBean specialistBean = specialistService.selectByS_username(s_username);

		if (specialistBean != null) {

			if (specialistService.signIn(s_username, s_password)) {

				specialistService.updateS_signin_ip(specialistBean.getS_id(), request.getRemoteAddr());
				specialistService.updateS_signin_time(specialistBean.getS_id());
				model.addAttribute("service", specialistService.selectByS_username(s_username));

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
	 * @param s_username-->專員信箱
	 * @param model-->Model
	 * @return /WEB-INF/views/service/secure/set-password.jsp
	 */
	@RequestMapping(path = "/service/secure/forget-password.do", method = RequestMethod.POST)
	public String forgetPasswordProcess(@RequestParam(name = "s_username") String s_username, Model model) {

		int random = (int) (Math.random() * 1000000);
		String s_password_random = String.valueOf(random);

		SpecialistBean specialistBean = specialistService.selectByS_username(s_username);
		specialistService.updateS_password(specialistBean.getS_id(), s_password_random, specialistBean.getS_salt());

		String to = s_username;
		String from = "chengjhan@gmail.com";
		String subject = "更改密碼";
		String text = s_password_random;
		specialistService.sendEmail(to, from, subject, text);

		model.addAttribute("service", specialistBean);

		return "redirect:/service/secure/set-password";
	}

	/**
	 * 重設專員密碼
	 * 
	 * @param s_password-->驗證碼(原碼)
	 * @param s_password_new-->新密碼(原碼)
	 * @param service-->Session
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 * @return /WEB-INF/views/service/secure/set-password.jsp
	 */
	@RequestMapping(path = "/service/secure/set-password.do", method = RequestMethod.POST)
	public String setPasswordProcess(@RequestParam(name = "s_password") String s_password,
			@RequestParam(name = "s_password_new") String s_password_new,
			@ModelAttribute("service") SpecialistBean service, SessionStatus sessionStatus) {

		String oldHashedPassword = specialistService.selectByS_id(service.getS_id()).getS_password();
		String inputOldHashedPassword = specialistService.getHashedPassword(s_password, service.getS_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			specialistService.updateS_password(service.getS_id(), s_password_new, service.getS_salt());

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
	 * @param service-->Session
	 * @param session-->HttpSession
	 * @param sessionStatus-->SessionStatus
	 * @return /WEB-INF/views/service/secure/sign-in.jsp
	 */
	@RequestMapping(path = "/service/secure/sign-out.do", method = RequestMethod.GET)
	public String signOutProcess(@ModelAttribute("service") SpecialistBean service, HttpSession session,
			SessionStatus sessionStatus) {

		// 清除 HttpSession
		if (session.getAttribute("user") != null) {
			session.removeAttribute("user"); // 清除特定 HttpSession
		}
		session.invalidate(); // 清除所有 HttpSession

		// 清除 @SessionAttributes
		sessionStatus.setComplete();

		return "redirect:/service/index";
	}

	/**
	 * 註冊
	 * 
	 * @param specialistBean-->SpecialistBean
	 * @param s_username-->專員帳號
	 * @param s_password-->專員密碼(原碼)
	 * @param request-->HttpServletRequest
	 * @param model-->Model
	 * @return /WEB-INF/views/service/index.jsp
	 * @return /WEB-INF/views/service/specialist/sign-up.jsp
	 */
	@RequestMapping(path = "/service/specialist/sign-up.do", method = RequestMethod.POST)
	public String signUpProcess(SpecialistBean specialistBean, @RequestParam(name = "s_username") String s_username,
			@RequestParam(name = "s_password") String s_password, @RequestParam(name = "s_c_id") Integer s_c_id,
			HttpServletRequest request, Model model) {

		if (specialistService.selectByS_username(s_username) == null) {

			String s_salt = specialistService.getSalt();

			specialistBean.setS_salt(s_salt);
			specialistBean.setS_password(specialistService.getHashedPassword(s_password, s_salt));
			specialistBean.setS_signin_number(1);
			specialistBean.setS_signin_ip(request.getRemoteAddr());
			specialistBean.setS_signin_time(new java.util.Date());
			specialistBean.setS_update_pass_time(new java.util.Date());
			specialistBean.setS_update_info_time(new java.util.Date());
			specialistBean.setS_ClinicBean(clinicService.selectByC_id(s_c_id));
			specialistService.signUp(specialistBean);

			model.addAttribute("service", specialistBean);

			return "redirect:/service/index";
		} else {
			return "redirect:/service/specialist/sign-up";
		}
	}

	/**
	 * 修改專員資料
	 * 
	 * @param service-->Session
	 * @param specialistBean-->SpecialistBean
	 * @return /WEB-INF/views/service/index.jsp
	 */
	@RequestMapping(path = "/service/specialist/update.do", method = RequestMethod.POST)
	public String updateProcess(@ModelAttribute("service") SpecialistBean service, SpecialistBean specialistBean) {

		specialistBean.setS_id(service.getS_id());
		specialistService.update(specialistBean);

		return "redirect:/service/index";
	}

	/**
	 * 修改專員密碼
	 * 
	 * @param s_password-->舊密碼(原碼)
	 * @param s_password_new-->新密碼(原碼)
	 * @param service-->Session
	 * @return /WEB-INF/views/service/index.jsp
	 * @return /WEB-INF/views/service/specialist/update-password.jsp
	 */
	@RequestMapping(path = "/service/specialist/update-password.do", method = RequestMethod.POST)
	public String updatePasswordProcess(@RequestParam(name = "s_password") String s_password,
			@RequestParam(name = "s_password_new") String s_password_new,
			@ModelAttribute("service") SpecialistBean service) {

		String oldHashedPassword = specialistService.selectByS_id(service.getS_id()).getS_password();
		String inputOldHashedPassword = specialistService.getHashedPassword(s_password, service.getS_salt());

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			specialistService.updateS_password(service.getS_id(), s_password_new, service.getS_salt());

			return "redirect:/service/index";
		} else {
			return "service/specialist/update-password";
		}
	}

}
