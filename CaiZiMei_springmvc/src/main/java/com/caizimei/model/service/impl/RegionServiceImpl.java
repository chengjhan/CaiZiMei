/*
 * CaiZiMei
 * File: RegionServiceImpl.java
 * Author: 詹晟
 * Date: 2017/4/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.dao.CityDAO;
import com.caizimei.model.dao.RegionDAO;
import com.caizimei.model.entity.RegionBean;
import com.caizimei.model.service.RegionService;

/**
 * region service implement
 * 
 * @author 詹晟
 */
@Service(value = "regionService")
public class RegionServiceImpl implements RegionService {

	/**
	 * 注入 RegionDAO
	 */
	@Autowired
	private RegionDAO regionDAO;

	/**
	 * 注入 CityDAO
	 */
	@Autowired
	private CityDAO cityDAO;

	/**
	 * 區域流水號搜尋
	 * 
	 * @param r_id-->區域流水號
	 * @return RegionBean
	 */
	@Override
	@Transactional(readOnly = true)
	public RegionBean selectByR_id(Integer r_id) {

		return regionDAO.selectByR_id(r_id);
	}

	/**
	 * 城市流水號搜尋
	 * 
	 * @param r_ci_id-->城市流水號
	 * @return List<RegionBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<RegionBean> selectByR_ci_id(Integer r_ci_id) {

		return regionDAO.selectByR_ci_id(r_ci_id);
	}

	/**
	 * 區域名搜尋
	 * 
	 * @param r_name-->區域名
	 * @return List<RegionBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<RegionBean> selectByR_name(String r_name) {

		List<RegionBean> result = null;

		if (r_name != null) {

			result = regionDAO.selectByR_name(r_name);
		}
		return (List<RegionBean>) result;
	}

	/**
	 * 郵遞區號搜尋
	 * 
	 * @param r_zipcode-->郵遞區號
	 * @return List<RegionBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public RegionBean selectByR_zipcode(String r_zipcode) {

		RegionBean result = null;

		if (r_zipcode != null) {

			result = regionDAO.selectByR_name(r_zipcode).get(0);
		}
		return result;
	}

	/**
	 * 條件搜尋
	 * 
	 * @param r_ci_id-->城市流水號
	 * @param r_name-->區域名
	 * @param r_zipcode-->郵遞區號
	 * @return List<RegionBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<RegionBean> selectByRegionConditions(Integer r_ci_id, String r_name, String r_zipcode) {

		return regionDAO.selectByRegionConditions(cityDAO.selectByCi_id(r_ci_id), r_name, r_zipcode);
	}

	/**
	 * 新增區域
	 * 
	 * @param regionBean-->RegionBean
	 * @return regionBean-->RegionBean
	 */
	@Override
	@Transactional
	public RegionBean insert(RegionBean regionBean) {

		RegionBean result = null;

		if (regionBean != null) {

			result = regionDAO.insert(regionBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param regionBean-->RegionBean
	 * @return regionBean-->RegionBean
	 */
	@Override
	@Transactional
	public RegionBean update(RegionBean regionBean) {

		return regionDAO.update(regionBean);
	}

	/**
	 * 刪除區域
	 * 
	 * @param r_id-->區域流水號
	 * @return true-->成功
	 */
	@Override
	@Transactional
	public Boolean delete(Integer r_id) {

		return regionDAO.delete(r_id);
	}

}
