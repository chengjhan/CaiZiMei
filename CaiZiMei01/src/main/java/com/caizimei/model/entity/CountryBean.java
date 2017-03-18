/*
 * CaiZiMei
 * File: ClinicBean.java
 * Author: 詹晟
 * Date: 2017/2/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class CountryBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer co_id;
	private String co_name;
	private String co_countrycode;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "ci_CountryBean")
	private Set<CityBean> co_CityBean = new HashSet<CityBean>();

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

	public String getCo_countrycode() {
		return co_countrycode;
	}

	public void setCo_countrycode(String co_countrycode) {
		this.co_countrycode = co_countrycode;
	}

	public Set<CityBean> getCo_CityBean() {
		return co_CityBean;
	}

	public void setCo_CityBean(Set<CityBean> co_CityBean) {
		this.co_CityBean = co_CityBean;
	}

}
