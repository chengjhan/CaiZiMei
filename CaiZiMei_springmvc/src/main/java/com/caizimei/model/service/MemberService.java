/*
 * CaiZiMei
 * File: MemberService.java
 * Author: 詹晟
 * Date: 2017/4/5
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.entity.MemberBean;

/**
 * member service interface
 * 
 * @author 詹晟
 */
public interface MemberService {

	MemberBean signUp(MemberBean memberBean);

	Boolean signIn(String m_username, String m_password);

	MemberBean selectByM_id(Integer m_id);

	MemberBean selectByM_username(String m_username);

	List<MemberBean> selectByConditions(String m_username, String m_lastname, String m_firstname, String m_mobilephone);

	MemberBean update(MemberBean memberBean);

	MemberBean updateM_password(Integer m_id, String m_password_new, String m_salt);

	MemberBean updateM_signin_ip(Integer m_id, String m_signin_ip);

	MemberBean updateM_signin_time(Integer m_id);

	String getHashedPassword(String m_password, String m_salt);

	String getMD5(String str);

	String getSalt();

}
