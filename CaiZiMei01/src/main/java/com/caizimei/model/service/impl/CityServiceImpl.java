package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.CityBean;
import com.caizimei.model.dao.CityDAO;
import com.caizimei.model.dao.CountryDAO;
import com.caizimei.model.service.CityService;

@Service(value = "cityService")
@Transactional
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDAO cityDAO;
	@Autowired
	private CountryDAO countryDAO;

	@Override
	@Transactional(readOnly = true)
	public List<CityBean> select() {
		return cityDAO.select();
	}

	@Override
	@Transactional(readOnly = true)
	public CityBean selectByCi_id(Integer ci_id) {
		CityBean result = null;
		if (ci_id != 0) {
			result = cityDAO.selectByCi_id(ci_id);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public CityBean selectByCi_name(String ci_name) {
		CityBean result = null;
		if (ci_name != null) {
			result = cityDAO.selectByCi_name(ci_name).get(0);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CityBean> selectByCi_co_name(String co_name) {
		return cityDAO.selectByCi_co_id(countryDAO.selectByCo_name(co_name).get(0).getCo_id());
	}

	@Override
	@Transactional
	public CityBean insert(CityBean cityBean) {
		CityBean result = null;
		if (cityBean != null) {
			result = cityDAO.insert(cityBean);
		}
		return result;
	}

	@Override
	@Transactional
	public CityBean update(CityBean cityBean) {
		return cityDAO.update(cityBean);
	}

	@Override
	@Transactional
	public Boolean delete(Integer ci_id) {
		return cityDAO.delete(ci_id);
	}

}
