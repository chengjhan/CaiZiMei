package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.CountryBean;

public interface CountryService {

	List<CountryBean> select();

	CountryBean selectByCo_id(Integer co_id);

	CountryBean selectByCo_name(String co_name);

	CountryBean insert(CountryBean countryBean);

	CountryBean update(CountryBean countryBean);

	Boolean delete(Integer co_id);

}
