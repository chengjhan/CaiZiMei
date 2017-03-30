/*
 * CaiZiMei
 * File: MemberDAO.java
 * Author: 詹晟
 * Date: 2017/3/30
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.entity.MemberBean;

/**
 * member DAO interface
 *
 * @author 詹晟
 */
public interface MemberDAO {

	List<MemberBean> select();

	MemberBean selectByM_id(Integer m_id);

	MemberBean selectByM_username(String m_username);

	List<MemberBean> selectByConditions(String m_lastname, String m_firstname, String m_mobilephone, String m_email);

	MemberBean insert(MemberBean memberBean);

	MemberBean update(MemberBean memberBean);

	MemberBean updateM_password(Integer m_id, String m_password_new_hashed);

	MemberBean updateM_signin_number(Integer m_id);

	MemberBean updateM_signin_ip(Integer m_id);

	MemberBean updateM_signin_time(Integer m_id);

}
