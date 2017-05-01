/*
 * CaiZiMei
 * File: AdminBean.java
 * Author: 詹晟
 * Date: 2017/5/1
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

/**
 * admin entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin")
public class AdminBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ad_id;
	private String ad_name;
	private String ad_localphone;
	private java.util.Date ad_insert_time;
	private java.util.Date ad_update_time;
	private Integer ad_status;
	private java.util.Date ad_status_time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "adu_AdminBean")
	private Set<AdminUserBean> ad_AdminUserBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "m_AdminBean")
	private Set<MemberBean> ad_MemberBean;

	public Integer getAd_id() {
		return ad_id;
	}

	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_name() {
		return ad_name;
	}

	public void setAd_name(String ad_name) {
		this.ad_name = ad_name;
	}

	public String getAd_localphone() {
		return ad_localphone;
	}

	public void setAd_localphone(String ad_localphone) {
		this.ad_localphone = ad_localphone;
	}

	public java.util.Date getAd_insert_time() {
		return ad_insert_time;
	}

	public void setAd_insert_time(java.util.Date ad_insert_time) {
		this.ad_insert_time = ad_insert_time;
	}

	public java.util.Date getAd_update_time() {
		return ad_update_time;
	}

	public void setAd_update_time(java.util.Date ad_update_time) {
		this.ad_update_time = ad_update_time;
	}

	public Integer getAd_status() {
		return ad_status;
	}

	public void setAd_status(Integer ad_status) {
		this.ad_status = ad_status;
	}

	public java.util.Date getAd_status_time() {
		return ad_status_time;
	}

	public void setAd_status_time(java.util.Date ad_status_time) {
		this.ad_status_time = ad_status_time;
	}

	public Set<AdminUserBean> getAd_AdminUserBean() {
		return ad_AdminUserBean;
	}

	public void setAd_AdminUserBean(Set<AdminUserBean> ad_AdminUserBean) {
		this.ad_AdminUserBean = ad_AdminUserBean;
	}

	public Set<MemberBean> getAd_MemberBean() {
		return ad_MemberBean;
	}

	public void setAd_MemberBean(Set<MemberBean> ad_MemberBean) {
		this.ad_MemberBean = ad_MemberBean;
	}

}
