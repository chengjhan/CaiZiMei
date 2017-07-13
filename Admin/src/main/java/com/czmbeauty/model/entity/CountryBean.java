/*
 * CaiZiMei
 * File: CountryBean.java
 * Author: 詹晟
 * Date: 2017/7/13
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
 * country entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "country")
public class CountryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer co_id;
	private String co_iso;
	private String co_name;
	private String co_phonecode;
	private Integer co_rank;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "s_CountryBean")
	private Set<StateBean> co_StateBean;

	public Integer getCo_id() {
		return co_id;
	}

	public void setCo_id(Integer co_id) {
		this.co_id = co_id;
	}

	public String getCo_iso() {
		return co_iso;
	}

	public void setCo_iso(String co_iso) {
		this.co_iso = co_iso;
	}

	public String getCo_name() {
		return co_name;
	}

	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}

	public String getCo_phonecode() {
		return co_phonecode;
	}

	public void setCo_phonecode(String co_phonecode) {
		this.co_phonecode = co_phonecode;
	}

	public Integer getCo_rank() {
		return co_rank;
	}

	public void setCo_rank(Integer co_rank) {
		this.co_rank = co_rank;
	}

	public Set<StateBean> getCo_StateBean() {
		return co_StateBean;
	}

	public void setCo_StateBean(Set<StateBean> co_StateBean) {
		this.co_StateBean = co_StateBean;
	}

}
