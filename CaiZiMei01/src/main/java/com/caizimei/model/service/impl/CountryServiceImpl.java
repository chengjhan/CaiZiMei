package com.caizimei.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caizimei.model.CountryBean;
import com.caizimei.model.dao.CountryDAO;
import com.caizimei.model.service.CountryService;

@Service(value = "countryService")
@Transactional
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;

	@Override
	@Transactional(readOnly = true)
	public List<CountryBean> select() {
		return countryDAO.select();
	}

	@Override
	@Transactional(readOnly = true)
	public CountryBean selectByCo_id(Integer co_id) {
		CountryBean result = null;
		if (co_id != 0) {
			result = countryDAO.selectByCo_id(co_id);
		}
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public CountryBean selectByCo_name(String co_name) {
		CountryBean result = null;
		if (co_name != null) {
			result = countryDAO.selectByCo_name(co_name).get(0);
		}
		return result;
	}

	@Override
	@Transactional
	public CountryBean insert(CountryBean countryBean) {
		CountryBean result = null;
		if (countryBean != null) {
			result = countryDAO.insert(countryBean);
		}
		return result;
	}

	@Override
	@Transactional
	public CountryBean update(CountryBean countryBean) {
		return countryDAO.update(countryBean);
	}

	@Override
	@Transactional
	public Boolean delete(Integer co_id) {
		return countryDAO.delete(co_id);
	}

}
