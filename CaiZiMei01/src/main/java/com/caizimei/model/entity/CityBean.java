/*
 * CaiZiMei
 * File: CityBean.java
 * Author: 詹晟
 * Date: 2017/2/24
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "city")
public class CityBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ci_id;
	private String ci_name;
	@ManyToOne
	@JoinColumn(name = "ci_co_id")
	private CountryBean ci_CountryBean;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "c_CityBean")
	private Set<ClinicBean> ci_ClinicBean = new HashSet<ClinicBean>();

	public Integer getCi_id() {
		return ci_id;
	}

	public void setCi_id(Integer ci_id) {
		this.ci_id = ci_id;
	}

	public String getCi_name() {
		return ci_name;
	}

	public void setCi_name(String ci_name) {
		this.ci_name = ci_name;
	}

	public CountryBean getCi_CountryBean() {
		return ci_CountryBean;
	}

	public void setCi_CountryBean(CountryBean ci_CountryBean) {
		this.ci_CountryBean = ci_CountryBean;
	}

	public Set<ClinicBean> getCi_ClinicBean() {
		return ci_ClinicBean;
	}

	public void setCi_ClinicBean(Set<ClinicBean> ci_ClinicBean) {
		this.ci_ClinicBean = ci_ClinicBean;
	}

}
