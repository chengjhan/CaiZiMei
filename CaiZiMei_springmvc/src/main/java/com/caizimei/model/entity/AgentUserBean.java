/*
 * CaiZiMei
 * File: AgentUserBean.java
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
 * agent_user entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "agent_user")
public class AgentUserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer au_id;
	@ManyToOne
	@JoinColumn(name = "au_a_id")
	private AgentBean au_AgentBean;
	private String au_username;
	private String au_password;
	private String au_salt;
	private String au_lastname;
	private String au_firstname;
	private String au_eng_name;
	private String au_email;
	private String au_mobilephone;
	private java.util.Date au_signup_time;
	private Integer au_signin_number;
	private String au_signin_ip;
	private java.util.Date au_signin_time;
	private java.util.Date au_update_pass_time;
	private java.util.Date au_update_info_time;

	public Integer getAu_id() {
		return au_id;
	}

	public void setAu_id(Integer au_id) {
		this.au_id = au_id;
	}

	public AgentBean getAu_AgentBean() {
		return au_AgentBean;
	}

	public void setAu_AgentBean(AgentBean au_AgentBean) {
		this.au_AgentBean = au_AgentBean;
	}

	public String getAu_username() {
		return au_username;
	}

	public void setAu_username(String au_username) {
		this.au_username = au_username;
	}

	public String getAu_password() {
		return au_password;
	}

	public void setAu_password(String au_password) {
		this.au_password = au_password;
	}

	public String getAu_salt() {
		return au_salt;
	}

	public void setAu_salt(String au_salt) {
		this.au_salt = au_salt;
	}

	public String getAu_lastname() {
		return au_lastname;
	}

	public void setAu_lastname(String au_lastname) {
		this.au_lastname = au_lastname;
	}

	public String getAu_firstname() {
		return au_firstname;
	}

	public void setAu_firstname(String au_firstname) {
		this.au_firstname = au_firstname;
	}

	public String getAu_eng_name() {
		return au_eng_name;
	}

	public void setAu_eng_name(String au_eng_name) {
		this.au_eng_name = au_eng_name;
	}

	public String getAu_email() {
		return au_email;
	}

	public void setAu_email(String au_email) {
		this.au_email = au_email;
	}

	public String getAu_mobilephone() {
		return au_mobilephone;
	}

	public void setAu_mobilephone(String au_mobilephone) {
		this.au_mobilephone = au_mobilephone;
	}

	public java.util.Date getAu_signup_time() {
		return au_signup_time;
	}

	public void setAu_signup_time(java.util.Date au_signup_time) {
		this.au_signup_time = au_signup_time;
	}

	public Integer getAu_signin_number() {
		return au_signin_number;
	}

	public void setAu_signin_number(Integer au_signin_number) {
		this.au_signin_number = au_signin_number;
	}

	public String getAu_signin_ip() {
		return au_signin_ip;
	}

	public void setAu_signin_ip(String au_signin_ip) {
		this.au_signin_ip = au_signin_ip;
	}

	public java.util.Date getAu_signin_time() {
		return au_signin_time;
	}

	public void setAu_signin_time(java.util.Date au_signin_time) {
		this.au_signin_time = au_signin_time;
	}

	public java.util.Date getAu_update_pass_time() {
		return au_update_pass_time;
	}

	public void setAu_update_pass_time(java.util.Date au_update_pass_time) {
		this.au_update_pass_time = au_update_pass_time;
	}

	public java.util.Date getAu_update_info_time() {
		return au_update_info_time;
	}

	public void setAu_update_info_time(java.util.Date au_update_info_time) {
		this.au_update_info_time = au_update_info_time;
	}

}
