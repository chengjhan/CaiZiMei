/*
 * CaiZiMei
 * File: BaseKindDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.BaseKindDao;
import com.czmbeauty.model.entity.BaseKindBean;

/**
 * base_kind DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "baseKindDao")
public class BaseKindDaoImpl implements BaseKindDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 據點種類流水號搜尋
	 * 
	 * @param bk_id
	 *            Integer --> 據點種類流水號
	 * @return BaseKindBean
	 */
	@Override
	public BaseKindBean selectByBk_id(Integer bk_id) {

		return hibernateTemplate.get(BaseKindBean.class, bk_id);
	}

}
