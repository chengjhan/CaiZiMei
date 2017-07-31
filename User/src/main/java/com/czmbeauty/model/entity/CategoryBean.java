/*
 * CaiZiMei/User
 * File: CategoryBean.java
 * Author: 詹晟
 * Date: 2017/7/31
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
 * category entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "category")
public class CategoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ca_id;
	private String ca_name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ba_CategoryBean")
	private Set<BaseBean> ca_BaseBean;

	public Integer getCa_id() {
		return ca_id;
	}

	public void setCa_id(Integer ca_id) {
		this.ca_id = ca_id;
	}

	public String getCa_name() {
		return ca_name;
	}

	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}

	public Set<BaseBean> getCa_BaseBean() {
		return ca_BaseBean;
	}

	public void setCa_BaseBean(Set<BaseBean> ca_BaseBean) {
		this.ca_BaseBean = ca_BaseBean;
	}

}
