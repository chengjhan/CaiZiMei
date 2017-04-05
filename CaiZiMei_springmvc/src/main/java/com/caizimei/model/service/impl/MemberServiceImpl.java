/*
 * CaiZiMei
 * File: MemberServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/5
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

import com.caizimei.model.dao.MemberDAO;
import com.caizimei.model.entity.MemberBean;
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
	 * 註冊
	 * 
	 * @param memberBean-->MemberBean
	 * @return result-->MemberBean
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
	 * 登入
	 * 
	 * @param m_username-->會員帳號
	 * @param m_password-->會員密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String m_username, String m_password) {

		MemberBean memberBean = memberDAO.selectByM_username(m_username);

		String m_salt = memberBean.getM_salt();

		if (getHashedPassword(m_password, m_salt).equals(memberBean.getM_password())) {

			memberDAO.updateM_signin_number(memberBean.getM_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 會員流水號搜尋
	 * 
	 * @param m_id-->會員流水號
	 * @return MemberBean
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberBean selectByM_id(Integer m_id) {

		return memberDAO.selectByM_id(m_id);
	}

	/**
	 * 會員帳號搜尋
	 * 
	 * @param m_username-->會員帳號
	 * @return MemberBean
	 */
	@Override
	@Transactional(readOnly = true)
	public MemberBean selectByM_username(String m_username) {

		return memberDAO.selectByM_username(m_username);
	}

	/**
	 * 條件搜尋
	 * 
	 * @param m_lastname-->會員姓
	 * @param m_firstname-->會員名
	 * @param m_mobilephone-->會員電話
	 * @param m_email-->會員信箱
	 * @return List<MemberBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<MemberBean> selectByConditions(String m_lastname, String m_firstname, String m_mobilephone,
			String m_email) {

		return memberDAO.selectByConditions(m_firstname, m_lastname, m_mobilephone, m_email);
	}

	/**
	 * 修改會員資料
	 * 
	 * @param memberBean-->MemberBean
	 * @return MemberBean
	 */
	@Override
	@Transactional
	public MemberBean update(MemberBean memberBean) {

		return memberDAO.update(memberBean);
	}

	/**
	 * 修改會員信箱
	 * 
	 * @param m_id-->會員流水號
	 * @param m_email-->會員信箱
	 * @return MemberBean
	 */
	@Override
	@Transactional
	public MemberBean updateM_email(Integer m_id, String m_email) {

		return memberDAO.updateM_email(m_id, m_email);
	}

	/**
	 * 修改會員密碼
	 * 
	 * @param m_id-->會員流水號
	 * @param m_password_new-->新密碼(原碼)
	 * @param m_salt-->塩
	 * @return MemberBean
	 */
	@Override
	@Transactional
	public MemberBean updateM_password(Integer m_id, String m_password_new, String m_salt) {

		return memberDAO.updateM_password(m_id, getHashedPassword(m_password_new, m_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param m_id-->會員流水號
	 * @param m_signin_ip-->登入IP
	 * @return MemberBean
	 */
	@Override
	public MemberBean updateM_signin_ip(Integer m_id, String m_signin_ip) {

		return memberDAO.updateM_signin_ip(m_id, m_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param m_id-->會員流水號
	 * @return MemberBean
	 */
	@Override
	public MemberBean updateM_signin_time(Integer m_id) {

		return memberDAO.updateM_signin_time(m_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param m_password-->會員密碼(原碼)
	 * @param m_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String m_password, String m_salt) {

		return getMD5(m_salt.replaceAll("-", getMD5(m_password)));
	}

	/**
	 * 轉換為 MD5
	 * 
	 * @param str-->原始字串
	 * @return str_MD5-->MD5字串
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
	 * 
	 * @return 隨機UUID字串
	 */
	@Override
	public String getSalt() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

}
