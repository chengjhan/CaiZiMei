/*
 * CaiZiMei
 * File: AgentUserServiceImpl.java
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

import com.caizimei.model.dao.AgentUserDAO;
import com.caizimei.model.entity.AgentUserBean;
import com.caizimei.model.service.AgentUserService;

/**
 * agent_user service implement
 * 
 * @author 詹晟
 */
@Service(value = "agentUserService")
public class AgentUserServiceImpl implements AgentUserService {

	/**
	 * 注入 AgentUserDAO
	 */
	@Autowired
	private AgentUserDAO agentUserDAO;

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 註冊
	 * 
	 * @param agentUserBean-->AgentUserBean
	 * @return result-->AgentUserBean
	 */
	@Override
	@Transactional
	public AgentUserBean signUp(AgentUserBean agentUserBean) {

		AgentUserBean result = null;

		if (agentUserBean != null) {

			result = agentUserDAO.insert(agentUserBean);
		}

		return result;
	}

	/**
	 * 登入
	 * 
	 * @param au_username-->代理商使用者信箱
	 * @param au_password-->代理商使用者密碼(原碼)
	 * @return true-->登入成功
	 * @return false-->登入失敗
	 */
	@Override
	@Transactional
	public Boolean signIn(String au_username, String au_password) {

		AgentUserBean agentUserBean = agentUserDAO.selectByAu_username(au_username);

		String au_salt = agentUserBean.getAu_salt();

		if (getHashedPassword(au_password, au_salt).equals(agentUserBean.getAu_password())) {

			agentUserDAO.updateAu_signin_number(agentUserBean.getAu_id());

			return true;
		} else {
			return false;
		}
	}

	/**
	 * 代理商使用者流水號搜尋
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @return AgentUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AgentUserBean selectByAu_id(Integer au_id) {

		return agentUserDAO.selectByAu_id(au_id);
	}

	/**
	 * 代理商使用者帳號搜尋
	 * 
	 * @param au_username-->代理商使用者帳號
	 * @return AgentUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AgentUserBean selectByAu_username(String au_username) {

		return agentUserDAO.selectByAu_username(au_username);
	}

	/**
	 * 代理商使用者信箱搜尋
	 * 
	 * @param au_email-->代理商使用者信箱
	 * @return AgentUserBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AgentUserBean selectByAu_email(String au_email) {

		return agentUserDAO.selectByAu_email(au_email);
	}

	/**
	 * 修改資料
	 * 
	 * @param agentUserBean-->AgentUserBean
	 * @return AgentUserBean
	 */
	@Override
	@Transactional
	public AgentUserBean update(AgentUserBean agentUserBean) {

		return agentUserDAO.update(agentUserBean);
	}

	/**
	 * 修改密碼
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @param au_password_new-->新密碼(原碼)
	 * @param au_salt-->塩
	 * @return AgentUserBean
	 */
	@Override
	@Transactional
	public AgentUserBean updateAu_password(Integer au_id, String au_password_new, String au_salt) {

		return agentUserDAO.updateAu_password(au_id, getHashedPassword(au_password_new, au_salt));
	}

	/**
	 * 更新登入IP
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @param au_signin_ip-->登入IP
	 * @return AgentUserBean
	 */
	@Override
	@Transactional
	public AgentUserBean updateAu_signin_ip(Integer au_id, String au_signin_ip) {

		return agentUserDAO.updateAu_signin_ip(au_id, au_signin_ip);
	}

	/**
	 * 更新登入時間
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @return AgentUserBean
	 */
	@Override
	@Transactional
	public AgentUserBean updateAu_signin_time(Integer au_id) {

		return agentUserDAO.updateAu_signin_time(au_id);
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param au_password-->代理商使用者密碼(原碼)
	 * @param au_salt-->塩
	 * @return MD5雜湊密碼
	 */
	@Override
	public String getHashedPassword(String au_password, String au_salt) {

		return getMD5(au_salt.replaceAll("-", getMD5(au_password)));
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
