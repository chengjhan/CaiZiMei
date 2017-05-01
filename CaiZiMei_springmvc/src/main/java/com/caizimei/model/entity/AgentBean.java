/*
 * CaiZiMei
 * File: AgentBean.java
 * Author: 詹晟
 * Date: 2017/5/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * agent entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "agent")
public class AgentBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer a_id;
	private String a_name;
	private String a_localphone;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date a_insert_time;
	private java.util.Date a_update_time;
	private Integer a_status;
	private java.util.Date a_status_time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "au_AgentBean")
	private Set<AgentUserBean> a_AgentUserBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_AgentBean")
	private Set<MemberBean> a_MemberBean;

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getA_localphone() {
		return a_localphone;
	}

	public void setA_localphone(String a_localphone) {
		this.a_localphone = a_localphone;
	}

	public java.util.Date getA_insert_time() {
		return a_insert_time;
	}

	public void setA_insert_time(java.util.Date a_insert_time) {
		this.a_insert_time = a_insert_time;
	}

	public java.util.Date getA_update_time() {
		return a_update_time;
	}

	public void setA_update_time(java.util.Date a_update_time) {
		this.a_update_time = a_update_time;
	}

	public Integer getA_status() {
		return a_status;
	}

	public void setA_status(Integer a_status) {
		this.a_status = a_status;
	}

	public java.util.Date getA_status_time() {
		return a_status_time;
	}

	public void setA_status_time(java.util.Date a_status_time) {
		this.a_status_time = a_status_time;
	}

	public Set<AgentUserBean> getA_AgentUserBean() {
		return a_AgentUserBean;
	}

	public void setA_AgentUserBean(Set<AgentUserBean> a_AgentUserBean) {
		this.a_AgentUserBean = a_AgentUserBean;
	}

	public Set<MemberBean> getA_MemberBean() {
		return a_MemberBean;
	}

	public void setA_MemberBean(Set<MemberBean> a_MemberBean) {
		this.a_MemberBean = a_MemberBean;
	}

}
