/*
 * CaiZiMei
 * File: StateBean.java
 * Author: 詹晟
 * Date: 2017/7/15
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
	private Integer s_id;
	@ManyToOne
	@JoinColumn(name = "s_co_id")
	private CountryBean s_CountryBean;
	private String s_name;
	private Integer s_rank;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ci_StateBean")
	private Set<CityBean> s_CityBean;

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public CountryBean getS_CountryBean() {
		return s_CountryBean;
	}

	public void setS_CountryBean(CountryBean s_CountryBean) {
		this.s_CountryBean = s_CountryBean;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

	public Integer getS_rank() {
		return s_rank;
	}

	public void setS_rank(Integer s_rank) {
		this.s_rank = s_rank;
	}

	public Set<CityBean> getS_CityBean() {
		return s_CityBean;
	}

	public void setS_CityBean(Set<CityBean> s_CityBean) {
		this.s_CityBean = s_CityBean;
	}

}
