/*
 * CaiZiMei
 * File: AdministratorBean.java
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
 * administrator entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "administrator")
public class AdministratorBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer a_id;
	private String a_username;
	private String a_password;
	private String a_salt;
	private String a_lastname;
	private String a_firstname;
	private String a_eng_name;
	private String a_mobilephone;
	private java.util.Date a_signup_time;

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

	public String getA_eng_name() {
		return a_eng_name;
	}

	public void setA_eng_name(String a_eng_name) {
		this.a_eng_name = a_eng_name;
	}

	public String getA_mobilephone() {
		return a_mobilephone;
	}

	public void setA_mobilephone(String a_mobilephone) {
		this.a_mobilephone = a_mobilephone;
	}

	public java.util.Date getA_signup_time() {
		return a_signup_time;
	}

	public void setA_signup_time(java.util.Date a_signup_time) {
		this.a_signup_time = a_signup_time;
	}

}
