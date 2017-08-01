/*
 * CaiZiMei
 * File: CityBean.java
 * Author: 詹晟
 * Date: 2017/8/1
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.google.gson.annotations.Expose;

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
	@Expose
	private Integer ci_id;
	@ManyToOne
	@JoinColumn(name = "ci_co_id")
	private CountryBean ci_CountryBean;
	@ManyToOne
	@JoinColumn(name = "ci_st_id")
	@Expose
	private StateBean ci_StateBean;
	@Expose
	@NotBlank
	@Size(max = 20)
	private String ci_name;
	@Expose
	@Max(99)
	private Integer ci_rank;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ba_CityBean")
	private Set<BaseBean> ci_BaseBean;

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

	public Set<BaseBean> getCi_BaseBean() {
		return ci_BaseBean;
	}

	public void setCi_BaseBean(Set<BaseBean> ci_BaseBean) {
		this.ci_BaseBean = ci_BaseBean;
	}

}
