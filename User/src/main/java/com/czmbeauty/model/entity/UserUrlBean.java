/*
 * CaiZiMei/User
 * File: UserUrlBean.java
 * Author: 詹晟
 * Date: 2017/11/29
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * user_url entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "user_url")
public class UserUrlBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uu_id;
	@ManyToOne
	@JoinColumn(name = "uu_cu_id")
	private CategoryUrlBean uu_CategoryUrlBean;
	private String uu_url;
	private String uu_name;

	public Integer getUu_id() {
		return uu_id;
	}

	public void setUu_id(Integer uu_id) {
		this.uu_id = uu_id;
	}

	public CategoryUrlBean getUu_CategoryUrlBean() {
		return uu_CategoryUrlBean;
	}

	public void setUu_CategoryUrlBean(CategoryUrlBean uu_CategoryUrlBean) {
		this.uu_CategoryUrlBean = uu_CategoryUrlBean;
	}

	public String getUu_url() {
		return uu_url;
	}

	public void setUu_url(String uu_url) {
		this.uu_url = uu_url;
	}

	public String getUv_name() {
		return uu_name;
	}

	public void setUv_name(String uu_name) {
		this.uu_name = uu_name;
	}

}
