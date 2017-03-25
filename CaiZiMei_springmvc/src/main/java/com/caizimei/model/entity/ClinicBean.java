/*
 * CaiZiMei
 * File: ClinicBean.java
 * Author: 詹晟
 * Date: 2017/3/26
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

/**
 * clinic entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "clinic")
public class ClinicBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer c_id;
	private String c_name;
	private String c_eng_name;
	private String c_telephone;
	@ManyToOne
	@JoinColumn(name = "c_ci_id")
	private CityBean c_CityBean;
	private String c_address;
	private Double c_latitude;
	private Double c_longitude;
	private String c_url;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "p_ClinicBean")
	private Set<PurchaseBean> c_PurchaseBean = new HashSet<PurchaseBean>();

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_eng_name() {
		return c_eng_name;
	}

	public void setC_eng_name(String c_eng_name) {
		this.c_eng_name = c_eng_name;
	}

	public String getC_telephone() {
		return c_telephone;
	}

	public void setC_telephone(String c_telephone) {
		this.c_telephone = c_telephone;
	}

	public CityBean getC_CityBean() {
		return c_CityBean;
	}

	public void setC_CityBean(CityBean c_CityBean) {
		this.c_CityBean = c_CityBean;
	}

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public Double getC_latitude() {
		return c_latitude;
	}

	public void setC_latitude(Double c_latitude) {
		this.c_latitude = c_latitude;
	}

	public Double getC_longitude() {
		return c_longitude;
	}

	public void setC_longitude(Double c_longitude) {
		this.c_longitude = c_longitude;
	}

	public String getC_url() {
		return c_url;
	}

	public void setC_url(String c_url) {
		this.c_url = c_url;
	}

	public Set<PurchaseBean> getC_PurchaseBean() {
		return c_PurchaseBean;
	}

	public void setC_PurchaseBean(Set<PurchaseBean> c_PurchaseBean) {
		this.c_PurchaseBean = c_PurchaseBean;
	}

}
