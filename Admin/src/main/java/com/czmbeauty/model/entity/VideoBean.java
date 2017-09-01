/*
 * CaiZiMei
 * File: VideoBean.java
 * Author: 詹晟
 * Date: 2017/9/1
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
 * video entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "video")
public class VideoBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vi_id;
	@ManyToOne
	@JoinColumn(name = "vi_ca_id")
	private CategoryBean vi_CategoryBean;
	@Size(max = 50)
	private String vi_name;
	@NotBlank
	private String vi_tag;
	@Max(99)
	private Integer vi_rank;
	private Integer vi_status;
	private java.util.Date vi_update_time;

	public Integer getVi_id() {
		return vi_id;
	}

	public void setVi_id(Integer vi_id) {
		this.vi_id = vi_id;
	}

	public CategoryBean getVi_CategoryBean() {
		return vi_CategoryBean;
	}

	public void setVi_CategoryBean(CategoryBean vi_CategoryBean) {
		this.vi_CategoryBean = vi_CategoryBean;
	}

	public String getVi_name() {
		return vi_name;
	}

	public void setVi_name(String vi_name) {
		this.vi_name = vi_name;
	}

	public String getVi_tag() {
		return vi_tag;
	}

	public void setVi_tag(String vi_tag) {
		this.vi_tag = vi_tag;
	}

	public Integer getVi_rank() {
		return vi_rank;
	}

	public void setVi_rank(Integer vi_rank) {
		this.vi_rank = vi_rank;
	}

	public Integer getVi_status() {
		return vi_status;
	}

	public void setVi_status(Integer vi_status) {
		this.vi_status = vi_status;
	}

	public java.util.Date getVi_update_time() {
		return vi_update_time;
	}

	public void setVi_update_time(java.util.Date vi_update_time) {
		this.vi_update_time = vi_update_time;
	}

}
