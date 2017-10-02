/*
 * CaiZiMei
 * File: AdminViewBean.java
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
import javax.persistence.Table;

/**
 * admin_view entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin_view")
public class AdminViewBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer av_id;
	private String av_name;
	private String av_view_name;

	public Integer getAv_id() {
		return av_id;
	}

	public void setAv_id(Integer av_id) {
		this.av_id = av_id;
	}

	public String getAv_name() {
		return av_name;
	}

	public void setAv_name(String av_name) {
		this.av_name = av_name;
	}

	public String getAv_view_name() {
		return av_view_name;
	}

	public void setAv_view_name(String av_view_name) {
		this.av_view_name = av_view_name;
	}

}
