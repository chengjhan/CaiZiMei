/*
 * CaiZiMei
 * File: AdministratorLogDAO.java
 * Author: 詹晟
 * Date: 2017/4/10
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.AdministratorLogBean;

/**
 * administrator_log DAO interface
 *
 * @author 詹晟
 */
public interface AdministratorLogDAO {

	List<AdministratorLogBean> select();

	List<AdministratorLogBean> selectByAl_a_id(Integer al_a_id);

	AdministratorLogBean insert(AdministratorLogBean administratorLogBean);

}
