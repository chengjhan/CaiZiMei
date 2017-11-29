/*
 * CaiZiMei/User
 * File: CategoryUrlBean.java
 * Author: 詹晟
 * Date: 2017/11/29
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
 * category_url entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "category_url")
public class CategoryUrlBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cu_id;
	private String cu_name;
	private String cu_code;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "uu_CategoryUrlBean")
	private Set<UserUrlBean> cu_UserUrlBean;

	public Integer getCu_id() {
		return cu_id;
	}

	public void setCu_id(Integer cu_id) {
		this.cu_id = cu_id;
	}

	public String getCu_name() {
		return cu_name;
	}

	public void setCu_name(String cu_name) {
		this.cu_name = cu_name;
	}

	public String getCu_code() {
		return cu_code;
	}

	public void setCu_code(String cu_code) {
		this.cu_code = cu_code;
	}

	public Set<UserUrlBean> getCu_UserUrlBean() {
		return cu_UserUrlBean;
	}

	public void setCu_UserUrlBean(Set<UserUrlBean> cu_UserUrlBean) {
		this.cu_UserUrlBean = cu_UserUrlBean;
	}

}
