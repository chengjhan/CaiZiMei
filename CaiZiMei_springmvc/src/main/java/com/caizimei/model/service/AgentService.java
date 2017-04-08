/*
 * CaiZiMei
 * File: AgentService.java
 * Author: 詹晟
 * Date: 2017/4/8
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

	AgentBean insert(AgentBean agentBean);

}
