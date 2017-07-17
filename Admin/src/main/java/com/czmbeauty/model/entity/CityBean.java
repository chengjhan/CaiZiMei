/*
 * CaiZiMei
 * File: CityBean.java
 * Author: 詹晟
 * Date: 2017/7/17
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

/**
 * city entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "city")
public class CityBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ci_id;
	@ManyToOne
	@JoinColumn(name = "ci_co_id")
	private CountryBean ci_CountryBean;
	@ManyToOne
	@JoinColumn(name = "ci_st_id")
	private StateBean ci_StateBean;
	private String ci_name;
	private Integer ci_rank;

	public Integer getCi_id() {
		return ci_id;
	}

	public void setCi_id(Integer ci_id) {
		this.ci_id = ci_id;
	}

	public CountryBean getCi_CountryBean() {
		return ci_CountryBean;
	}

	public void setCi_CountryBean(CountryBean ci_CountryBean) {
		this.ci_CountryBean = ci_CountryBean;
	}

	public StateBean getCi_StateBean() {
		return ci_StateBean;
	}

	public void setCi_StateBean(StateBean ci_StateBean) {
		this.ci_StateBean = ci_StateBean;
	}

	public String getCi_name() {
		return ci_name;
	}

	public void setCi_name(String ci_name) {
		this.ci_name = ci_name;
	}

	public Integer getCi_rank() {
		return ci_rank;
	}

	public void setCi_rank(Integer ci_rank) {
		this.ci_rank = ci_rank;
	}

}
