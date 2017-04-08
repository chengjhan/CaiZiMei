/*
 * CaiZiMei
 * File: AgentServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.AgentDAO;
import com.caizimei.model.entity.AgentBean;
import com.caizimei.model.service.AgentService;

/**
 * agent service implement
 * 
 * @author 詹晟
 */
@Service(value = "agentService")
@Transactional
public class AgentServiceImpl implements AgentService {

	/**
	 * 注入 AgentDAO
	 */
	@Autowired
	private AgentDAO agentDAO;

	/**
	 * 搜尋全部代理商
	 * 
	 * @return List<AgentBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AgentBean> select() {

		return agentDAO.select();
	}

	/**
	 * 新增代理商
	 * 
	 * @param agentBean-->AgentBean
	 * @return result-->AgentBean
	 */
	@Override
	@Transactional
	public AgentBean insert(AgentBean agentBean) {

		AgentBean result = null;

		if (agentBean != null) {

			result = agentDAO.insert(agentBean);
		}
		return result;
	}

}
