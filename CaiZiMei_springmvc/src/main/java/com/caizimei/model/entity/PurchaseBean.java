/*
 * CaiZiMei
 * File: PurchaseBean.java
 * Author: 詹晟
 * Date: 2017/3/26
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
 * purchase entity
 * 
 * @author 詹晟
 */
@Entity
@Table(name = "purchase")
public class PurchaseBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer p_id;
	private Integer p_m_id;
	private Integer p_c_id;

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public Integer getP_m_id() {
		return p_m_id;
	}

	public void setP_m_id(Integer p_m_id) {
		this.p_m_id = p_m_id;
	}

	public Integer getP_c_id() {
		return p_c_id;
	}

	public void setP_c_id(Integer p_c_id) {
		this.p_c_id = p_c_id;
	}

}
