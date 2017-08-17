/*
 * CaiZiMei
 * File: AdminBean.java
 * Author: 詹晟
 * Date: 2017/8/18
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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.google.gson.annotations.Expose;

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
	@Expose
	private Integer ad_id;
	@Expose
	@Pattern(regexp = "^[a-zA-Z0-9_]{3,20}$")
	private String ad_username;
	@Pattern(regexp = "^(?=.*([a-z]|[A-Z]))(?=.*[0-9])(?=\\S+$){8,32}$")
	private String ad_password;
	private String ad_salt;
	@Size(max = 20)
	private String ad_lastname;
	@Size(max = 20)
	private String ad_firstname;
	@NotEmpty
	@Email
	private String ad_email;
	private java.util.Date ad_signup_time;
	private Integer ad_signin_number;
	private String ad_signin_ip;
	private java.util.Date ad_signin_time;
	private java.util.Date ad_update_info_time;
	private java.util.Date ad_update_pwd_time;
	@Expose
	private Integer ad_status;
	@Expose
	private java.util.Date ad_status_time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "al_AdminBean")
	private Set<AdminLogBean> ad_AdminLogBean;

	public Integer getAd_id() {
		return ad_id;
	}

	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_username() {
		return ad_username;
	}

	public void setAd_username(String ad_username) {
		this.ad_username = ad_username;
	}

	public String getAd_password() {
		return ad_password;
	}

	public void setAd_password(String ad_password) {
		this.ad_password = ad_password;
	}

	public String getAd_salt() {
		return ad_salt;
	}

	public void setAd_salt(String ad_salt) {
		this.ad_salt = ad_salt;
	}

	public String getAd_lastname() {
		return ad_lastname;
	}

	public void setAd_lastname(String ad_lastname) {
		this.ad_lastname = ad_lastname;
	}

	public String getAd_firstname() {
		return ad_firstname;
	}

	public void setAd_firstname(String ad_firstname) {
		this.ad_firstname = ad_firstname;
	}

	public String getAd_email() {
		return ad_email;
	}

	public void setAd_email(String ad_email) {
		this.ad_email = ad_email;
	}

	public java.util.Date getAd_signup_time() {
		return ad_signup_time;
	}

	public void setAd_signup_time(java.util.Date ad_signup_time) {
		this.ad_signup_time = ad_signup_time;
	}

	public Integer getAd_signin_number() {
		return ad_signin_number;
	}

	public void setAd_signin_number(Integer ad_signin_number) {
		this.ad_signin_number = ad_signin_number;
	}

	public String getAd_signin_ip() {
		return ad_signin_ip;
	}

	public void setAd_signin_ip(String ad_signin_ip) {
		this.ad_signin_ip = ad_signin_ip;
	}

	public java.util.Date getAd_signin_time() {
		return ad_signin_time;
	}

	public void setAd_signin_time(java.util.Date ad_signin_time) {
		this.ad_signin_time = ad_signin_time;
	}

	public java.util.Date getAd_update_info_time() {
		return ad_update_info_time;
	}

	public void setAd_update_info_time(java.util.Date ad_update_info_time) {
		this.ad_update_info_time = ad_update_info_time;
	}

	public java.util.Date getAd_update_pwd_time() {
		return ad_update_pwd_time;
	}

	public void setAd_update_pwd_time(java.util.Date ad_update_pwd_time) {
		this.ad_update_pwd_time = ad_update_pwd_time;
	}

	public Integer getAd_status() {
		return ad_status;
	}

	public void setAd_status(Integer ad_status) {
		this.ad_status = ad_status;
	}

	public java.util.Date getAd_status_time() {
		return ad_status_time;
	}

	public void setAd_status_time(java.util.Date ad_status_time) {
		this.ad_status_time = ad_status_time;
	}

	public Set<AdminLogBean> getAd_AdminLogBean() {
		return ad_AdminLogBean;
	}

	public void setAd_AdminLogBean(Set<AdminLogBean> ad_AdminLogBean) {
		this.ad_AdminLogBean = ad_AdminLogBean;
	}

}
