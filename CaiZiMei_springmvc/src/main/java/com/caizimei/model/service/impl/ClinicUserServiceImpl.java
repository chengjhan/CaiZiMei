/*
 * CaiZiMei
 * File: ClinicUserServiceImpl.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.ClinicUserDAO;
import com.caizimei.model.entity.ClinicUserBean;
import com.caizimei.model.service.ClinicUserService;

/**
 * clinic_user service implement
 * 
 * @author 詹晟
 */
@Service(value = "clinicUserService")
public class ClinicUserServiceImpl implements ClinicUserService {

	/**
	 * 注入 ClinicUserDAO
	 */
	@Autowired
	private ClinicUserDAO clinicUserDAO;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param clinicUserBean-->ClinicUserBean
	 * @return result-->ClinicUserBean
	 */
	@Override
	@Transactional
	public ClinicUserBean signUp(ClinicUserBean clinicUserBean) {

		ClinicUserBean result = null;

		if (clinicUserBean != null) {

			result = clinicUserDAO.insert(clinicUserBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param cu_username-->診所使用者信箱
	 * @param cu_password-->診所使用者密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String cu_username, String cu_password) {

		ClinicUserBean clinicUserBean = clinicUserDAO.selectByCu_username(cu_username);

		String cu_salt = clinicUserBean.getCu_salt();

		if (getHashedPassword(cu_password, cu_salt).equals(clinicUserBean.getCu_password())) {

			clinicUserDAO.updateCu_signin_number(clinicUserBean.getCu_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 診所使用者流水號搜尋
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ClinicUserBean selectByCu_id(Integer cu_id) {

		return clinicUserDAO.selectByCu_id(cu_id);
	}

	/**
	 * 診所使用者帳號搜尋
	 * 
	 * @param cu_username-->診所使用者帳號
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ClinicUserBean selectByCu_username(String cu_username) {

		return clinicUserDAO.selectByCu_username(cu_username);
	}

	/**
	 * 診所使用者信箱搜尋
	 * 
	 * @param cu_email-->診所使用者信箱
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ClinicUserBean selectByCu_email(String cu_email) {

		return clinicUserDAO.selectByCu_email(cu_email);
	}

	/**
	 * 修改資料
	 * 
	 * @param clinicUserBean-->ClinicUserBean
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional
	public ClinicUserBean update(ClinicUserBean clinicUserBean) {

		return clinicUserDAO.update(clinicUserBean);
	}

	/**
	 * 修改密碼
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @param cu_password_new-->新密碼(原碼)
	 * @param cu_salt-->塩
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional
	public ClinicUserBean updateCu_password(Integer cu_id, String cu_password_new, String cu_salt) {

		return clinicUserDAO.updateCu_password(cu_id, getHashedPassword(cu_password_new, cu_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @param cu_signin_ip-->登入IP
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional
	public ClinicUserBean updateCu_signin_ip(Integer cu_id, String cu_signin_ip) {

		return clinicUserDAO.updateCu_signin_ip(cu_id, cu_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param cu_id-->診所使用者流水號
	 * @return ClinicUserBean
	 */
	@Override
	@Transactional
	public ClinicUserBean updateCu_signin_time(Integer cu_id) {

		return clinicUserDAO.updateCu_signin_time(cu_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param cu_password-->診所使用者密碼(原碼)
	 * @param cu_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String cu_password, String cu_salt) {

		return getMD5(cu_salt.replaceAll("-", getMD5(cu_password)));
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

	/**
	 * 寄送 Email
	 * 
	 * @param to-->收件者
	 * @param from-->寄件者
	 * @param subject-->信件主旨
	 * @param text-->信件內容
	 */
	@Override
	public void sendEmail(String to, String from, String subject, String text) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);

		mailSender.send(simpleMailMessage);
	}

}
