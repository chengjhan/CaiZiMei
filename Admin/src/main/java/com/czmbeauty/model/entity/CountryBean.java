/*
 * CaiZiMei
 * File: CountryBean.java
 * Author: 詹晟
 * Date: 2017/8/12
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

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
	@Size(max = 2)
	@Pattern(regexp = "^$|^[a-zA-Z]{2}$")
	private String co_iso;
	@Expose
	@NotBlank
	@Size(max = 20)
	private String co_name;
	@Expose
	@Size(max = 5)
	@Pattern(regexp = "^$|^[0-9]+$")
	private String co_phonecode;
	@Expose
	@Max(99)
	private Integer co_rank;
	private Integer co_status;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "st_CountryBean")
	private Set<StateBean> co_StateBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ci_CountryBean")
	private Set<CityBean> co_CityBean;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ba_CountryBean")
	private Set<BaseBean> co_BaseBean;

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

	public Set<BaseBean> getCo_BaseBean() {
		return co_BaseBean;
	}

	public void setCo_BaseBean(Set<BaseBean> co_BaseBean) {
		this.co_BaseBean = co_BaseBean;
	}

	public Integer getCo_status() {
		return co_status;
	}

	public void setCo_status(Integer co_status) {
		this.co_status = co_status;
	}

}
