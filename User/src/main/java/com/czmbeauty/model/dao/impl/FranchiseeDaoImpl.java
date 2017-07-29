/*
 * CaiZiMei/User
 * File: FranchiseeDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/30
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

}
