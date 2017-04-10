/*
 * CaiZiMei
 * File: AdministratorServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/10
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

import com.caizimei.model.dao.AdministratorDAO;
import com.caizimei.model.entity.AdministratorBean;
import com.caizimei.model.service.AdministratorService;

/**
 * administrator service implement
 * 
 * @author 詹晟
 */
@Service(value = "administratorService")
public class AdministratorServiceImpl implements AdministratorService {

	/**
	 * 注入 AdministratorDAO
	 */
	@Autowired
	private AdministratorDAO administratorDAO;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param administratorBean-->AdministratorBean
	 * @return result-->AdministratorBean
	 */
	@Override
	@Transactional
	public AdministratorBean signUp(AdministratorBean administratorBean) {

		AdministratorBean result = null;

		if (administratorBean != null) {

			result = administratorDAO.insert(administratorBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param a_username-->管理員信箱
	 * @param a_password-->管理員密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String a_username, String a_password) {

		AdministratorBean administratorBean = administratorDAO.selectByA_username(a_username);

		String a_salt = administratorBean.getA_salt();

		if (getHashedPassword(a_password, a_salt).equals(administratorBean.getA_password())) {

			administratorDAO.updateA_signin_number(administratorBean.getA_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param a_id-->管理員流水號
	 * @return AdministratorBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdministratorBean selectByA_id(Integer a_id) {

		return administratorDAO.selectByA_id(a_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param a_username-->管理員帳號
	 * @return AdministratorBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdministratorBean selectByA_username(String a_username) {

		return administratorDAO.selectByA_username(a_username);
	}

	/**
	 * 修改管理員資料
	 * 
	 * @param administratorBean-->AdministratorBean
	 * @return AdministratorBean
	 */
	@Override
	@Transactional
	public AdministratorBean update(AdministratorBean administratorBean) {

		return administratorDAO.update(administratorBean);
	}

	/**
	 * 修改管理員密碼
	 * 
	 * @param a_id-->管理員流水號
	 * @param a_password_new-->新密碼(原碼)
	 * @param a_salt-->塩
	 * @return AdministratorBean
	 */
	@Override
	@Transactional
	public AdministratorBean updateA_password(Integer a_id, String a_password_new, String a_salt) {

		return administratorDAO.updateA_password(a_id, getHashedPassword(a_password_new, a_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param a_id-->管理員流水號
	 * @param a_signin_ip-->登入IP
	 * @return AdministratorBean
	 */
	@Override
	@Transactional
	public AdministratorBean updateA_signin_ip(Integer a_id, String a_signin_ip) {

		return administratorDAO.updateA_signin_ip(a_id, a_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param a_id-->管理員流水號
	 * @return AdministratorBean
	 */
	@Override
	@Transactional
	public AdministratorBean updateA_signin_time(Integer a_id) {

		return administratorDAO.updateA_signin_time(a_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param a_password-->管理員密碼(原碼)
	 * @param a_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String a_password, String a_salt) {

		return getMD5(a_salt.replaceAll("-", getMD5(a_password)));
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
