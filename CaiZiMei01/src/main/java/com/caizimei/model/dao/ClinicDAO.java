package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.ClinicBean;

public interface ClinicDAO {

	List<ClinicBean> select();

	List<ClinicBean> selectByConditions(String c_name, String c_telephone);

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	Boolean delete(Integer c_id);

}
