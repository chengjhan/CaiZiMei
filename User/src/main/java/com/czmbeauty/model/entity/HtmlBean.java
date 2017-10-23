/*
 * CaiZiMei/User
 * File: HtmlBean.java
 * Author: 詹晟
 * Date: 2017/10/24
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
 * html entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "html")
public class HtmlBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ht_id;
	@ManyToOne
	@JoinColumn(name = "ht_ca_id")
	private CategoryBean ht_CategoryBean;
	private String ht_name;
	private String ht_tag;
	private Integer ht_rank;
	private Integer ht_status;
	private java.util.Date ht_update_time;

	public Integer getHt_id() {
		return ht_id;
	}

	public void setHt_id(Integer ht_id) {
		this.ht_id = ht_id;
	}

	public CategoryBean getHt_CategoryBean() {
		return ht_CategoryBean;
	}

	public void setHt_CategoryBean(CategoryBean ht_CategoryBean) {
		this.ht_CategoryBean = ht_CategoryBean;
	}

	public String getHt_name() {
		return ht_name;
	}

	public void setHt_name(String ht_name) {
		this.ht_name = ht_name;
	}

	public String getHt_tag() {
		return ht_tag;
	}

	public void setHt_tag(String ht_tag) {
		this.ht_tag = ht_tag;
	}

	public Integer getHt_rank() {
		return ht_rank;
	}

	public void setHt_rank(Integer ht_rank) {
		this.ht_rank = ht_rank;
	}

	public Integer getHt_status() {
		return ht_status;
	}

	public void setHt_status(Integer ht_status) {
		this.ht_status = ht_status;
	}

	public java.util.Date getHt_update_time() {
		return ht_update_time;
	}

	public void setHt_update_time(java.util.Date ht_update_time) {
		this.ht_update_time = ht_update_time;
	}

}
