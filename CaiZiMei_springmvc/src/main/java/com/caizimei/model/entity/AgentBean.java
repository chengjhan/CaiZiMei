/*
 * CaiZiMei
 * File: AgentBean.java
 * Author: 詹晟
 * Date: 2017/4/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date a_insert_time;

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

	public java.util.Date getA_insert_time() {
		return a_insert_time;
	}

	public void setA_insert_time(java.util.Date a_insert_time) {
		this.a_insert_time = a_insert_time;
	}

}
