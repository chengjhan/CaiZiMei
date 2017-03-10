package com.caizimei.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class MemberBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer m_id;
	private String m_account;
	private String m_password;
	private String m_lastname;
	private String m_firstname;
	private java.util.Date m_birth;
	private Integer m_sex;
	private Double m_height;
	private Double m_weight;
	private String m_telephone;
	private String m_address;
	private String m_email;
	private java.util.Date m_signup_time;
	private java.util.Date m_signin_time;

	public Integer getM_id() {
		return m_id;
	}

	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}

	public String getM_account() {
		return m_account;
	}

	public void setM_account(String m_account) {
		this.m_account = m_account;
	}

	public String getM_password() {
		return m_password;
	}

	public void setM_password(String m_password) {
		this.m_password = m_password;
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

	public String getM_telephone() {
		return m_telephone;
	}

	public void setM_telephone(String m_telephone) {
		this.m_telephone = m_telephone;
	}

	public String getM_address() {
		return m_address;
	}

	public void setM_address(String m_address) {
		this.m_address = m_address;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public java.util.Date getM_signup_time() {
		return m_signup_time;
	}

	public void setM_signup_time(java.util.Date m_signup_time) {
		this.m_signup_time = m_signup_time;
	}

	public java.util.Date getM_signin_time() {
		return m_signin_time;
	}

	public void setM_signin_time(java.util.Date m_signin_time) {
		this.m_signin_time = m_signin_time;
	}

}
