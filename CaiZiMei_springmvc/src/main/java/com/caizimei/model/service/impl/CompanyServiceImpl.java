/*
 * CaiZiMei
 * File: CompanyServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/9
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.CompanyDAO;
import com.caizimei.model.entity.CompanyBean;
import com.caizimei.model.service.CompanyService;

/**
 * company service implement
 * 
 * @author 詹晟
 */
@Service(value = "companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {

	/**
	 * 注入 CompanyDAO
	 */
	@Autowired
	private CompanyDAO companyDAO;

	/**
	 * 搜尋全部代理商
	 * 
	 * @return List<CompanyBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CompanyBean> select() {

		return companyDAO.select();
	}

	/**
	 * 新增代理商
	 * 
	 * @param companyBean-->CompanyBean
	 * @return result-->CompanyBean
	 */
	@Override
	@Transactional
	public CompanyBean insert(CompanyBean companyBean) {

		CompanyBean result = null;

		if (companyBean != null) {

			result = companyDAO.insert(companyBean);
		}
		return result;
	}

}
