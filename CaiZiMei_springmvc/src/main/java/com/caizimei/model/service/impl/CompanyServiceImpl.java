/*
 * CaiZiMei
 * File: CompanyServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/17
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
public class CompanyServiceImpl implements CompanyService {

	/**
	 * 注入 CompanyDAO
	 */
	@Autowired
	private CompanyDAO companyDAO;

	/**
	 * 搜尋全部公司
	 * 
	 * @return List<CompanyBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CompanyBean> select() {

		return companyDAO.select();
	}

	/**
	 * 公司流水號搜尋
	 * 
	 * @return CompanyBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CompanyBean selectByCom_id(Integer com_id) {

		return companyDAO.selectByCom_id(com_id);
	}

	/**
	 * 搜尋可顯示的公司
	 * 
	 * List<CompanyBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CompanyBean> selectByCom_status() {

		return companyDAO.selectByCom_status();
	}

	/**
	 * 新增公司
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

	/**
	 * 修改公司資料
	 * 
	 * @param companyBean-->CompanyBean
	 * @return CompanyBean
	 */
	@Override
	@Transactional
	public CompanyBean update(CompanyBean newCompanyBean) {

		return companyDAO.update(newCompanyBean);
	}

	/**
	 * 切換公司顯示狀態
	 * 
	 * @param com_id-->公司流水號
	 * @return CompanyBean
	 */
	@Override
	@Transactional
	public CompanyBean updateCom_status(Integer com_id) {

		return companyDAO.updateCom_status(com_id);
	}

}
