/*
 * CaiZiMei
 * File: AdminPathBean.java
 * Author: 詹晟
 * Date: 2017/11/30
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * admin_path entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin_path")
public class AdminPathBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ap_id;
	@ManyToOne
	@JoinColumn(name = "ap_cp_id")
	private CategoryPathBean ap_CategoryPathBean;
	private String ap_path;
	private String ap_name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "al_AdminPathBean")
	private Set<AdminLogBean> aa_AdminLogBean;

	public Integer getAp_id() {
		return ap_id;
	}

	public void setAp_id(Integer ap_id) {
		this.ap_id = ap_id;
	}

	public CategoryPathBean getAp_CategoryPathBean() {
		return ap_CategoryPathBean;
	}

	public void setAp_CategoryPathBean(CategoryPathBean ap_CategoryPathBean) {
		this.ap_CategoryPathBean = ap_CategoryPathBean;
	}

	public String getAp_path() {
		return ap_path;
	}

	public void setAp_path(String ap_path) {
		this.ap_path = ap_path;
	}

	public String getAp_name() {
		return ap_name;
	}

	public void setAp_name(String ap_name) {
		this.ap_name = ap_name;
	}

}
