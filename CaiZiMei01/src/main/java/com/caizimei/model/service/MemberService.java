/*
 * CaiZiMei
 * File: MemberService.java
 * Author: Cheng Jhan
 * Date: 2017/3/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.MemberBean;

/** member service */
public interface MemberService {

	Boolean signIn(String m_account, String m_password_MD5);

	MemberBean signUp(MemberBean memberBean);

	MemberBean selectByM_id(Integer m_id);

	MemberBean selectByM_account(String m_account);

	List<MemberBean> selectByConditions(String m_firstname, String m_lastname, String m_telephone, String m_email);

	MemberBean update(MemberBean memberBean);

	MemberBean updateM_password(Integer m_id, String m_password_new_MD5);

	String passwordToMD5(String m_password);

}
