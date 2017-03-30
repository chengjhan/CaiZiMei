/*
 * CaiZiMei
 * File: ClinicDAOImpl.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.ClinicDAO;
import com.caizimei.model.entity.ClinicBean;

/**
 * clinic DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "clinicDAO")
public class ClinicDAOImpl implements ClinicDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部診所
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> select() {

		return (List<ClinicBean>) hibernateTemplate.find("from ClinicBean");
	}

	/**
	 * 診所流水號搜尋
	 * 
	 * @param c_id-->診所流水號
	 * @return List<ClinicBean>
	 */
	@Override
	public ClinicBean selectByC_id(Integer c_id) {

		return hibernateTemplate.get(ClinicBean.class, c_id);
	}

	/**
	 * 診所名搜尋
	 * 
	 * @param c_name-->診所名
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectByC_name(String c_name) {

		return (List<ClinicBean>) hibernateTemplate.findByNamedParam("from ClinicBean where c_name=:c_name", "c_name",
				c_name);
	}

	/**
	 * 城市流水號搜尋
	 * 
	 * @param c_ci_id-->城市流水號
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectByC_r_id(Integer c_r_id) {

		return (List<ClinicBean>) hibernateTemplate.findByNamedParam("from ClinicBean where c_r_id=:c_r_id", "c_r_id",
				c_r_id);
	}

	/**
	 * 條件搜尋
	 * 
	 * @param c_name-->診所名
	 * @param c_localphone-->診所電話
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectByConditions(String c_name, String c_localphone) {

		DetachedCriteria criteria = DetachedCriteria.forClass(ClinicBean.class);

		if (c_name != null && !c_name.trim().isEmpty()) {
			criteria.add(Restrictions.like("c_name", "%" + c_name + "%"));
		}
		if (c_localphone != null && !c_localphone.trim().isEmpty()) {
			criteria.add(Restrictions.like("c_localphone", "%" + c_localphone + "%"));
		}

		return (List<ClinicBean>) hibernateTemplate.findByCriteria(criteria);
	}

	/**
	 * 新增診所
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean insert(ClinicBean clinicBean) {

		hibernateTemplate.save(clinicBean);

		return clinicBean;
	}

	/**
	 * 修改診所資料
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean update(ClinicBean clinicBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(clinicBean);

		return clinicBean;
	}

	/**
	 * 刪除診所
	 * 
	 * @param c_id-->診所流水號
	 * @return true-->成功
	 */
	@Override
	public Boolean delete(Integer c_id) {

		hibernateTemplate.delete(hibernateTemplate.get(ClinicBean.class, c_id));

		return true;
	}

}
