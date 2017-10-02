/*
 * CaiZiMei
 * File: AdminActionBean.java
 * Author: 詹晟
 * Date: 2017/10/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * admin_action entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin_action")
public class AdminActionBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aa_id;
	private String aa_name;
	private String aa_action_name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "al_AdminActionBean")
	private Set<AdminLogBean> aa_AdminLogBean;

	public Integer getAa_id() {
		return aa_id;
	}

	public void setAa_id(Integer aa_id) {
		this.aa_id = aa_id;
	}

	public String getAa_name() {
		return aa_name;
	}

	public void setAa_name(String aa_name) {
		this.aa_name = aa_name;
	}

	public String getAa_action_name() {
		return aa_action_name;
	}

	public void setAa_action_name(String aa_action_name) {
		this.aa_action_name = aa_action_name;
	}

	public Set<AdminLogBean> getAa_AdminLogBean() {
		return aa_AdminLogBean;
	}

	public void setAa_AdminLogBean(Set<AdminLogBean> aa_AdminLogBean) {
		this.aa_AdminLogBean = aa_AdminLogBean;
	}

}
