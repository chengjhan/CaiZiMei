/*
 * CaiZiMei
 * File: FranchiseeDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.FranchiseeDao;
import com.czmbeauty.model.entity.FranchiseeBean;

/**
 * franchisee DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "franchiseeDao")
public class FranchiseeDaoImpl implements FranchiseeDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有加盟店
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<FranchiseeBean> selectAll() {

		return (List<FranchiseeBean>) hibernateTemplate.find("from FranchiseeBean order by fr_id asc");
	}

	/**
	 * 加盟店流水號搜尋
	 * 
	 * @param fr_id-->加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	public FranchiseeBean selectByFr_id(Integer fr_id) {

		return hibernateTemplate.get(FranchiseeBean.class, fr_id);
	}

	/**
	 * 狀態搜尋
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<FranchiseeBean> selectByFr_status() {

		return (List<FranchiseeBean>) hibernateTemplate
				.find("from FranchiseeBean where fr_status=1 order by fr_id asc");
	}

	/**
	 * 新增加盟店
	 * 
	 * @param franchiseeBean-->FranchiseeBean
	 * @return franchiseeBean-->FranchiseeBean
	 */
	@Override
	public FranchiseeBean insert(FranchiseeBean franchiseeBean) {

		hibernateTemplate.save(franchiseeBean);

		return franchiseeBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param newFranchiseeBean-->FranchiseeBean
	 * @return franchiseeBean-->FranchiseeBean
	 */
	@Override
	public FranchiseeBean update(FranchiseeBean newFranchiseeBean) {

		FranchiseeBean franchiseeBean = hibernateTemplate.get(FranchiseeBean.class, newFranchiseeBean.getFr_id());

		franchiseeBean.setFr_name(newFranchiseeBean.getFr_name());
		franchiseeBean.setFr_eng_name(newFranchiseeBean.getFr_eng_name());
		franchiseeBean.setFr_localphone(newFranchiseeBean.getFr_localphone());
		franchiseeBean.setFr_CountryBean(newFranchiseeBean.getFr_CountryBean());
		franchiseeBean.setFr_StateBean(newFranchiseeBean.getFr_StateBean());
		franchiseeBean.setFr_CityBean(newFranchiseeBean.getFr_CityBean());
		franchiseeBean.setFr_address(newFranchiseeBean.getFr_address());
		franchiseeBean.setFr_latitude(newFranchiseeBean.getFr_latitude());
		franchiseeBean.setFr_longitude(newFranchiseeBean.getFr_longitude());
		franchiseeBean.setFr_url(newFranchiseeBean.getFr_url());
		franchiseeBean.setFr_update_time(newFranchiseeBean.getFr_update_time());

		return franchiseeBean;
	}

}
