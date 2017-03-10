package com.caizimei.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.caizimei.model.MemberBean;
import com.caizimei.model.service.MemberService;

@Controller
@SessionAttributes("member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@RequestMapping(path = "/member/sign-in.controller", method = RequestMethod.POST)
	public String signInProcess(@RequestParam(name = "m_account") String m_account,
			@RequestParam(name = "m_password") String m_password, Model model) {
		if (memberService.signIn(m_account, m_password)) {
			model.addAttribute("member", memberService.selectByM_account(m_account));
			return "member.sign-in-success";
		} else {
			return "";
		}
	}

	@RequestMapping(path = "/member/sign-up.controller", method = RequestMethod.POST)
	public String signUpProcess(MemberBean memberBean, @RequestParam(name = "m_birth_year") String m_birth_year,
			@RequestParam(name = "m_birth_month") String m_birth_month,
			@RequestParam(name = "m_birth_date") String m_birth_date,
			@RequestParam(name = "m_telephone_front") String m_telephone_front,
			@RequestParam(name = "m_telephone_back") String m_telephone_back) {
		java.util.Date m_birth = null;
		try {
			m_birth = simpleDateFormat.parse(m_birth_year + "-" + m_birth_month + "-" + m_birth_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		memberBean.setM_birth(m_birth);
		memberBean.setM_telephone(m_telephone_front + "-" + m_telephone_back);
		memberService.signUp(memberBean);
		return "member.insert";
	}

	@RequestMapping(path = "/member/select.controller", method = RequestMethod.GET)
	public String selectByConditionsProcess(MemberBean memberBean, Model model) {
		model.addAttribute("selectByConditions", memberService.selectByConditions(memberBean.getM_firstname(),
				memberBean.getM_lastname(), memberBean.getM_telephone(), memberBean.getM_email()));
		return "member.search";
	}

}
