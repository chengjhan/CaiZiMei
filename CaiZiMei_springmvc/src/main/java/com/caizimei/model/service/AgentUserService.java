/*
 * CaiZiMei
 * File: AgentUserService.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import com.caizimei.model.entity.AgentUserBean;

/**
 * agent_user service interface
 * 
 * @author 詹晟
 */
public interface AgentUserService {

	AgentUserBean signUp(AgentUserBean agentUserBean);

	Boolean signIn(String au_username, String au_password);

	AgentUserBean selectByAu_id(Integer au_id);

	AgentUserBean selectByAu_username(String au_username);

	AgentUserBean selectByAu_email(String au_email);

	AgentUserBean update(AgentUserBean agentUserBean);

	AgentUserBean updateAu_password(Integer au_id, String au_password_new, String au_salt);

	AgentUserBean updateAu_signin_ip(Integer au_id, String au_signin_ip);

	AgentUserBean updateAu_signin_time(Integer au_id);

	String getHashedPassword(String au_password, String au_salt);

	String getMD5(String str);

	String getSalt();

	void sendEmail(String to, String from, String subject, String text);

}
