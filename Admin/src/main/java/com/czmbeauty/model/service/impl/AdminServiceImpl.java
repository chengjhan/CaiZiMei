/*
 * CaiZiMei
 * File: AdminServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.model.dao.AdminDao;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.service.AdminService;

/**
 * admin service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

	/**
	 * 注入 AdminDao
	 */
	@Autowired
	private AdminDao adminDao;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param adminBean-->AdminBean
	 * @return result-->AdminBean
	 */
	@Override
	@Transactional
	public AdminBean signUp(AdminBean adminBean) {

		AdminBean result = null;

		if (adminBean != null) {

			result = adminDao.insert(adminBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param ad_username-->管理員帳號
	 * @param ad_password-->管理員密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String ad_username, String ad_password) {

		AdminBean adminBean = adminDao.selectByAd_username(ad_username);

		String ad_salt = adminBean.getAd_salt();

		if (getHashedPassword(ad_password, ad_salt).equals(adminBean.getAd_password())) {

			adminDao.updateAd_signin_number(adminBean.getAd_id());

			return true;
		} else {

			return false;
		}
	}

	/**
	 * 搜尋所有管理員
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminBean> selectAll() {

		return adminDao.selectAll();
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param ad_id-->管理員流水號
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_id(Integer ad_id) {

		return adminDao.selectByAd_id(ad_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param ad_username-->管理員帳號
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_username(String ad_username) {

		return adminDao.selectByAd_username(ad_username);
	}

	/**
	 * 管理員信箱搜尋
	 * 
	 * @param ad_email-->管理員帳號
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_email(String ad_email) {

		return adminDao.selectByAd_email(ad_email);
	}

	/**
	 * 修改資料
	 * 
	 * @param adminBean-->AdminBean
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean update(AdminBean adminBean) {

		return adminDao.update(adminBean);
	}

	/**
	 * 修改密碼
	 * 
	 * @param ad_id-->管理員流水號
	 * @param ad_password_new-->新密碼(原碼)
	 * @param ad_salt-->塩
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean updateAd_password(Integer ad_id, String ad_password_new, String ad_salt) {

		return adminDao.updateAd_password(ad_id, getHashedPassword(ad_password_new, ad_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param ad_id-->管理員流水號
	 * @param ad_signin_ip-->登入IP
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean updateAd_signin_ip(Integer ad_id, String ad_signin_ip) {

		return adminDao.updateAd_signin_ip(ad_id, ad_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param ad_id-->管理員流水號
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean updateAd_signin_time(Integer ad_id) {

		return adminDao.updateAd_signin_time(ad_id);
	}

	/**
	 * 切換狀態
	 * 
	 * @param ad_id-->管理員流水號
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean updateAd_status(Integer ad_id) {

		return adminDao.updateAd_status(ad_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param ad_password-->管理員密碼(原碼)
	 * @param ad_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String ad_password, String ad_salt) {

		return getMD5(ad_salt.replaceAll("-", getMD5(ad_password)));
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
