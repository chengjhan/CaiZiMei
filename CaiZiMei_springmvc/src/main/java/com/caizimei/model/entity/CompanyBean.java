/*
 * CaiZiMei
 * File: CompanyBean.java
 * Author: 詹晟
 * Date: 2017/4/13
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
 * company entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "company")
public class CompanyBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer com_id;
	private String com_name;
	private String com_localphone;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date com_insert_time;
	private Integer com_status;
	private java.util.Date com_status_time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "e_CompanyBean")
	private Set<EmployeeBean> com_EmployeeBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_CompanyBean")
	private Set<MemberBean> com_MemberBean;

	public Integer getCom_id() {
		return com_id;
	}

	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}

	public String getCom_name() {
		return com_name;
	}

	public void setCom_name(String com_name) {
		this.com_name = com_name;
	}

	public String getCom_localphone() {
		return com_localphone;
	}

	public void setCom_localphone(String com_localphone) {
		this.com_localphone = com_localphone;
	}

	public java.util.Date getCom_insert_time() {
		return com_insert_time;
	}

	public void setCom_insert_time(java.util.Date com_insert_time) {
		this.com_insert_time = com_insert_time;
	}

	public Set<EmployeeBean> getCom_EmployeeBean() {
		return com_EmployeeBean;
	}

	public void setCom_EmployeeBean(Set<EmployeeBean> com_EmployeeBean) {
		this.com_EmployeeBean = com_EmployeeBean;
	}

	public Set<MemberBean> getCom_MemberBean() {
		return com_MemberBean;
	}

	public void setCom_MemberBean(Set<MemberBean> com_MemberBean) {
		this.com_MemberBean = com_MemberBean;
	}

	public Integer getCom_status() {
		return com_status;
	}

	public void setCom_status(Integer com_status) {
		this.com_status = com_status;
	}

	public java.util.Date getCom_status_time() {
		return com_status_time;
	}

	public void setCom_status_time(java.util.Date com_status_time) {
		this.com_status_time = com_status_time;
	}

}
