/*
 * CaiZiMei
 * File: MemberServiceImpl.java
 * Author: 詹晟
 * Date: 2017/3/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.MemberBean;
import com.caizimei.model.dao.MemberDAO;
import com.caizimei.model.service.MemberService;

/**
 * member service implement
 * 
 * @author 詹晟
 */
@Service(value = "memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

	/**
	 * 注入 MemberDAO
	 */
	@Autowired
	private MemberDAO memberDAO;

	/**
	 * 登入
	 */
	@Override
	@Transactional
	public Boolean signIn(String m_username, String m_password_hashed) {
		MemberBean memberBean = memberDAO.selectByM_username(m_username);
		if (memberBean != null) {
			if (memberBean.getM_password().equals(m_password_hashed)) {
				memberDAO.updateM_signin_time(memberBean.getM_id());
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 註冊
	 */
	@Override
	@Transactional
	public MemberBean signUp(MemberBean memberBean) {
		MemberBean result = null;
		if (memberBean != null) {
			result = memberDAO.insert(memberBean);
		}
		return result;
	}

	/**
	 * 流水號查詢
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberBean selectByM_id(Integer m_id) {
		return memberDAO.selectByM_id(m_id);
	}

	/**
	 * 帳號查詢
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberBean selectByM_username(String m_username) {
		return memberDAO.selectByM_username(m_username);
	}

	/**
	 * 條件查詢
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MemberBean> selectByConditions(String m_firstname, String m_lastname, String m_telephone,
			String m_email) {
		return memberDAO.selectByConditions(m_firstname, m_lastname, m_telephone, m_email);
	}

	/**
	 * 修改會員資料
	 */
	@Override
	@Transactional
	public MemberBean update(MemberBean memberBean) {
		return memberDAO.update(memberBean);
	}

	/**
	 * 修改密碼
	 */
	@Override
	@Transactional
	public MemberBean updateM_password(Integer m_id, String m_password_new_hashed) {
		return memberDAO.updateM_password(m_id, m_password_new_hashed);
	}

	/**
	 * 轉換為 MD5
	 */
	@Override
	public String getMD5(String str) {
		String str_MD5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] str_bytes = str.getBytes();
			md.update(str_bytes);
			byte[] digestBytes = md.digest();
			BigInteger digestInt = new BigInteger(1, digestBytes);
			str_MD5 = digestInt.toString(16);
			while (str_MD5.length() < 32) {
				str_MD5 = "0" + str_MD5;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str_MD5;
	}

	/**
	 * 產生 salt
	 */
	@Override
	public String getSalt() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * 製造雜湊密碼
	 */
	@Override
	public String getHashedPassword(String m_password, String m_salt) {
		return getMD5(getSalt().replaceAll("-", getMD5(m_password)));
	}

}
