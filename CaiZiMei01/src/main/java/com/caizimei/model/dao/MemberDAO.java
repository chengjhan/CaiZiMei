package com.caizimei.model.dao;

import java.util.List;

import com.caizimei.model.MemberBean;

public interface MemberDAO {

	List<MemberBean> select();

	MemberBean selectByM_id(Integer m_id);

	MemberBean selectByM_account(String m_account);

	List<MemberBean> selectByConditions(String m_firstname, String m_lastname, String m_telephone, String m_email);

	MemberBean insert(MemberBean memberBean);

	MemberBean update(MemberBean memberBean);

	MemberBean updateM_password(Integer m_id, String m_password);

	MemberBean updateM_signin_time(Integer m_id);

}
