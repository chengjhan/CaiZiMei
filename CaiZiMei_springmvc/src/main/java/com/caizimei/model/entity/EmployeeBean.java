/*
 * CaiZiMei
 * File: EmployeeBean.java
 * Author: 詹晟
 * Date: 2017/4/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * employee entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "employee")
public class EmployeeBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer e_id;
	private String e_username;
	private String e_password;
	private String e_salt;
	private String e_lastname;
	private String e_firstname;
	private String e_eng_name;
	private String e_mobilephone;
	private java.util.Date e_signup_time;
	@ManyToOne
	@JoinColumn(name = "e_com_id")
	private CompanyBean e_CompanyBean;

	public Integer getE_id() {
		return e_id;
	}

	public void setE_id(Integer e_id) {
		this.e_id = e_id;
	}

	public String getE_username() {
		return e_username;
	}

	public void setE_username(String e_username) {
		this.e_username = e_username;
	}

	public String getE_password() {
		return e_password;
	}

	public void setE_password(String e_password) {
		this.e_password = e_password;
	}

	public String getE_salt() {
		return e_salt;
	}

	public void setE_salt(String e_salt) {
		this.e_salt = e_salt;
	}

	public String getE_lastname() {
		return e_lastname;
	}

	public void setE_lastname(String e_lastname) {
		this.e_lastname = e_lastname;
	}

	public String getE_firstname() {
		return e_firstname;
	}

	public void setE_firstname(String e_firstname) {
		this.e_firstname = e_firstname;
	}

	public String getE_eng_name() {
		return e_eng_name;
	}

	public void setE_eng_name(String e_eng_name) {
		this.e_eng_name = e_eng_name;
	}

	public String getE_mobilephone() {
		return e_mobilephone;
	}

	public void setE_mobilephone(String e_mobilephone) {
		this.e_mobilephone = e_mobilephone;
	}

	public java.util.Date getE_signup_time() {
		return e_signup_time;
	}

	public void setE_signup_time(java.util.Date e_signup_time) {
		this.e_signup_time = e_signup_time;
	}

	public CompanyBean getE_CompanyBean() {
		return e_CompanyBean;
	}

	public void setE_CompanyBean(CompanyBean e_CompanyBean) {
		this.e_CompanyBean = e_CompanyBean;
	}

}