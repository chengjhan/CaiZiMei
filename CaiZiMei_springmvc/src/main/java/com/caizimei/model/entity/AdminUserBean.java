/*
 * CaiZiMei
 * File: AdminUserBean.java
 * Author: 詹晟
 * Date: 2017/5/1
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * admin_user entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "admin_user")
public class AdminUserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adu_id;
	@ManyToOne
	@JoinColumn(name = "adu_ad_id")
	private AdminBean adu_AdminBean;
	private String adu_username;
	private String adu_password;
	private String adu_salt;
	private String adu_lastname;
	private String adu_firstname;
	private String adu_eng_name;
	private String adu_email;
	private String adu_mobilephone;
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date adu_signup_time;
	private Integer adu_signin_number;
	private String adu_signin_ip;
	private java.util.Date adu_signin_time;
	private java.util.Date adu_update_pass_time;
	private java.util.Date adu_update_info_time;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "adul_AdminUserBean")
	private Set<AdminUserLogBean> adu_AdminUserLogBean;

	public Integer getAdu_id() {
		return adu_id;
	}

	public void setAdu_id(Integer adu_id) {
		this.adu_id = adu_id;
	}

	public AdminBean getAdu_AdminBean() {
		return adu_AdminBean;
	}

	public void setAdu_AdminBean(AdminBean adu_AdminBean) {
		this.adu_AdminBean = adu_AdminBean;
	}

	public String getAdu_username() {
		return adu_username;
	}

	public void setAdu_username(String adu_username) {
		this.adu_username = adu_username;
	}

	public String getAdu_password() {
		return adu_password;
	}

	public void setAdu_password(String adu_password) {
		this.adu_password = adu_password;
	}

	public String getAdu_salt() {
		return adu_salt;
	}

	public void setAdu_salt(String adu_salt) {
		this.adu_salt = adu_salt;
	}

	public String getAdu_lastname() {
		return adu_lastname;
	}

	public void setAdu_lastname(String adu_lastname) {
		this.adu_lastname = adu_lastname;
	}

	public String getAdu_firstname() {
		return adu_firstname;
	}

	public void setAdu_firstname(String adu_firstname) {
		this.adu_firstname = adu_firstname;
	}

	public String getAdu_eng_name() {
		return adu_eng_name;
	}

	public void setAdu_eng_name(String adu_eng_name) {
		this.adu_eng_name = adu_eng_name;
	}

	public String getAdu_email() {
		return adu_email;
	}

	public void setAdu_email(String adu_email) {
		this.adu_email = adu_email;
	}

	public String getAdu_mobilephone() {
		return adu_mobilephone;
	}

	public void setAdu_mobilephone(String adu_mobilephone) {
		this.adu_mobilephone = adu_mobilephone;
	}

	public java.util.Date getAdu_signup_time() {
		return adu_signup_time;
	}

	public void setAdu_signup_time(java.util.Date adu_signup_time) {
		this.adu_signup_time = adu_signup_time;
	}

	public Integer getAdu_signin_number() {
		return adu_signin_number;
	}

	public void setAdu_signin_number(Integer adu_signin_number) {
		this.adu_signin_number = adu_signin_number;
	}

	public String getAdu_signin_ip() {
		return adu_signin_ip;
	}

	public void setAdu_signin_ip(String adu_signin_ip) {
		this.adu_signin_ip = adu_signin_ip;
	}

	public java.util.Date getAdu_signin_time() {
		return adu_signin_time;
	}

	public void setAdu_signin_time(java.util.Date adu_signin_time) {
		this.adu_signin_time = adu_signin_time;
	}

	public java.util.Date getAdu_update_pass_time() {
		return adu_update_pass_time;
	}

	public void setAdu_update_pass_time(java.util.Date adu_update_pass_time) {
		this.adu_update_pass_time = adu_update_pass_time;
	}

	public java.util.Date getAdu_update_info_time() {
		return adu_update_info_time;
	}

	public void setAdu_update_info_time(java.util.Date adu_update_info_time) {
		this.adu_update_info_time = adu_update_info_time;
	}

	public Set<AdminUserLogBean> getAdu_AdminUserLogBean() {
		return adu_AdminUserLogBean;
	}

	public void setAdu_AdminUserLogBean(Set<AdminUserLogBean> adu_AdminUserLogBean) {
		this.adu_AdminUserLogBean = adu_AdminUserLogBean;
	}

}
