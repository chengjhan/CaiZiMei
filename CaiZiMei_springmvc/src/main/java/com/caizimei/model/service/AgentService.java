/*
 * CaiZiMei
 * File: AgentService.java
 * Author: 詹晟
 * Date: 2017/5/3
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.AgentBean;

/**
 * agent service interface
 * 
 * @author 詹晟
 */
public interface AgentService {

	List<AgentBean> select();

	AgentBean selectByA_id(Integer a_id);

	List<AgentBean> selectByA_status();

	AgentBean insert(AgentBean agentBean);

	AgentBean update(AgentBean newAgentBean);

	AgentBean updateA_status(Integer a_id);

}
