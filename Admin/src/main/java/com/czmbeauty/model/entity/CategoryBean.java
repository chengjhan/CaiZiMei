/*
 * CaiZiMei
 * File: CategoryBean.java
 * Author: 詹晟
 * Date: 2017/10/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * category entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "category")
public class CategoryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ca_id;
	private String ca_name;
	private String ca_directory;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ba_CategoryBean")
	private Set<BaseBean> ca_BaseBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "im_CategoryBean")
	private Set<ImageBean> ca_ImageBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vi_CategoryBean")
	private Set<VideoBean> ca_VideoBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ht_CategoryBean")
	private Set<HtmlBean> ca_HtmlBean;

	public Integer getCa_id() {
		return ca_id;
	}

	public void setCa_id(Integer ca_id) {
		this.ca_id = ca_id;
	}

	public String getCa_name() {
		return ca_name;
	}

	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}

	public String getCa_directory() {
		return ca_directory;
	}

	public void setCa_directory(String ca_directory) {
		this.ca_directory = ca_directory;
	}

	public Set<BaseBean> getCa_BaseBean() {
		return ca_BaseBean;
	}

	public void setCa_BaseBean(Set<BaseBean> ca_BaseBean) {
		this.ca_BaseBean = ca_BaseBean;
	}

	public Set<ImageBean> getCa_ImageBean() {
		return ca_ImageBean;
	}

	public void setCa_ImageBean(Set<ImageBean> ca_ImageBean) {
		this.ca_ImageBean = ca_ImageBean;
	}

	public Set<VideoBean> getCa_VideoBean() {
		return ca_VideoBean;
	}

	public void setCa_VideoBean(Set<VideoBean> ca_VideoBean) {
		this.ca_VideoBean = ca_VideoBean;
	}

	public Set<HtmlBean> getCa_HtmlBean() {
		return ca_HtmlBean;
	}

	public void setCa_HtmlBean(Set<HtmlBean> ca_HtmlBean) {
		this.ca_HtmlBean = ca_HtmlBean;
	}

}
