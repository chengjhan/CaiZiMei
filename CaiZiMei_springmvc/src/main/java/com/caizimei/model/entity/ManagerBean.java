/*
 * CaiZiMei
 * File: ManagerBean.java
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
import javax.persistence.Table;

/**
 * manager entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "manager")
public class ManagerBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ma_id;
	private String ma_username;
	private String ma_password;
	private String ma_salt;
	private String ma_lastname;
	private String ma_firstname;
	private String ma_eng_name;
	private String ma_mobilephone;
	private java.util.Date ma_signup_time;

	public Integer getMa_id() {
		return ma_id;
	}

	public void setMa_id(Integer ma_id) {
		this.ma_id = ma_id;
	}

	public String getMa_username() {
		return ma_username;
	}

	public void setMa_username(String ma_username) {
		this.ma_username = ma_username;
	}

	public String getMa_password() {
		return ma_password;
	}

	public void setMa_password(String ma_password) {
		this.ma_password = ma_password;
	}

	public String getMa_salt() {
		return ma_salt;
	}

	public void setMa_salt(String ma_salt) {
		this.ma_salt = ma_salt;
	}

	public String getMa_lastname() {
		return ma_lastname;
	}

	public void setMa_lastname(String ma_lastname) {
		this.ma_lastname = ma_lastname;
	}

	public String getMa_firstname() {
		return ma_firstname;
	}

	public void setMa_firstname(String ma_firstname) {
		this.ma_firstname = ma_firstname;
	}

	public String getMa_eng_name() {
		return ma_eng_name;
	}

	public void setMa_eng_name(String ma_eng_name) {
		this.ma_eng_name = ma_eng_name;
	}

	public String getMa_mobilephone() {
		return ma_mobilephone;
	}

	public void setMa_mobilephone(String ma_mobilephone) {
		this.ma_mobilephone = ma_mobilephone;
	}

	public java.util.Date getMa_signup_time() {
		return ma_signup_time;
	}

	public void setMa_signup_time(java.util.Date ma_signup_time) {
		this.ma_signup_time = ma_signup_time;
	}

}
