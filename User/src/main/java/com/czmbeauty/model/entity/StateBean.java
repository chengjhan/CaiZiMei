/*
 * CaiZiMei
 * File: StateBean.java
 * Author: 詹晟
 * Date: 2017/7/17
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * state entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "state")
public class StateBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer st_id;
	@ManyToOne
	@JoinColumn(name = "st_co_id")
	private CountryBean st_CountryBean;
	private String st_name;
	private Integer st_rank;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ci_StateBean")
	private Set<CityBean> st_CityBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cl_StateBean")
	private Set<ClinicBean> st_ClinicBean;

	public Integer getSt_id() {
		return st_id;
	}

	public void setSt_id(Integer st_id) {
		this.st_id = st_id;
	}

	public CountryBean getSt_CountryBean() {
		return st_CountryBean;
	}

	public void setSt_CountryBean(CountryBean st_CountryBean) {
		this.st_CountryBean = st_CountryBean;
	}

	public String getSt_name() {
		return st_name;
	}

	public void setSt_name(String st_name) {
		this.st_name = st_name;
	}

	public Integer getSt_rank() {
		return st_rank;
	}

	public void setSt_rank(Integer st_rank) {
		this.st_rank = st_rank;
	}

	public Set<CityBean> getSt_CityBean() {
		return st_CityBean;
	}

	public void setSt_CityBean(Set<CityBean> st_CityBean) {
		this.st_CityBean = st_CityBean;
	}

	public Set<ClinicBean> getSt_ClinicBean() {
		return st_ClinicBean;
	}

	public void setSt_ClinicBean(Set<ClinicBean> st_ClinicBean) {
		this.st_ClinicBean = st_ClinicBean;
	}

}
