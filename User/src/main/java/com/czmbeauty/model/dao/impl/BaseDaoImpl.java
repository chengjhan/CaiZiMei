/*
 * CaiZiMei/User
 * File: BaseDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/20
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.BaseDao;
import com.czmbeauty.model.entity.BaseBean;

/**
 * base DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋開啟的據點
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseBean> selectOpenBase() {

		return (List<BaseBean>) hibernateTemplate.find(HQL_SELECT_OPEN_BASE);
	}

}
