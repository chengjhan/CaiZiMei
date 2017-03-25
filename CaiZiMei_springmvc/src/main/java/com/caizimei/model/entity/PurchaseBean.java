package com.caizimei.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne
	@JoinColumn(name = "p_m_id")
	private MemberBean p_MemberBean;
	@ManyToOne
	@JoinColumn(name = "p_c_id")
	private ClinicBean p_ClinicBean;

	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	public MemberBean getP_MemberBean() {
		return p_MemberBean;
	}

	public void setP_MemberBean(MemberBean p_MemberBean) {
		this.p_MemberBean = p_MemberBean;
	}

	public ClinicBean getP_ClinicBean() {
		return p_ClinicBean;
	}

	public void setP_ClinicBean(ClinicBean p_ClinicBean) {
		this.p_ClinicBean = p_ClinicBean;
	}

}
