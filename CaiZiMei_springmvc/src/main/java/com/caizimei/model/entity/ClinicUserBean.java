/*
 * CaiZiMei
 * File: ClinicUserBean.java
 * Author: 詹晟
 * Date: 2017/5/1
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
 * clinic_user entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "clinic_user")
public class ClinicUserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cu_id;
	@ManyToOne
	@JoinColumn(name = "cu_c_id")
	private ClinicBean cu_ClinicBean;
	private String cu_username;
	private String cu_password;
	private String cu_salt;
	private String cu_lastname;
	private String cu_firstname;
	private String cu_eng_name;
	private String cu_email;
	private String cu_mobilephone;
	private java.util.Date cu_signup_time;
	private Integer cu_signin_number;
	private String cu_signin_ip;
	private java.util.Date cu_signin_time;
	private java.util.Date cu_update_pass_time;
	private java.util.Date cu_update_info_time;

	public Integer getCu_id() {
		return cu_id;
	}

	public void setCu_id(Integer cu_id) {
		this.cu_id = cu_id;
	}

	public ClinicBean getCu_ClinicBean() {
		return cu_ClinicBean;
	}

	public void setCu_ClinicBean(ClinicBean cu_ClinicBean) {
		this.cu_ClinicBean = cu_ClinicBean;
	}

	public String getCu_username() {
		return cu_username;
	}

	public void setCu_username(String cu_username) {
		this.cu_username = cu_username;
	}

	public String getCu_password() {
		return cu_password;
	}

	public void setCu_password(String cu_password) {
		this.cu_password = cu_password;
	}

	public String getCu_salt() {
		return cu_salt;
	}

	public void setCu_salt(String cu_salt) {
		this.cu_salt = cu_salt;
	}

	public String getCu_lastname() {
		return cu_lastname;
	}

	public void setCu_lastname(String cu_lastname) {
		this.cu_lastname = cu_lastname;
	}

	public String getCu_firstname() {
		return cu_firstname;
	}

	public void setCu_firstname(String cu_firstname) {
		this.cu_firstname = cu_firstname;
	}

	public String getCu_eng_name() {
		return cu_eng_name;
	}

	public void setCu_eng_name(String cu_eng_name) {
		this.cu_eng_name = cu_eng_name;
	}

	public String getCu_email() {
		return cu_email;
	}

	public void setCu_email(String cu_email) {
		this.cu_email = cu_email;
	}

	public String getCu_mobilephone() {
		return cu_mobilephone;
	}

	public void setCu_mobilephone(String cu_mobilephone) {
		this.cu_mobilephone = cu_mobilephone;
	}

	public java.util.Date getCu_signup_time() {
		return cu_signup_time;
	}

	public void setCu_signup_time(java.util.Date cu_signup_time) {
		this.cu_signup_time = cu_signup_time;
	}

	public Integer getCu_signin_number() {
		return cu_signin_number;
	}

	public void setCu_signin_number(Integer cu_signin_number) {
		this.cu_signin_number = cu_signin_number;
	}

	public String getCu_signin_ip() {
		return cu_signin_ip;
	}

	public void setCu_signin_ip(String cu_signin_ip) {
		this.cu_signin_ip = cu_signin_ip;
	}

	public java.util.Date getCu_signin_time() {
		return cu_signin_time;
	}

	public void setCu_signin_time(java.util.Date cu_signin_time) {
		this.cu_signin_time = cu_signin_time;
	}

	public java.util.Date getCu_update_pass_time() {
		return cu_update_pass_time;
	}

	public void setCu_update_pass_time(java.util.Date cu_update_pass_time) {
		this.cu_update_pass_time = cu_update_pass_time;
	}

	public java.util.Date getCu_update_info_time() {
		return cu_update_info_time;
	}

	public void setCu_update_info_time(java.util.Date cu_update_info_time) {
		this.cu_update_info_time = cu_update_info_time;
	}

}
