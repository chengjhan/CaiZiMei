package com.caizimei.model.service;

import java.util.List;

import com.caizimei.model.MemberBean;

public interface MemberService {

	Boolean signIn(String m_account, String m_password);

	MemberBean signUp(MemberBean memberBean);

	MemberBean updateM_password(Integer m_id, String m_password);

	MemberBean selectByM_account(String m_account);

	List<MemberBean> selectByConditions(String m_firstname, String m_lastname, String m_telephone, String m_email);

	String passwordToMD5(String m_password);

}
