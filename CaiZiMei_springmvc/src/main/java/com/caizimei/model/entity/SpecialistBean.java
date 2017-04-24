/*
 * CaiZiMei
 * File: SpecialistBean.java
 * Author: 詹晟
 * Date: 2017/4/24
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
 * specialist entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "specialist")
public class SpecialistBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer s_id;
	private String s_username;
	private String s_password;
	private String s_salt;
	private String s_lastname;
	private String s_firstname;
	private String s_eng_name;
	private String s_email;
	private String s_mobilephone;
	private java.util.Date s_signup_time;
	private Integer s_signin_number;
	private String s_signin_ip;
	private java.util.Date s_signin_time;
	private java.util.Date s_update_pass_time;
	private java.util.Date s_update_info_time;
	@ManyToOne
	@JoinColumn(name = "s_c_id")
	private ClinicBean s_ClinicBean;

	public Integer getS_id() {
		return s_id;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public String getS_username() {
		return s_username;
	}

	public void setS_username(String s_username) {
		this.s_username = s_username;
	}

	public String getS_password() {
		return s_password;
	}

	public void setS_password(String s_password) {
		this.s_password = s_password;
	}

	public String getS_salt() {
		return s_salt;
	}

	public void setS_salt(String s_salt) {
		this.s_salt = s_salt;
	}

	public String getS_lastname() {
		return s_lastname;
	}

	public void setS_lastname(String s_lastname) {
		this.s_lastname = s_lastname;
	}

	public String getS_firstname() {
		return s_firstname;
	}

	public void setS_firstname(String s_firstname) {
		this.s_firstname = s_firstname;
	}

	public String getS_eng_name() {
		return s_eng_name;
	}

	public void setS_eng_name(String s_eng_name) {
		this.s_eng_name = s_eng_name;
	}

	public String getS_email() {
		return s_email;
	}

	public void setS_email(String s_email) {
		this.s_email = s_email;
	}

	public String getS_mobilephone() {
		return s_mobilephone;
	}

	public void setS_mobilephone(String s_mobilephone) {
		this.s_mobilephone = s_mobilephone;
	}

	public java.util.Date getS_signup_time() {
		return s_signup_time;
	}

	public void setS_signup_time(java.util.Date s_signup_time) {
		this.s_signup_time = s_signup_time;
	}

	public Integer getS_signin_number() {
		return s_signin_number;
	}

	public void setS_signin_number(Integer s_signin_number) {
		this.s_signin_number = s_signin_number;
	}

	public String getS_signin_ip() {
		return s_signin_ip;
	}

	public void setS_signin_ip(String s_signin_ip) {
		this.s_signin_ip = s_signin_ip;
	}

	public java.util.Date getS_signin_time() {
		return s_signin_time;
	}

	public void setS_signin_time(java.util.Date s_signin_time) {
		this.s_signin_time = s_signin_time;
	}

	public java.util.Date getS_update_pass_time() {
		return s_update_pass_time;
	}

	public void setS_update_pass_time(java.util.Date s_update_pass_time) {
		this.s_update_pass_time = s_update_pass_time;
	}

	public java.util.Date getS_update_info_time() {
		return s_update_info_time;
	}

	public void setS_update_info_time(java.util.Date s_update_info_time) {
		this.s_update_info_time = s_update_info_time;
	}

	public ClinicBean getS_ClinicBean() {
		return s_ClinicBean;
	}

	public void setS_ClinicBean(ClinicBean s_ClinicBean) {
		this.s_ClinicBean = s_ClinicBean;
	}

}
