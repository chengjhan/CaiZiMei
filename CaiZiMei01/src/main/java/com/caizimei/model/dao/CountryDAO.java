package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.CountryBean;

public interface CountryDAO {

	List<CountryBean> select();

	CountryBean selectByCo_id(Integer co_id);

	List<CountryBean> selectByCo_name(String co_name);

	CountryBean insert(CountryBean countryBean);

	CountryBean update(CountryBean countryBean);

	Boolean delete(Integer co_id);

}
