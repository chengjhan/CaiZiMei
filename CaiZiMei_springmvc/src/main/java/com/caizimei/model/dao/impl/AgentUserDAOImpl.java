/*
 * CaiZiMei
 * File: AgentUserDAOImpl.java
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

import com.caizimei.model.dao.AgentUserDAO;
import com.caizimei.model.entity.AgentUserBean;

/**
 * agent_user DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "agentUserDAO")
public class AgentUserDAOImpl implements AgentUserDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部代理商使用者
	 * 
	 * @return List<AgentUserBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AgentUserBean> select() {

		return (List<AgentUserBean>) hibernateTemplate.find("from AgentUserBean order by au_id asc");
	}

	/**
	 * 代理商使用者流水號搜尋
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @return AgentUserBean
	 */
	@Override
	public AgentUserBean selectByAu_id(Integer au_id) {

		return hibernateTemplate.get(AgentUserBean.class, au_id);
	}

	/**
	 * 代理商使用者帳號搜尋
	 * 
	 * @param au_username-->代理商使用者帳號
	 * @return AgentUserBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AgentUserBean selectByAu_username(String au_username) {

		List<AgentUserBean> list = (List<AgentUserBean>) hibernateTemplate
				.findByNamedParam("from AgentUserBean where au_username=:au_username", "au_username", au_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 代理商使用者信箱搜尋
	 * 
	 * @param au_email-->代理商使用者信箱
	 * @return AgentUserBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AgentUserBean selectByAu_email(String au_email) {

		List<AgentUserBean> list = (List<AgentUserBean>) hibernateTemplate
				.findByNamedParam("from AgentUserBean where au_email=:au_email", "au_email", au_email);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 新增代理商使用者
	 * 
	 * @param agentUserBean-->AgentUserBean
	 * @return agentUserBean-->AgentUserBean
	 */
	@Override
	public AgentUserBean insert(AgentUserBean agentUserBean) {

		hibernateTemplate.save(agentUserBean);

		return agentUserBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param newAgentUserBean-->AgentUserBean
	 * @return agentUserBean-->AgentUserBean
	 */
	@Override
	public AgentUserBean update(AgentUserBean newAgentUserBean) {

		AgentUserBean agentUserBean = hibernateTemplate.get(AgentUserBean.class, newAgentUserBean.getAu_id());

		agentUserBean.setAu_lastname(newAgentUserBean.getAu_lastname());
		agentUserBean.setAu_firstname(newAgentUserBean.getAu_firstname());
		agentUserBean.setAu_eng_name(newAgentUserBean.getAu_eng_name());
		agentUserBean.setAu_email(newAgentUserBean.getAu_email());
		agentUserBean.setAu_mobilephone(newAgentUserBean.getAu_mobilephone());
		agentUserBean.setAu_update_info_time(new java.util.Date());

		return agentUserBean;
	}

	/**
	 * 修改密碼
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @param au_password_new_hashed-->新密碼(雜湊)
	 * @return agentUserBean-->AgentUserBean
	 */
	@Override
	public AgentUserBean updateAu_password(Integer au_id, String au_password_new_hashed) {

		AgentUserBean agentUserBean = hibernateTemplate.get(AgentUserBean.class, au_id);
		agentUserBean.setAu_password(au_password_new_hashed);
		agentUserBean.setAu_update_pass_time(new java.util.Date());

		return agentUserBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @return agentUserBean-->AgentUserBean
	 */
	@Override
	public AgentUserBean updateAu_signin_number(Integer au_id) {

		AgentUserBean agentUserBean = hibernateTemplate.get(AgentUserBean.class, au_id);
		Integer au_signin_number = hibernateTemplate.get(AgentUserBean.class, au_id).getAu_signin_number();
		agentUserBean.setAu_signin_number(au_signin_number + 1);

		return agentUserBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @param au_signin_ip-->登入IP
	 * @return agentUserBean-->AgentUserBean
	 */
	@Override
	public AgentUserBean updateAu_signin_ip(Integer au_id, String au_signin_ip) {

		AgentUserBean agentUserBean = hibernateTemplate.get(AgentUserBean.class, au_id);
		agentUserBean.setAu_signin_ip(au_signin_ip);

		return agentUserBean;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param au_id-->代理商使用者流水號
	 * @return agentUserBean-->AgentUserBean
	 */
	@Override
	public AgentUserBean updateAu_signin_time(Integer au_id) {

		AgentUserBean agentUserBean = hibernateTemplate.get(AgentUserBean.class, au_id);
		agentUserBean.setAu_signin_time(new java.util.Date());

		return agentUserBean;
	}

}
