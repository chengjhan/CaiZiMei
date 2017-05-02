/*
 * CaiZiMei
 * File: SpecialistServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/27
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
import com.caizimei.model.entity.SpecialistBean;
import com.caizimei.model.service.ClinicUserService;

/**
 * specialist service implement
 * 
 * @author 詹晟
 */
@Service(value = "specialistService")
public class SpecialistServiceImpl implements ClinicUserService {

	/**
	 * 注入 SpecialistDAO
	 */
	@Autowired
	private ClinicUserDAO specialistDAO;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param specialistBean-->SpecialistBean
	 * @return result-->SpecialistBean
	 */
	@Override
	@Transactional
	public SpecialistBean signUp(SpecialistBean specialistBean) {

		SpecialistBean result = null;

		if (specialistBean != null) {

			result = specialistDAO.insert(specialistBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param s_username-->專員信箱
	 * @param s_password-->專員密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String s_username, String s_password) {

		SpecialistBean specialistBean = specialistDAO.selectByS_username(s_username);

		String s_salt = specialistBean.getS_salt();

		if (getHashedPassword(s_password, s_salt).equals(specialistBean.getS_password())) {

			specialistDAO.updateS_signin_number(specialistBean.getS_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 專員流水號搜尋
	 * 
	 * @param s_id-->專員流水號
	 * @return SpecialistBean
	 */
	@Override
	@Transactional(readOnly = true)
	public SpecialistBean selectByS_id(Integer s_id) {

		return specialistDAO.selectByS_id(s_id);
	}

	/**
	 * 專員帳號搜尋
	 * 
	 * @param s_username-->專員帳號
	 * @return SpecialistBean
	 */
	@Override
	@Transactional(readOnly = true)
	public SpecialistBean selectByS_username(String s_username) {

		return specialistDAO.selectByS_username(s_username);
	}

	/**
	 * 修改專員資料
	 * 
	 * @param specialistBean-->SpecialistBean
	 * @return SpecialistBean
	 */
	@Override
	@Transactional
	public SpecialistBean update(SpecialistBean specialistBean) {

		return specialistDAO.update(specialistBean);
	}

	/**
	 * 修改專員密碼
	 * 
	 * @param s_id-->專員流水號
	 * @param s_password_new-->新密碼(原碼)
	 * @param s_salt-->塩
	 * @return SpecialistBean
	 */
	@Override
	@Transactional
	public SpecialistBean updateS_password(Integer s_id, String s_password_new, String s_salt) {

		return specialistDAO.updateS_password(s_id, getHashedPassword(s_password_new, s_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param s_id-->專員流水號
	 * @param s_signin_ip-->登入IP
	 * @return SpecialistBean
	 */
	@Override
	@Transactional
	public SpecialistBean updateS_signin_ip(Integer s_id, String s_signin_ip) {

		return specialistDAO.updateS_signin_ip(s_id, s_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param s_id-->專員流水號
	 * @return SpecialistBean
	 */
	@Override
	@Transactional
	public SpecialistBean updateS_signin_time(Integer s_id) {

		return specialistDAO.updateS_signin_time(s_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param s_password-->專員密碼(原碼)
	 * @param s_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String s_password, String s_salt) {

		return getMD5(s_salt.replaceAll("-", getMD5(s_password)));
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
