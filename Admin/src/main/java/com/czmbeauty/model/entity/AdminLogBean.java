/*
 * CaiZiMei
 * File: AdminLogBean.java
 * Author: 詹晟
 * Date: 2017/10/2
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
 * admin_log entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin_log")
public class AdminLogBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer al_id;
	private java.util.Date al_insert_time;
	@ManyToOne
	@JoinColumn(name = "al_ad_id")
	private AdminBean al_AdminBean;
	@ManyToOne
	@JoinColumn(name = "al_aa_id")
	private AdminActionBean al_AdminActionBean;
	private String al_ip;

	public Integer getAl_id() {
		return al_id;
	}

	public void setAl_id(Integer al_id) {
		this.al_id = al_id;
	}

	public java.util.Date getAl_insert_time() {
		return al_insert_time;
	}

	public void setAl_insert_time(java.util.Date al_insert_time) {
		this.al_insert_time = al_insert_time;
	}

	public AdminBean getAl_AdminBean() {
		return al_AdminBean;
	}

	public void setAl_AdminBean(AdminBean al_AdminBean) {
		this.al_AdminBean = al_AdminBean;
	}

	public AdminActionBean getAl_AdminActionBean() {
		return al_AdminActionBean;
	}

	public void setAl_AdminActionBean(AdminActionBean al_AdminActionBean) {
		this.al_AdminActionBean = al_AdminActionBean;
	}

	public String getAl_ip() {
		return al_ip;
	}

	public void setAl_ip(String al_ip) {
		this.al_ip = al_ip;
	}

}
