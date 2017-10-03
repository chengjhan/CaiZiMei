/*
 * CaiZiMei/User
 * File: UserViewBean.java
 * Author: 詹晟
 * Date: 2017/10/3
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
 * user_view entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "user_view")
public class UserViewBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uv_id;
	private String uv_name;
	private String uv_view_name;

	public Integer getUv_id() {
		return uv_id;
	}

	public void setUv_id(Integer uv_id) {
		this.uv_id = uv_id;
	}

	public String getUv_name() {
		return uv_name;
	}

	public void setUv_name(String uv_name) {
		this.uv_name = uv_name;
	}

	public String getUv_view_name() {
		return uv_view_name;
	}

	public void setUv_view_name(String uv_view_name) {
		this.uv_view_name = uv_view_name;
	}

}
