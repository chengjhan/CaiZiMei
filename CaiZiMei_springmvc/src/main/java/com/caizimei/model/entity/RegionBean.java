/*
 * CaiZiMei
 * File: RegionBean.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * region entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "region")
public class RegionBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer r_id;
	@ManyToOne
	@JoinColumn(name = "r_ci_id")
	private CityBean r_CityBean;
	private String r_name;
	private String r_zipcode;
	private Integer r_rank;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "c_RegionBean")
	private Set<ClinicBean> r_ClinicBean;

	public Integer getR_id() {
		return r_id;
	}

	public void setR_id(Integer r_id) {
		this.r_id = r_id;
	}

	public CityBean getR_CityBean() {
		return r_CityBean;
	}

	public void setR_CityBean(CityBean r_CityBean) {
		this.r_CityBean = r_CityBean;
	}

	public String getR_name() {
		return r_name;
	}

	public void setR_name(String r_name) {
		this.r_name = r_name;
	}

	public String getR_zipcode() {
		return r_zipcode;
	}

	public void setR_zipcode(String r_zipcode) {
		this.r_zipcode = r_zipcode;
	}

	public Integer getR_rank() {
		return r_rank;
	}

	public void setR_rank(Integer r_rank) {
		this.r_rank = r_rank;
	}

	public Set<ClinicBean> getR_ClinicBean() {
		return r_ClinicBean;
	}

	public void setR_ClinicBean(Set<ClinicBean> r_ClinicBean) {
		this.r_ClinicBean = r_ClinicBean;
	}

}
