/*
 * CaiZiMei
 * File: YoutubeBean.java
 * Author: 詹晟
 * Date: 2017/8/22
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
 * youtube entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "youtube")
public class YoutubeBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer yo_id;
	@ManyToOne
	@JoinColumn(name = "im_ca_id")
	private CategoryBean im_CategoryBean;
	private String yo_name;
	private String yo_tag;
	private Integer yo_rank;
	private java.util.Date yo_update_time;

	public Integer getYo_id() {
		return yo_id;
	}

	public void setYo_id(Integer yo_id) {
		this.yo_id = yo_id;
	}

	public CategoryBean getIm_CategoryBean() {
		return im_CategoryBean;
	}

	public void setIm_CategoryBean(CategoryBean im_CategoryBean) {
		this.im_CategoryBean = im_CategoryBean;
	}

	public String getYo_name() {
		return yo_name;
	}

	public void setYo_name(String yo_name) {
		this.yo_name = yo_name;
	}

	public String getYo_tag() {
		return yo_tag;
	}

	public void setYo_tag(String yo_tag) {
		this.yo_tag = yo_tag;
	}

	public Integer getYo_rank() {
		return yo_rank;
	}

	public void setYo_rank(Integer yo_rank) {
		this.yo_rank = yo_rank;
	}

	public java.util.Date getYo_update_time() {
		return yo_update_time;
	}

	public void setYo_update_time(java.util.Date yo_update_time) {
		this.yo_update_time = yo_update_time;
	}

}
