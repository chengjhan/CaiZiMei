/*
 * CaiZiMei/User
 * File: CountryBean.java
 * Author: 詹晟
 * Date: 2017/7/27
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

import com.google.gson.annotations.Expose;

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
	@Expose
	private Integer co_id;
	@Expose
	private String co_iso;
	@Expose
	private String co_name;
	@Expose
	private String co_phonecode;
	@Expose
	private Integer co_rank;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "st_CountryBean")
	private Set<StateBean> co_StateBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ci_CountryBean")
	private Set<CityBean> co_CityBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cl_CountryBean")
	private Set<ClinicBean> co_ClinicBean;

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

	public Set<CityBean> getCo_CityBean() {
		return co_CityBean;
	}

	public void setCo_CityBean(Set<CityBean> co_CityBean) {
		this.co_CityBean = co_CityBean;
	}

	public Set<ClinicBean> getCo_ClinicBean() {
		return co_ClinicBean;
	}

	public void setCo_ClinicBean(Set<ClinicBean> co_ClinicBean) {
		this.co_ClinicBean = co_ClinicBean;
	}

}
