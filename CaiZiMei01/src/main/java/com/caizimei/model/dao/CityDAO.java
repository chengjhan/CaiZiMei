package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.CityBean;

public interface CityDAO {

	List<CityBean> select();

	CityBean selectByCi_id(Integer ci_id);

	List<CityBean> selectByCi_name(String ci_name);

	List<CityBean> selectByCi_co_id(Integer ci_co_id);

	CityBean insert(CityBean cityBean);

	CityBean update(CityBean cityBean);

	Boolean delete(Integer ci_id);

}
