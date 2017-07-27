/*
 * CaiZiMei/User
 * File: SliderMainBean.java
 * Author: 詹晟
 * Date: 2017/7/27
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * slider_main entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "slider_main")
public class SliderMainBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Integer sm_id;
	@Expose
	private String sm_name;
	private String sm_path;
	@Expose
	private String sm_filename;
	@Expose
	private String sm_url;
	@Expose
	private Integer sm_rank;
	private Integer sm_status;
	private java.util.Date sm_update_time;

	public Integer getSm_id() {
		return sm_id;
	}

	public void setSm_id(Integer sm_id) {
		this.sm_id = sm_id;
	}

	public String getSm_name() {
		return sm_name;
	}

	public void setSm_name(String sm_name) {
		this.sm_name = sm_name;
	}

	public String getSm_path() {
		return sm_path;
	}

	public void setSm_path(String sm_path) {
		this.sm_path = sm_path;
	}

	public String getSm_filename() {
		return sm_filename;
	}

	public void setSm_filename(String sm_filename) {
		this.sm_filename = sm_filename;
	}

	public String getSm_url() {
		return sm_url;
	}

	public void setSm_url(String sm_url) {
		this.sm_url = sm_url;
	}

	public Integer getSm_rank() {
		return sm_rank;
	}

	public void setSm_rank(Integer sm_rank) {
		this.sm_rank = sm_rank;
	}

	public Integer getSm_status() {
		return sm_status;
	}

	public void setSm_status(Integer sm_status) {
		this.sm_status = sm_status;
	}

	public java.util.Date getSm_update_time() {
		return sm_update_time;
	}

	public void setSm_update_time(java.util.Date sm_update_time) {
		this.sm_update_time = sm_update_time;
	}

}
