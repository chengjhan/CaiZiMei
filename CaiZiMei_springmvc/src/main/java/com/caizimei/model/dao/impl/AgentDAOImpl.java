/*
 * CaiZiMei
 * File: AgentDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.AgentDAO;
import com.caizimei.model.entity.AgentBean;

/**
 * agent DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "agentDAO")
public class AgentDAOImpl implements AgentDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部代理商
	 * 
	 * @return List<AgentBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AgentBean> select() {

		return (List<AgentBean>) hibernateTemplate.find("from AgentBean");
	}

	/**
	 * 新增代理商
	 * 
	 * @param agentBean-->AgentBean
	 * @return agentBean-->AgentBean
	 */
	@Override
	public AgentBean insert(AgentBean agentBean) {

		hibernateTemplate.save(agentBean);

		return agentBean;
	}

}
