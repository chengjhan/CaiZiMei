package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.CityBean;

public interface CityService {

	List<CityBean> select();

	CityBean selectByCi_id(Integer ci_id);

	CityBean selectByCi_name(String ci_name);

	List<CityBean> selectByCi_co_name(String ci_co_name);

	CityBean insert(CityBean cityBean);

	CityBean update(CityBean cityBean);

	Boolean delete(Integer ci_id);

}
