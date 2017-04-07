/*
 * CaiZiMei
 * File: AgentBean.java
 * Author: 詹晟
 * Date: 2017/4/7
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
 * agent entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "agent")
public class AgentBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer a_id;
	private String a_name;
	private String a_localphone;
	private java.util.Date a_signup_time;

	public Integer getA_id() {
		return a_id;
	}

	public void setA_id(Integer a_id) {
		this.a_id = a_id;
	}

	public String getA_name() {
		return a_name;
	}

	public void setA_name(String a_name) {
		this.a_name = a_name;
	}

	public String getA_localphone() {
		return a_localphone;
	}

	public void setA_localphone(String a_localphone) {
		this.a_localphone = a_localphone;
	}

	public java.util.Date getA_signup_time() {
		return a_signup_time;
	}

	public void setA_signup_time(java.util.Date a_signup_time) {
		this.a_signup_time = a_signup_time;
	}

}
