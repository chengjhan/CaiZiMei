/*
 * CaiZiMei
 * File: AgentDAOImpl.java
 * Author: 詹晟
 * Date: 2017/5/3
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

		return (List<AgentBean>) hibernateTemplate.find("from AgentBean order by a_id asc");
	}

	/**
	 * 代理商流水號搜尋
	 * 
	 * @param a_id-->代理商流水號
	 * @return AgentBean
	 */
	@Override
	public AgentBean selectByA_id(Integer a_id) {

		return hibernateTemplate.get(AgentBean.class, a_id);
	}

	/**
	 * 搜尋可顯示的代理商
	 * 
	 * List<AgentBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AgentBean> selectByA_status() {

		return (List<AgentBean>) hibernateTemplate.find("from AgentBean where a_status=1 order by a_id asc");
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

	/**
	 * 修改資料
	 * 
	 * @param newAgentBean-->AgentBean
	 * @return agentBean-->AgentBean
	 */
	@Override
	public AgentBean update(AgentBean newAgentBean) {

		AgentBean agentBean = hibernateTemplate.get(AgentBean.class, newAgentBean.getA_id());

		agentBean.setA_name(newAgentBean.getA_name());
		agentBean.setA_localphone(newAgentBean.getA_localphone());
		agentBean.setA_update_time(new java.util.Date());

		return agentBean;
	}

	/**
	 * 切換顯示狀態
	 * 
	 * @param a_id-->代理商流水號
	 * @return agentBean-->AgentBean
	 */
	@Override
	public AgentBean updateA_status(Integer a_id) {

		AgentBean agentBean = hibernateTemplate.get(AgentBean.class, a_id);

		if (agentBean.getA_status() == 1) {

			// 不顯示
			agentBean.setA_status(0);
			agentBean.setA_status_time(new java.util.Date());

		} else {

			// 顯示
			agentBean.setA_status(1);
			agentBean.setA_status_time(new java.util.Date());

		}

		return agentBean;
	}

}
