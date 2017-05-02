/*
 * CaiZiMei
 * File: EmployeeServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/12
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

import com.caizimei.model.dao.AgentUserDAO;
import com.caizimei.model.entity.EmployeeBean;
import com.caizimei.model.service.AgentUserService;

/**
 * employee service implement
 * 
 * @author 詹晟
 */
@Service(value = "employeeService")
public class EmployeeServiceImpl implements AgentUserService {

	/**
	 * 注入 EmployeeDAO
	 */
	@Autowired
	private AgentUserDAO employeeDAO;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param employeeBean-->EmployeeBean
	 * @return result-->EmployeeBean
	 */
	@Override
	@Transactional
	public EmployeeBean signUp(EmployeeBean employeeBean) {

		EmployeeBean result = null;

		if (employeeBean != null) {

			result = employeeDAO.insert(employeeBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param e_username-->員工信箱
	 * @param e_password-->員工密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String e_username, String e_password) {

		EmployeeBean employeeBean = employeeDAO.selectByE_username(e_username);

		String e_salt = employeeBean.getE_salt();

		if (getHashedPassword(e_password, e_salt).equals(employeeBean.getE_password())) {

			employeeDAO.updateE_signin_number(employeeBean.getE_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 員工流水號搜尋
	 * 
	 * @param e_id-->員工流水號
	 * @return EmployeeBean
	 */
	@Override
	@Transactional(readOnly = true)
	public EmployeeBean selectByE_id(Integer e_id) {

		return employeeDAO.selectByE_id(e_id);
	}

	/**
	 * 員工帳號搜尋
	 * 
	 * @param e_username-->員工帳號
	 * @return EmployeeBean
	 */
	@Override
	@Transactional(readOnly = true)
	public EmployeeBean selectByE_username(String e_username) {

		return employeeDAO.selectByE_username(e_username);
	}

	/**
	 * 修改員工資料
	 * 
	 * @param employeeBean-->EmployeeBean
	 * @return EmployeeBean
	 */
	@Override
	@Transactional
	public EmployeeBean update(EmployeeBean employeeBean) {

		return employeeDAO.update(employeeBean);
	}

	/**
	 * 修改員工密碼
	 * 
	 * @param e_id-->員工流水號
	 * @param e_password_new-->新密碼(原碼)
	 * @param e_salt-->塩
	 * @return EmployeeBean
	 */
	@Override
	@Transactional
	public EmployeeBean updateE_password(Integer e_id, String e_password_new, String e_salt) {

		return employeeDAO.updateE_password(e_id, getHashedPassword(e_password_new, e_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param e_id-->員工流水號
	 * @param e_signin_ip-->登入IP
	 * @return EmployeeBean
	 */
	@Override
	@Transactional
	public EmployeeBean updateE_signin_ip(Integer e_id, String e_signin_ip) {

		return employeeDAO.updateE_signin_ip(e_id, e_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param e_id-->員工流水號
	 * @return EmployeeBean
	 */
	@Override
	@Transactional
	public EmployeeBean updateE_signin_time(Integer e_id) {

		return employeeDAO.updateE_signin_time(e_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param e_password-->員工密碼(原碼)
	 * @param e_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String e_password, String e_salt) {

		return getMD5(e_salt.replaceAll("-", getMD5(e_password)));
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
