/*
 * CaiZiMei
 * File: ClinicDAOImpl.java
 * Author: 詹晟
 * Date: 2017/4/13
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

		return (List<ClinicBean>) hibernateTemplate.find("from ClinicBean order by c_id asc");
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
	 * 區域流水號搜尋可顯示的診所
	 * 
	 * @param c_r_id-->區域流水號
	 * @return List<ClinicBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectByC_r_id(Integer c_r_id) {

		return (List<ClinicBean>) hibernateTemplate
				.findByNamedParam("from ClinicBean where c_r_id=:c_r_id and c_status=1", "c_r_id", c_r_id);
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
	 * @param newClinicBean-->ClinicBean
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean update(ClinicBean newClinicBean) {

		ClinicBean clinicBean = hibernateTemplate.get(ClinicBean.class, newClinicBean.getC_id());

		clinicBean.setC_name(newClinicBean.getC_name());
		clinicBean.setC_eng_name(newClinicBean.getC_eng_name());
		clinicBean.setC_localphone(newClinicBean.getC_localphone());
		clinicBean.setC_RegionBean(newClinicBean.getC_RegionBean());
		clinicBean.setC_address(newClinicBean.getC_address());
		clinicBean.setC_url(newClinicBean.getC_url());
		clinicBean.setC_update_time(new java.util.Date());

		return clinicBean;
	}

	/**
	 * 切換診所顯示狀態
	 * 
	 * @param c_id-->診所流水號
	 * @return clinicBean-->ClinicBean
	 */
	@Override
	public ClinicBean updateC_status(Integer c_id) {

		ClinicBean clinicBean = hibernateTemplate.get(ClinicBean.class, c_id);

		if (clinicBean.getC_status() == 1) {

			// 不顯示
			clinicBean.setC_status(0);
			clinicBean.setC_status_time(new java.util.Date());

		} else {

			// 顯示
			clinicBean.setC_status(1);
			clinicBean.setC_status_time(new java.util.Date());

		}

		return clinicBean;
	}

}
