/*
 * CaiZiMei/User
 * File: BaseDaoImpl.java
 * Author: 詹晟
 * Date: 2017/7/31
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
	 * 狀態搜尋
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseBean> selectOpen() {

		return (List<BaseBean>) hibernateTemplate.find("from BaseBean where ba_status=1 order by ba_id asc");
	}

}
