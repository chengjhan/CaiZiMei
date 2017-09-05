/*
 * CaiZiMei
 * File: ImageBean.java
 * Author: 詹晟
 * Date: 2017/9/6
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * image entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "image")
public class ImageBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer im_id;
	@ManyToOne
	@JoinColumn(name = "im_ca_id")
	private CategoryBean im_CategoryBean;
	@NotBlank
	@Size(max = 20)
	private String im_name;
	private String im_path;
	private String im_filename;
	@Size(max = 100)
	private String im_url;
	@Max(99)
	private Integer im_rank;
	private Integer im_status;
	private java.util.Date im_update_time;

	public Integer getIm_id() {
		return im_id;
	}

	public void setIm_id(Integer im_id) {
		this.im_id = im_id;
	}

	public CategoryBean getIm_CategoryBean() {
		return im_CategoryBean;
	}

	public void setIm_CategoryBean(CategoryBean im_CategoryBean) {
		this.im_CategoryBean = im_CategoryBean;
	}

	public String getIm_name() {
		return im_name;
	}

	public void setIm_name(String im_name) {
		this.im_name = im_name;
	}

	public String getIm_path() {
		return im_path;
	}

	public void setIm_path(String im_path) {
		this.im_path = im_path;
	}

	public String getIm_filename() {
		return im_filename;
	}

	public void setIm_filename(String im_filename) {
		this.im_filename = im_filename;
	}

	public String getIm_url() {
		return im_url;
	}

	public void setIm_url(String im_url) {
		this.im_url = im_url;
	}

	public Integer getIm_rank() {
		return im_rank;
	}

	public void setIm_rank(Integer im_rank) {
		this.im_rank = im_rank;
	}

	public Integer getIm_status() {
		return im_status;
	}

	public void setIm_status(Integer im_status) {
		this.im_status = im_status;
	}

	public java.util.Date getIm_update_time() {
		return im_update_time;
	}

	public void setIm_update_time(java.util.Date im_update_time) {
		this.im_update_time = im_update_time;
	}

}
