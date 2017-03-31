/*
 * CaiZiMei
 * File: MemberDAOImpl.java
 * Author: 詹晟
 * Date: 2017/3/31
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.dao.MemberDAO;
import com.caizimei.model.entity.MemberBean;

/**
 * member DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "mamberDAO")
public class MemberDAOImpl implements MemberDAO {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋全部會員
	 * 
	 * @return List<MemberBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MemberBean> select() {

		return (List<MemberBean>) hibernateTemplate.find("from MemberBean");
	}

	/**
	 * 會員流水號搜尋
	 * 
	 * @param m_id-->會員流水號
	 * @return MemberBean
	 */
	@Override
	public MemberBean selectByM_id(Integer m_id) {

		return hibernateTemplate.get(MemberBean.class, m_id);
	}

	/**
	 * 會員帳號搜尋
	 * 
	 * @param m_username-->會員帳號
	 * @return MemberBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public MemberBean selectByM_username(String m_username) {

		List<MemberBean> list = (List<MemberBean>) hibernateTemplate
				.findByNamedParam("from MemberBean where m_username=:m_username", "m_username", m_username);

		if (!list.isEmpty()) {

			return list.get(0);
		}
		return null;
	}

	/**
	 * 條件搜尋
	 * 
	 * @param m_lastname-->會員姓
	 * @param m_firstname-->會員名
	 * @param m_mobilephone-->會員手機
	 * @param m_email-->會員信箱
	 * @return List<MemberBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MemberBean> selectByConditions(String m_lastname, String m_firstname, String m_mobilephone,
			String m_email) {

		DetachedCriteria criteria = DetachedCriteria.forClass(MemberBean.class);

		if (m_lastname != null && !m_lastname.trim().isEmpty()) {
			criteria.add(Restrictions.like("m_lastname", "%" + m_lastname + "%"));
		}
		if (m_firstname != null && !m_firstname.trim().isEmpty()) {
			criteria.add(Restrictions.like("m_firstname", "%" + m_firstname + "%"));
		}
		if (m_mobilephone != null && !m_mobilephone.trim().isEmpty()) {
			criteria.add(Restrictions.eq("m_mobilephone", m_mobilephone));
		}
		if (m_email != null && !m_email.trim().isEmpty()) {
			criteria.add(Restrictions.like("m_email", "%" + m_email + "%"));
		}

		return (List<MemberBean>) hibernateTemplate.findByCriteria(criteria);
	}

	/**
	 * 新增會員
	 * 
	 * @param memberBean-->MemberBean
	 * @return memberBean-->MemberBean
	 */
	@Override
	public MemberBean insert(MemberBean memberBean) {

		hibernateTemplate.save(memberBean);

		return memberBean;
	}

	/**
	 * 修改會員資料
	 * 
	 * @param newMemberBean-->MemberBean
	 * @return memberBean-->MemberBean
	 */
	@Override
	public MemberBean update(MemberBean newMemberBean) {

		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, newMemberBean.getM_id());

		memberBean.setM_lastname(newMemberBean.getM_lastname());
		memberBean.setM_firstname(newMemberBean.getM_firstname());
		memberBean.setM_birth(newMemberBean.getM_birth());
		memberBean.setM_sex(newMemberBean.getM_sex());
		memberBean.setM_height(newMemberBean.getM_height());
		memberBean.setM_weight(newMemberBean.getM_weight());
		memberBean.setM_localphone(newMemberBean.getM_localphone());
		memberBean.setM_mobilephone(newMemberBean.getM_mobilephone());
		memberBean.setM_zipcode(newMemberBean.getM_zipcode());
		memberBean.setM_country(newMemberBean.getM_country());
		memberBean.setM_city(newMemberBean.getM_city());
		memberBean.setM_region(newMemberBean.getM_region());
		memberBean.setM_address(newMemberBean.getM_address());
		memberBean.setM_email(newMemberBean.getM_email());
		memberBean.setM_update_info_time(new java.util.Date());

		return memberBean;
	}

	/**
	 * 修改會員密碼
	 * 
	 * @param m_id-->會員流水號
	 * @param m_password_new_hashed-->新密碼(雜湊)
	 * @return memberBean-->MemberBean
	 */
	@Override
	public MemberBean updateM_password(Integer m_id, String m_password_new_hashed) {

		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		memberBean.setM_password(m_password_new_hashed);
		memberBean.setM_update_pass_time(new java.util.Date());

		return memberBean;
	}

	/**
	 * 更新登入次數
	 * 
	 * @param m_id-->會員流水號
	 * @return memberBean-->MemberBean
	 */
	@Override
	public MemberBean updateM_signin_number(Integer m_id) {

		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		Integer m_signin_number = hibernateTemplate.get(MemberBean.class, m_id).getM_signin_number();
		memberBean.setM_signin_number(m_signin_number + 1);

		return memberBean;
	}

	/**
	 * 更新登入IP
	 * 
	 * @param m_id-->會員流水號
	 * @return memberBean-->MemberBean
	 */
	@Override
	public MemberBean updateM_signin_ip(Integer m_id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 更新登入時間
	 * 
	 * @param m_id-->會員流水號
	 * @return memberBean-->MemberBean
	 */
	@Override
	public MemberBean updateM_signin_time(Integer m_id) {

		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		memberBean.setM_signin_time(new java.util.Date());

		return memberBean;
	}

}
