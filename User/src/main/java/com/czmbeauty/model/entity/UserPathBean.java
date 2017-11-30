/*
 * CaiZiMei/User
 * File: UserPathBean.java
 * Author: 詹晟
 * Date: 2017/11/30
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
 * user_path entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "user_path")
public class UserPathBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer up_id;
	@ManyToOne
	@JoinColumn(name = "up_cp_id")
	private CategoryPathBean up_CategoryPathBean;
	private String up_path;
	private String up_name;

	public Integer getUp_id() {
		return up_id;
	}

	public void setUp_id(Integer up_id) {
		this.up_id = up_id;
	}

	public CategoryPathBean getUp_CategoryPathBean() {
		return up_CategoryPathBean;
	}

	public void setUp_CategoryPathBean(CategoryPathBean up_CategoryPathBean) {
		this.up_CategoryPathBean = up_CategoryPathBean;
	}

	public String getUp_path() {
		return up_path;
	}

	public void setUp_path(String up_path) {
		this.up_path = up_path;
	}

	public String getUp_name() {
		return up_name;
	}

	public void setUp_name(String up_name) {
		this.up_name = up_name;
	}

}
