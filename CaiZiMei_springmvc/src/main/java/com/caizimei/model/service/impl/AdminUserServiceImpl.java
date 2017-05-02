/*
 * CaiZiMei
 * File: AdminUserServiceImpl.java
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

import com.caizimei.model.dao.AdminUserDAO;
import com.caizimei.model.entity.AdminUserBean;
import com.caizimei.model.service.AdminUserService;

/**
 * admin_user service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminUserService")
public class AdminUserServiceImpl implements AdminUserService {

	/**
	 * 注入 AdminUserDAO
	 */
	@Autowired
	private AdminUserDAO adminUserDAO;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param adminUserBean-->AdminUserBean
	 * @return result-->AdminUserBean
	 */
	@Override
	@Transactional
	public AdminUserBean signUp(AdminUserBean adminUserBean) {

		AdminUserBean result = null;

		if (adminUserBean != null) {

			result = adminUserDAO.insert(adminUserBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param adu_username-->管理員信箱
	 * @param adu_password-->管理員密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String adu_username, String adu_password) {

		AdminUserBean adminUserBean = adminUserDAO.selectByAdu_username(adu_username);

		String adu_salt = adminUserBean.getAdu_salt();

		if (getHashedPassword(adu_password, adu_salt).equals(adminUserBean.getAdu_password())) {

			adminUserDAO.updateAdu_signin_number(adminUserBean.getAdu_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param adu_id-->管理員流水號
	 * @return AdminUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminUserBean selectByAdu_id(Integer adu_id) {

		return adminUserDAO.selectByAdu_id(adu_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param adu_username-->管理員帳號
	 * @return AdminUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminUserBean selectByAdu_username(String adu_username) {

		return adminUserDAO.selectByAdu_username(adu_username);
	}

	/**
	 * 修改管理員資料
	 * 
	 * @param adminUserBean-->AdminUserBean
	 * @return AdminUserBean
	 */
	@Override
	@Transactional
	public AdminUserBean update(AdminUserBean adminUserBean) {

		return adminUserDAO.update(adminUserBean);
	}

	/**
	 * 修改管理員密碼
	 * 
	 * @param adu_id-->管理員流水號
	 * @param adu_password_new-->新密碼(原碼)
	 * @param adu_salt-->塩
	 * @return AdminUserBean
	 */
	@Override
	@Transactional
	public AdminUserBean updateAdu_password(Integer adu_id, String adu_password_new, String adu_salt) {

		return adminUserDAO.updateAdu_password(adu_id, getHashedPassword(adu_password_new, adu_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param adu_id-->管理員流水號
	 * @param adu_signin_ip-->登入IP
	 * @return AdminUserBean
	 */
	@Override
	@Transactional
	public AdminUserBean updateAdu_signin_ip(Integer adu_id, String adu_signin_ip) {

		return adminUserDAO.updateAdu_signin_ip(adu_id, adu_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param adu_id-->管理員流水號
	 * @return AdminUserBean
	 */
	@Override
	@Transactional
	public AdminUserBean updateAdu_signin_time(Integer adu_id) {

		return adminUserDAO.updateAdu_signin_time(adu_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param adu_password-->管理員密碼(原碼)
	 * @param adu_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String adu_password, String adu_salt) {

		return getMD5(adu_salt.replaceAll("-", getMD5(adu_password)));
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
