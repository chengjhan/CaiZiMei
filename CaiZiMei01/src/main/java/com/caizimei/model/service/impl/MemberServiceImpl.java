package com.caizimei.model.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.MemberBean;
import com.caizimei.model.dao.MemberDAO;
import com.caizimei.model.service.MemberService;

@Service(value = "memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDAO;

	// 登入
	@Override
	@Transactional
	public Boolean signIn(String m_account, String m_password) {
		MemberBean memberBean = memberDAO.selectByM_account(m_account);
		if (memberBean != null) {
			if (memberBean.getM_password().equals(passwordToMD5(m_password))) {
				memberDAO.updateM_signin_time(memberBean.getM_id());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// 註冊
	@Override
	@Transactional
	public MemberBean signUp(MemberBean memberBean) {
		MemberBean result = null;
		if (memberBean != null) {
			memberBean.setM_password(passwordToMD5(memberBean.getM_password()));
			result = memberDAO.insert(memberBean);
		}
		return result;
	}

	// 修改密碼
	@Override
	@Transactional
	public MemberBean updateM_password(Integer m_id, String m_password) {
		return memberDAO.updateM_password(m_id, passwordToMD5(m_password));
	}

	// 帳號查詢
	@Override
	@Transactional(readOnly = true)
	public MemberBean selectByM_account(String m_account) {
		return memberDAO.selectByM_account(m_account);
	}

	// 條件查詢
	@Override
	@Transactional(readOnly = true)
	public List<MemberBean> selectByConditions(String m_firstname, String m_lastname, String m_telephone,
			String m_email) {
		return memberDAO.selectByConditions(m_firstname, m_lastname, m_telephone, m_email);
	}

	// 轉換密碼為 MD5
	@Override
	public String passwordToMD5(String m_password) {
		String passwordMD5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passwordBytes = m_password.getBytes();
			md.update(passwordBytes);
			byte[] digestBytes = md.digest();
			BigInteger digestInt = new BigInteger(1, digestBytes);
			passwordMD5 = digestInt.toString(16);
			while (passwordMD5.length() < 32) {
				passwordMD5 = "0" + passwordMD5;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return passwordMD5;
	}

}
