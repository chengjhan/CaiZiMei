/*
 * CaiZiMei
 * File: ClinicBean.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * clinic entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "clinic")
public class ClinicBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer c_id;
	private String c_name;
	private String c_eng_name;
	private String c_localphone;
	@ManyToOne
	@JoinColumn(name = "c_r_id")
	private RegionBean c_RegionBean;
	private String c_address;
	private Double c_latitude;
	private Double c_longitude;
	private String c_url;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date c_insert_time;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date c_update_time;
	private Integer c_status;
	private java.util.Date c_status_time;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "m_ClinicBean")
	private Set<MemberBean> c_MemberBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cu_ClinicBean")
	private Set<ClinicUserBean> c_ClinicUserBean;

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_eng_name() {
		return c_eng_name;
	}

	public void setC_eng_name(String c_eng_name) {
		this.c_eng_name = c_eng_name;
	}

	public String getC_localphone() {
		return c_localphone;
	}

	public void setC_localphone(String c_localphone) {
		this.c_localphone = c_localphone;
	}

	public RegionBean getC_RegionBean() {
		return c_RegionBean;
	}

	public void setC_RegionBean(RegionBean c_RegionBean) {
		this.c_RegionBean = c_RegionBean;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public Double getC_latitude() {
		return c_latitude;
	}

	public void setC_latitude(Double c_latitude) {
		this.c_latitude = c_latitude;
	}

	public Double getC_longitude() {
		return c_longitude;
	}

	public void setC_longitude(Double c_longitude) {
		this.c_longitude = c_longitude;
	}

	public String getC_url() {
		return c_url;
	}

	public void setC_url(String c_url) {
		this.c_url = c_url;
	}

	public java.util.Date getC_insert_time() {
		return c_insert_time;
	}

	public void setC_insert_time(java.util.Date c_insert_time) {
		this.c_insert_time = c_insert_time;
	}

	public java.util.Date getC_update_time() {
		return c_update_time;
	}

	public void setC_update_time(java.util.Date c_update_time) {
		this.c_update_time = c_update_time;
	}

	public Set<MemberBean> getC_MemberBean() {
		return c_MemberBean;
	}

	public void setC_MemberBean(Set<MemberBean> c_MemberBean) {
		this.c_MemberBean = c_MemberBean;
	}

	public Integer getC_status() {
		return c_status;
	}

	public void setC_status(Integer c_status) {
		this.c_status = c_status;
	}

	public java.util.Date getC_status_time() {
		return c_status_time;
	}

	public void setC_status_time(java.util.Date c_status_time) {
		this.c_status_time = c_status_time;
	}

	public Set<ClinicUserBean> getC_ClinicUserBean() {
		return c_ClinicUserBean;
	}

	public void setC_ClinicUserBean(Set<ClinicUserBean> c_ClinicUserBean) {
		this.c_ClinicUserBean = c_ClinicUserBean;
	}

}
