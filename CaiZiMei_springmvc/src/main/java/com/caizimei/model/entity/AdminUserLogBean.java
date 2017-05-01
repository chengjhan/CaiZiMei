/*
 * CaiZiMei
 * File: AdminUserLogBean.java
 * Author: 詹晟
 * Date: 2017/5/1
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * admin_user_log entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin_user_log")
public class AdminUserLogBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adul_id;
	private java.util.Date adul_insert_time;
	@ManyToOne
	@JoinColumn(name = "adul_adu_id")
	private AdminUserBean adul_AdminUserBean;
	private String adul_operation;

	public Integer getAdul_id() {
		return adul_id;
	}

	public void setAdul_id(Integer adul_id) {
		this.adul_id = adul_id;
	}

	public java.util.Date getAdul_insert_time() {
		return adul_insert_time;
	}

	public void setAdul_insert_time(java.util.Date adul_insert_time) {
		this.adul_insert_time = adul_insert_time;
	}

	public AdminUserBean getAdul_AdminUserBean() {
		return adul_AdminUserBean;
	}

	public void setAdul_AdminUserBean(AdminUserBean adul_AdminUserBean) {
		this.adul_AdminUserBean = adul_AdminUserBean;
	}

	public String getAdul_operation() {
		return adul_operation;
	}

	public void setAdul_operation(String adul_operation) {
		this.adul_operation = adul_operation;
	}

}
