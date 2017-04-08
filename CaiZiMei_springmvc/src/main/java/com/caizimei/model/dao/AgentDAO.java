/*
 * CaiZiMei
 * File: AgentDAO.java
 * Author: 詹晟
 * Date: 2017/4/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AgentBean;

/**
 * agent DAO interface
 * 
 * @author 詹晟
 */
public interface AgentDAO {

	List<AgentBean> select();

	AgentBean insert(AgentBean agentBean);

}
