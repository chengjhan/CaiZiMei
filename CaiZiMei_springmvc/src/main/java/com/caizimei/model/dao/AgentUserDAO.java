/*
 * CaiZiMei
 * File: AgentUserDAO.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AgentUserBean;

/**
 * agent_user DAO interface
 *
 * @author 詹晟
 */
public interface AgentUserDAO {

	List<AgentUserBean> select();

	AgentUserBean selectByAu_id(Integer au_id);

	AgentUserBean selectByAu_username(String au_username);

	AgentUserBean insert(AgentUserBean agentUserBean);

	AgentUserBean update(AgentUserBean agentUserBean);

	AgentUserBean updateAu_password(Integer au_id, String au_password_new_hashed);

	AgentUserBean updateAu_signin_number(Integer au_id);

	AgentUserBean updateAu_signin_ip(Integer au_id, String au_signin_ip);

	AgentUserBean updateAu_signin_time(Integer au_id);

}
