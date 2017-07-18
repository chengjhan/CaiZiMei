/*
 * CaiZiMei
 * File: ClinicBean.java
 * Author: 詹晟
 * Date: 2017/7/18
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

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
	@Expose
	private Integer cl_id;
	@Expose
	private String cl_name;
	@Expose
	private String cl_eng_name;
	@Expose
	private String cl_localphone;
	@ManyToOne
	@JoinColumn(name = "cl_co_id")
	private CountryBean cl_CountryBean;
	@ManyToOne
	@JoinColumn(name = "cl_st_id")
	private StateBean cl_StateBean;
	@ManyToOne
	@JoinColumn(name = "cl_ci_id")
	@Expose
	private CityBean cl_CityBean;
	@Expose
	private String cl_address;
	@Expose
	private Double cl_latitude;
	@Expose
	private Double cl_longitude;
	@Expose
	private String cl_url;
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private java.util.Date cl_insert_time;
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private java.util.Date cl_update_time;
	private Integer cl_status;
	private java.util.Date cl_status_time;

	public Integer getCl_id() {
		return cl_id;
	}

	public void setCl_id(Integer cl_id) {
		this.cl_id = cl_id;
	}

	public String getCl_name() {
		return cl_name;
	}

	public void setCl_name(String cl_name) {
		this.cl_name = cl_name;
	}

	public String getCl_eng_name() {
		return cl_eng_name;
	}

	public void setCl_eng_name(String cl_eng_name) {
		this.cl_eng_name = cl_eng_name;
	}

	public String getCl_localphone() {
		return cl_localphone;
	}

	public void setCl_localphone(String cl_localphone) {
		this.cl_localphone = cl_localphone;
	}

	public CountryBean getCl_CountryBean() {
		return cl_CountryBean;
	}

	public void setCl_CountryBean(CountryBean cl_CountryBean) {
		this.cl_CountryBean = cl_CountryBean;
	}

	public StateBean getCl_StateBean() {
		return cl_StateBean;
	}

	public void setCl_StateBean(StateBean cl_StateBean) {
		this.cl_StateBean = cl_StateBean;
	}

	public CityBean getCl_CityBean() {
		return cl_CityBean;
	}

	public void setCl_CityBean(CityBean cl_CityBean) {
		this.cl_CityBean = cl_CityBean;
	}

	public String getCl_address() {
		return cl_address;
	}

	public void setCl_address(String cl_address) {
		this.cl_address = cl_address;
	}

	public Double getCl_latitude() {
		return cl_latitude;
	}

	public void setCl_latitude(Double cl_latitude) {
		this.cl_latitude = cl_latitude;
	}

	public Double getCl_longitude() {
		return cl_longitude;
	}

	public void setCl_longitude(Double cl_longitude) {
		this.cl_longitude = cl_longitude;
	}

	public String getCl_url() {
		return cl_url;
	}

	public void setCl_url(String cl_url) {
		this.cl_url = cl_url;
	}

	public java.util.Date getCl_insert_time() {
		return cl_insert_time;
	}

	public void setCl_insert_time(java.util.Date cl_insert_time) {
		this.cl_insert_time = cl_insert_time;
	}

	public java.util.Date getCl_update_time() {
		return cl_update_time;
	}

	public void setCl_update_time(java.util.Date cl_update_time) {
		this.cl_update_time = cl_update_time;
	}

	public Integer getCl_status() {
		return cl_status;
	}

	public void setCl_status(Integer cl_status) {
		this.cl_status = cl_status;
	}

	public java.util.Date getCl_status_time() {
		return cl_status_time;
	}

	public void setCl_status_time(java.util.Date cl_status_time) {
		this.cl_status_time = cl_status_time;
	}

}
