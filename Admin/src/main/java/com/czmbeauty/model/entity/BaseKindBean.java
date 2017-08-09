/*
 * CaiZiMei
 * File: BaseKindBean.java
 * Author: 詹晟
 * Date: 2017/8/9
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
 * base_kind entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "base_kind")
public class BaseKindBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bk_id;
	private String bk_name;
	private String bk_eng_name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ba_BaseKindBean")
	private Set<BaseBean> bk_BaseBean;

	public Integer getBk_id() {
		return bk_id;
	}

	public void setBk_id(Integer bk_id) {
		this.bk_id = bk_id;
	}

	public String getBk_name() {
		return bk_name;
	}

	public void setBk_name(String bk_name) {
		this.bk_name = bk_name;
	}

	public String getBk_eng_name() {
		return bk_eng_name;
	}

	public void setBk_eng_name(String bk_eng_name) {
		this.bk_eng_name = bk_eng_name;
	}

	public Set<BaseBean> getBk_BaseBean() {
		return bk_BaseBean;
	}

	public void setBk_BaseBean(Set<BaseBean> bk_BaseBean) {
		this.bk_BaseBean = bk_BaseBean;
	}

}
