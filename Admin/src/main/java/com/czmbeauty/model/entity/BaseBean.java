/*
 * CaiZiMei
 * File: BaseBean.java
 * Author: 詹晟
 * Date: 2017/7/30
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
 * base entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "base")
public class BaseBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer ba_id;
	@ManyToOne
	@JoinColumn(name = "ba_ca_id")
	private CategoryBean ba_CategoryBean;
	@Expose
	private String ba_name;
	@Expose
	private String ba_eng_name;
	@Expose
	private String ba_localphone;
	@ManyToOne
	@JoinColumn(name = "ba_co_id")
	private CountryBean ba_CountryBean;
	@ManyToOne
	@JoinColumn(name = "ba_st_id")
	private StateBean ba_StateBean;
	@ManyToOne
	@JoinColumn(name = "ba_ci_id")
	@Expose
	private CityBean ba_CityBean;
	@Expose
	private String ba_address;
	@Expose
	private Double ba_latitude;
	@Expose
	private Double ba_longitude;
	@Expose
	private String ba_url;
	@Expose
	private java.util.Date ba_insert_time;
	@Expose
	private java.util.Date ba_update_time;
	@Expose
	private Integer ba_status;
	@Expose
	private java.util.Date ba_status_time;

	public Integer getBa_id() {
		return ba_id;
	}

	public void setBa_id(Integer ba_id) {
		this.ba_id = ba_id;
	}

	public CategoryBean getBa_CategoryBean() {
		return ba_CategoryBean;
	}

	public void setBa_CategoryBean(CategoryBean ba_CategoryBean) {
		this.ba_CategoryBean = ba_CategoryBean;
	}

	public String getBa_name() {
		return ba_name;
	}

	public void setBa_name(String ba_name) {
		this.ba_name = ba_name;
	}

	public String getBa_eng_name() {
		return ba_eng_name;
	}

	public void setBa_eng_name(String ba_eng_name) {
		this.ba_eng_name = ba_eng_name;
	}

	public String getBa_localphone() {
		return ba_localphone;
	}

	public void setBa_localphone(String ba_localphone) {
		this.ba_localphone = ba_localphone;
	}

	public CountryBean getBa_CountryBean() {
		return ba_CountryBean;
	}

	public void setBa_CountryBean(CountryBean ba_CountryBean) {
		this.ba_CountryBean = ba_CountryBean;
	}

	public StateBean getBa_StateBean() {
		return ba_StateBean;
	}

	public void setBa_StateBean(StateBean ba_StateBean) {
		this.ba_StateBean = ba_StateBean;
	}

	public CityBean getBa_CityBean() {
		return ba_CityBean;
	}

	public void setBa_CityBean(CityBean ba_CityBean) {
		this.ba_CityBean = ba_CityBean;
	}

	public String getBa_address() {
		return ba_address;
	}

	public void setBa_address(String ba_address) {
		this.ba_address = ba_address;
	}

	public Double getBa_latitude() {
		return ba_latitude;
	}

	public void setBa_latitude(Double ba_latitude) {
		this.ba_latitude = ba_latitude;
	}

	public Double getBa_longitude() {
		return ba_longitude;
	}

	public void setBa_longitude(Double ba_longitude) {
		this.ba_longitude = ba_longitude;
	}

	public String getBa_url() {
		return ba_url;
	}

	public void setBa_url(String ba_url) {
		this.ba_url = ba_url;
	}

	public java.util.Date getBa_insert_time() {
		return ba_insert_time;
	}

	public void setBa_insert_time(java.util.Date ba_insert_time) {
		this.ba_insert_time = ba_insert_time;
	}

	public java.util.Date getBa_update_time() {
		return ba_update_time;
	}

	public void setBa_update_time(java.util.Date ba_update_time) {
		this.ba_update_time = ba_update_time;
	}

	public Integer getBa_status() {
		return ba_status;
	}

	public void setBa_status(Integer ba_status) {
		this.ba_status = ba_status;
	}

	public java.util.Date getBa_status_time() {
		return ba_status_time;
	}

	public void setBa_status_time(java.util.Date ba_status_time) {
		this.ba_status_time = ba_status_time;
	}

}
