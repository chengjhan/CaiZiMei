/*
 * CaiZiMei
 * File: FranchiseeBean.java
 * Author: 詹晟
 * Date: 2017/7/22
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

import com.google.gson.annotations.Expose;

/**
 * franchisee entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "franchisee")
public class FranchiseeBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer fr_id;
	@Expose
	private String fr_name;
	@Expose
	private String fr_eng_name;
	@Expose
	private String fr_localphone;
	@ManyToOne
	@JoinColumn(name = "fr_co_id")
	private CountryBean fr_CountryBean;
	@ManyToOne
	@JoinColumn(name = "fr_st_id")
	private StateBean fr_StateBean;
	@ManyToOne
	@JoinColumn(name = "fr_ci_id")
	@Expose
	private CityBean fr_CityBean;
	@Expose
	private String fr_address;
	@Expose
	private Double fr_latitude;
	@Expose
	private Double fr_longitude;
	@Expose
	private String fr_url;
	@Expose
	private java.util.Date fr_insert_time;
	@Expose
	private java.util.Date fr_update_time;
	@Expose
	private Integer fr_status;
	@Expose
	private java.util.Date fr_status_time;

	public Integer getFr_id() {
		return fr_id;
	}

	public void setFr_id(Integer fr_id) {
		this.fr_id = fr_id;
	}

	public String getFr_name() {
		return fr_name;
	}

	public void setFr_name(String fr_name) {
		this.fr_name = fr_name;
	}

	public String getFr_eng_name() {
		return fr_eng_name;
	}

	public void setFr_eng_name(String fr_eng_name) {
		this.fr_eng_name = fr_eng_name;
	}

	public String getFr_localphone() {
		return fr_localphone;
	}

	public void setFr_localphone(String fr_localphone) {
		this.fr_localphone = fr_localphone;
	}

	public CountryBean getFr_CountryBean() {
		return fr_CountryBean;
	}

	public void setFr_CountryBean(CountryBean fr_CountryBean) {
		this.fr_CountryBean = fr_CountryBean;
	}

	public StateBean getFr_StateBean() {
		return fr_StateBean;
	}

	public void setFr_StateBean(StateBean fr_StateBean) {
		this.fr_StateBean = fr_StateBean;
	}

	public CityBean getFr_CityBean() {
		return fr_CityBean;
	}

	public void setFr_CityBean(CityBean fr_CityBean) {
		this.fr_CityBean = fr_CityBean;
	}

	public String getFr_address() {
		return fr_address;
	}

	public void setFr_address(String fr_address) {
		this.fr_address = fr_address;
	}

	public Double getFr_latitude() {
		return fr_latitude;
	}

	public void setFr_latitude(Double fr_latitude) {
		this.fr_latitude = fr_latitude;
	}

	public Double getFr_longitude() {
		return fr_longitude;
	}

	public void setFr_longitude(Double fr_longitude) {
		this.fr_longitude = fr_longitude;
	}

	public String getFr_url() {
		return fr_url;
	}

	public void setFr_url(String fr_url) {
		this.fr_url = fr_url;
	}

	public java.util.Date getFr_insert_time() {
		return fr_insert_time;
	}

	public void setFr_insert_time(java.util.Date fr_insert_time) {
		this.fr_insert_time = fr_insert_time;
	}

	public java.util.Date getFr_update_time() {
		return fr_update_time;
	}

	public void setFr_update_time(java.util.Date fr_update_time) {
		this.fr_update_time = fr_update_time;
	}

	public Integer getFr_status() {
		return fr_status;
	}

	public void setFr_status(Integer fr_status) {
		this.fr_status = fr_status;
	}

	public java.util.Date getFr_status_time() {
		return fr_status_time;
	}

	public void setFr_status_time(java.util.Date fr_status_time) {
		this.fr_status_time = fr_status_time;
	}

}
