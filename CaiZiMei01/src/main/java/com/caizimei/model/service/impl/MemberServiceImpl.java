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
	public Boolean signIn(String m_account, String m_password_MD5) {
		MemberBean memberBean = memberDAO.selectByM_account(m_account);
		if (memberBean != null) {
			if (memberBean.getM_password().equals(m_password_MD5)) {
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
			result = memberDAO.insert(memberBean);
		}
		return result;
	}

	// 流水號查詢
	@Override
	@Transactional(readOnly = true)
	public MemberBean selectByM_id(Integer m_id) {
		return memberDAO.selectByM_id(m_id);
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

	// 修改會員資料
	@Override
	@Transactional
	public MemberBean update(MemberBean memberBean) {
		return memberDAO.update(memberBean);
	}

	// 修改密碼
	@Override
	@Transactional
	public MemberBean updateM_password(Integer m_id, String m_password_new_MD5) {
		return memberDAO.updateM_password(m_id, m_password_new_MD5);
	}

	// 轉換密碼為 MD5
	@Override
	public String passwordToMD5(String m_password) {
		String m_password_MD5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] passwordBytes = m_password.getBytes();
			md.update(passwordBytes);
			byte[] digestBytes = md.digest();
			BigInteger digestInt = new BigInteger(1, digestBytes);
			m_password_MD5 = digestInt.toString(16);
			while (m_password_MD5.length() < 32) {
				m_password_MD5 = "0" + m_password_MD5;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return m_password_MD5;
	}

}
