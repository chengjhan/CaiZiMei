/*
 * CaiZiMei
 * File: CountryServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.CountryDAO;
import com.caizimei.model.entity.CountryBean;
import com.caizimei.model.service.CountryService;

/**
 * country service implement
 * 
 * @author 詹晟
 */
@Service(value = "countryService")
public class CountryServiceImpl implements CountryService {

	/**
	 * 注入 CountryDAO
	 */
	@Autowired
	private CountryDAO countryDAO;

	/**
	 * 搜尋全部國家
	 * 
	 * @return List<CountryBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CountryBean> select() {

		return countryDAO.select();
	}

	/**
	 * 國家流水號搜尋
	 * 
	 * @param co_id-->國家流水號
	 * @return result-->CountryBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CountryBean selectByCo_id(Integer co_id) {

		CountryBean result = null;

		if (co_id != 0) {

			result = countryDAO.selectByCo_id(co_id);
		}
		return result;
	}

	/**
	 * 國家名搜尋
	 * 
	 * @param co_name-->國家名
	 * @return result-->CountryBean
	 */
	@Override
	@Transactional(readOnly = true)
	public CountryBean selectByCo_name(String co_name) {

		CountryBean result = null;

		if (co_name != null) {

			result = countryDAO.selectByCo_name(co_name).get(0);
		}
		return result;
	}

	/**
	 * 新增國家
	 * 
	 * @param countryBean-->CountryBean
	 * @return result-->CountryBean
	 */
	@Override
	@Transactional
	public CountryBean insert(CountryBean countryBean) {

		CountryBean result = null;

		if (countryBean != null) {

			result = countryDAO.insert(countryBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param countryBean-->CountryBean
	 * @return CountryBean
	 */
	@Override
	@Transactional
	public CountryBean update(CountryBean countryBean) {

		return countryDAO.update(countryBean);
	}

	/**
	 * 刪除國家
	 * 
	 * @param co_id-->國家流水號
	 * @return true-->成功
	 */
	@Override
	@Transactional
	public Boolean delete(Integer co_id) {

		return countryDAO.delete(co_id);
	}

}
