/*
 * CaiZiMei
 * File: MemberBean.java
 * Author: 詹晟
 * Date: 2017/4/2
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * member entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "member")
public class MemberBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer m_id;
	private String m_username;
	private String m_email;
	private String m_password;
	private String m_salt;
	private String m_lastname;
	private String m_firstname;
	@Temporal(TemporalType.DATE)
	private java.util.Date m_birth;
	private Integer m_sex;
	private Double m_height;
	private Double m_weight;
	private String m_localphone;
	private String m_mobilephone;
	private String m_zipcode;
	private String m_country;
	private String m_city;
	private String m_region;
	private String m_address;
	private Integer m_limit;
	private java.util.Date m_signup_time;
	private Integer m_signin_number;
	private String m_signin_ip;
	private java.util.Date m_signin_time;
	private java.util.Date m_update_pass_time;
	private java.util.Date m_update_info_time;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "purchase", joinColumns = @JoinColumn(name = "p_m_id"), inverseJoinColumns = @JoinColumn(name = "p_c_id"))
	private Set<ClinicBean> m_ClinicBean;

	public Integer getM_id() {
		return m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}

	public String getM_username() {
		return m_username;
	}

	public void setM_username(String m_username) {
		this.m_username = m_username;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
	}

	public String getM_salt() {
		return m_salt;
	}

	public void setM_salt(String m_salt) {
		this.m_salt = m_salt;
	}

	public String getM_lastname() {
		return m_lastname;
	}

	public void setM_lastname(String m_lastname) {
		this.m_lastname = m_lastname;
	}

	public String getM_firstname() {
		return m_firstname;
	}

	public void setM_firstname(String m_firstname) {
		this.m_firstname = m_firstname;
	}

	public java.util.Date getM_birth() {
		return m_birth;
	}

	public void setM_birth(java.util.Date m_birth) {
		this.m_birth = m_birth;
	}

	public Integer getM_sex() {
		return m_sex;
	}

	public void setM_sex(Integer m_sex) {
		this.m_sex = m_sex;
	}

	public Double getM_height() {
		return m_height;
	}

	public void setM_height(Double m_height) {
		this.m_height = m_height;
	}

	public Double getM_weight() {
		return m_weight;
	}

	public void setM_weight(Double m_weight) {
		this.m_weight = m_weight;
	}

	public String getM_localphone() {
		return m_localphone;
	}

	public void setM_localphone(String m_localphone) {
		this.m_localphone = m_localphone;
	}

	public String getM_mobilephone() {
		return m_mobilephone;
	}

	public void setM_mobilephone(String m_mobilephone) {
		this.m_mobilephone = m_mobilephone;
	}

	public String getM_zipcode() {
		return m_zipcode;
	}

	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}

	public String getM_country() {
		return m_country;
	}

	public void setM_country(String m_country) {
		this.m_country = m_country;
	}

	public String getM_city() {
		return m_city;
	}

	public void setM_city(String m_city) {
		this.m_city = m_city;
	}

	public String getM_region() {
		return m_region;
	}

	public void setM_region(String m_region) {
		this.m_region = m_region;
	}

	public String getM_address() {
		return m_address;
	}

	public void setM_address(String m_address) {
		this.m_address = m_address;
	}

	public Integer getM_limit() {
		return m_limit;
	}

	public void setM_limit(Integer m_limit) {
		this.m_limit = m_limit;
	}

	public java.util.Date getM_signup_time() {
		return m_signup_time;
	}

	public void setM_signup_time(java.util.Date m_signup_time) {
		this.m_signup_time = m_signup_time;
	}

	public Integer getM_signin_number() {
		return m_signin_number;
	}

	public void setM_signin_number(Integer m_signin_number) {
		this.m_signin_number = m_signin_number;
	}

	public String getM_signin_ip() {
		return m_signin_ip;
	}

	public void setM_signin_ip(String m_signin_ip) {
		this.m_signin_ip = m_signin_ip;
	}

	public java.util.Date getM_signin_time() {
		return m_signin_time;
	}

	public void setM_signin_time(java.util.Date m_signin_time) {
		this.m_signin_time = m_signin_time;
	}

	public java.util.Date getM_update_pass_time() {
		return m_update_pass_time;
	}

	public void setM_update_pass_time(java.util.Date m_update_pass_time) {
		this.m_update_pass_time = m_update_pass_time;
	}

	public java.util.Date getM_update_info_time() {
		return m_update_info_time;
	}

	public void setM_update_info_time(java.util.Date m_update_info_time) {
		this.m_update_info_time = m_update_info_time;
	}

	public Set<ClinicBean> getM_ClinicBean() {
		return m_ClinicBean;
	}

	public void setM_ClinicBean(Set<ClinicBean> m_ClinicBean) {
		this.m_ClinicBean = m_ClinicBean;
	}

}
