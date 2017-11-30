/*
 * CaiZiMei/User
 * File: CategoryPathBean.java
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * category_path entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "category_path")
public class CategoryPathBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cp_id;
	private String cp_name;
	private String cp_extension;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "up_CategoryPathBean")
	private Set<UserPathBean> cp_UserPathBean;

	public Integer getCp_id() {
		return cp_id;
	}

	public void setCp_id(Integer cp_id) {
		this.cp_id = cp_id;
	}

	public String getCp_name() {
		return cp_name;
	}

	public void setCp_name(String cp_name) {
		this.cp_name = cp_name;
	}

	public String getCp_extension() {
		return cp_extension;
	}

	public void setCp_extension(String cp_extension) {
		this.cp_extension = cp_extension;
	}

	public Set<UserPathBean> getCp_UserPathBean() {
		return cp_UserPathBean;
	}

	public void setCp_UserPathBean(Set<UserPathBean> cp_UserPathBean) {
		this.cp_UserPathBean = cp_UserPathBean;
	}

}
