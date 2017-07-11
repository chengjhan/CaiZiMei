/*
 * CaiZiMei
 * File: AdminBean.java
 * Author: 詹晟
 * Date: 2017/7/11
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
 * admin entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin")
public class AdminBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer a_id;
	private String a_username;
	private String a_password;
	private String a_salt;
	private String a_lastname;
	private String a_firstname;
	private String a_email;
	private java.util.Date a_signup_time;
	private Integer a_signin_number;
	private String a_signin_ip;
	private java.util.Date a_signin_time;
	private java.util.Date a_update_info_time;
	private java.util.Date a_update_pwd_time;
	private Integer a_status;
	private java.util.Date a_status_time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "al_AdminBean")
	private Set<AdminLogBean> a_AdminLogBean;

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public String getA_username() {
		return a_username;
	}

	public void setA_username(String a_username) {
		this.a_username = a_username;
	}

	public String getA_password() {
		return a_password;
	}

	public void setA_password(String a_password) {
		this.a_password = a_password;
	}

	public String getA_salt() {
		return a_salt;
	}

	public void setA_salt(String a_salt) {
		this.a_salt = a_salt;
	}

	public String getA_lastname() {
		return a_lastname;
	}

	public void setA_lastname(String a_lastname) {
		this.a_lastname = a_lastname;
	}

	public String getA_firstname() {
		return a_firstname;
	}

	public void setA_firstname(String a_firstname) {
		this.a_firstname = a_firstname;
	}

	public String getA_email() {
		return a_email;
	}

	public void setA_email(String a_email) {
		this.a_email = a_email;
	}

	public java.util.Date getA_signup_time() {
		return a_signup_time;
	}

	public void setA_signup_time(java.util.Date a_signup_time) {
		this.a_signup_time = a_signup_time;
	}

	public Integer getA_signin_number() {
		return a_signin_number;
	}

	public void setA_signin_number(Integer a_signin_number) {
		this.a_signin_number = a_signin_number;
	}

	public String getA_signin_ip() {
		return a_signin_ip;
	}

	public void setA_signin_ip(String a_signin_ip) {
		this.a_signin_ip = a_signin_ip;
	}

	public java.util.Date getA_signin_time() {
		return a_signin_time;
	}

	public void setA_signin_time(java.util.Date a_signin_time) {
		this.a_signin_time = a_signin_time;
	}

	public java.util.Date getA_update_info_time() {
		return a_update_info_time;
	}

	public void setA_update_info_time(java.util.Date a_update_info_time) {
		this.a_update_info_time = a_update_info_time;
	}

	public java.util.Date getA_update_pwd_time() {
		return a_update_pwd_time;
	}

	public void setA_update_pwd_time(java.util.Date a_update_pwd_time) {
		this.a_update_pwd_time = a_update_pwd_time;
	}

	public Integer getA_status() {
		return a_status;
	}

	public void setA_status(Integer a_status) {
		this.a_status = a_status;
	}

	public java.util.Date getA_status_time() {
		return a_status_time;
	}

	public void setA_status_time(java.util.Date a_status_time) {
		this.a_status_time = a_status_time;
	}

	public Set<AdminLogBean> getA_AdminLogBean() {
		return a_AdminLogBean;
	}

	public void setA_AdminLogBean(Set<AdminLogBean> a_AdminLogBean) {
		this.a_AdminLogBean = a_AdminLogBean;
	}

}
