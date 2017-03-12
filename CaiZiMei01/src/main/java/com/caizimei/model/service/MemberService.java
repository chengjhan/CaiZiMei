package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.MemberBean;

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
