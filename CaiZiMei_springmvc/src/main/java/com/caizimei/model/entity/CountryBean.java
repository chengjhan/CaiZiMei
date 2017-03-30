/*
 * CaiZiMei
 * File: CountryBean.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

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
	private String co_name;
	private String co_eng_name;
	private Integer co_rank;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "ci_CountryBean")
	private Set<CityBean> co_CityBean;

	public Integer getCo_id() {
		return co_id;
	}

	public void setCo_id(Integer co_id) {
		this.co_id = co_id;
	}

	public String getCo_name() {
		return co_name;
	}

	public void setCo_name(String co_name) {
		this.co_name = co_name;
	}

	public String getCo_eng_name() {
		return co_eng_name;
	}

	public void setCo_eng_name(String co_eng_name) {
		this.co_eng_name = co_eng_name;
	}

	public Integer getCo_rank() {
		return co_rank;
	}

	public void setCo_rank(Integer co_rank) {
		this.co_rank = co_rank;
	}

	public Set<CityBean> getCo_CityBean() {
		return co_CityBean;
	}

	public void setCo_CityBean(Set<CityBean> co_CityBean) {
		this.co_CityBean = co_CityBean;
	}

}
