package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.ClinicBean;

public interface ClinicService {

	List<ClinicBean> select();

	List<ClinicBean> selectByConditions(String c_name, String c_telephone);

	ClinicBean insert(ClinicBean clinicBean);

	ClinicBean update(ClinicBean clinicBean);

	Boolean delete(Integer c_id);

	Double[] addressToLatLng(String address) throws Exception;

}
