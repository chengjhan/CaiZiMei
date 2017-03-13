/*
 * CaiZiMei
 * File: MemberDAOImpl.java
 * Author: Cheng Jhan
 * Date: 2017/3/13
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

import com.caizimei.model.MemberBean;
import com.caizimei.model.dao.MemberDAO;

/** member DAO implement */
@Repository(value = "mamberDAO")
public class MemberDAOImpl implements MemberDAO {

	/** 注入 HibernateTemplate */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/** 查詢全部會員 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MemberBean> select() {
		return (List<MemberBean>) hibernateTemplate.find("from MemberBean");
	}

	/** 流水號查詢 */
	@Override
	public MemberBean selectByM_id(Integer m_id) {
		return hibernateTemplate.get(MemberBean.class, m_id);
	}

	/** 帳號查詢 */
	@Override
	@SuppressWarnings("unchecked")
	public MemberBean selectByM_account(String m_account) {
		List<MemberBean> list = (List<MemberBean>) hibernateTemplate.find("from MemberBean where m_account=?",
				m_account);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	/** 條件查詢 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MemberBean> selectByConditions(String m_firstname, String m_lastname, String m_telephone,
			String m_email) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MemberBean.class);
		if (m_firstname != null && !m_firstname.trim().isEmpty()) {
			criteria.add(Restrictions.like("m_firstname", "%" + m_firstname + "%"));
		}
		if (m_lastname != null && !m_lastname.trim().isEmpty()) {
			criteria.add(Restrictions.like("m_lastname", "%" + m_lastname + "%"));
		}
		if (m_telephone != null && !m_telephone.trim().isEmpty()) {
			criteria.add(Restrictions.eq("m_telephone", m_telephone));
		}
		if (m_email != null && !m_email.trim().isEmpty()) {
			criteria.add(Restrictions.like("m_email", "%" + m_email + "%"));
		}
		return (List<MemberBean>) hibernateTemplate.findByCriteria(criteria);
	}

	/** 新增會員 */
	@Override
	public MemberBean insert(MemberBean memberBean) {
		hibernateTemplate.save(memberBean);
		return memberBean;
	}

	/** 修改會員資料 */
	@Override
	public MemberBean update(MemberBean newMemberBean) {
		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, newMemberBean.getM_id());
		memberBean.setM_lastname(newMemberBean.getM_lastname());
		memberBean.setM_firstname(newMemberBean.getM_firstname());
		memberBean.setM_birth(newMemberBean.getM_birth());
		memberBean.setM_sex(newMemberBean.getM_sex());
		memberBean.setM_height(newMemberBean.getM_height());
		memberBean.setM_weight(newMemberBean.getM_weight());
		memberBean.setM_telephone(newMemberBean.getM_telephone());
		memberBean.setM_address(newMemberBean.getM_address());
		memberBean.setM_email(newMemberBean.getM_email());
		return memberBean;
	}

	/** 修改會員密碼 */
	@Override
	public MemberBean updateM_password(Integer m_id, String m_password_new_MD5) {
		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		memberBean.setM_password(m_password_new_MD5);
		return memberBean;
	}

	/** 修改登入時間 */
	@Override
	public MemberBean updateM_signin_time(Integer m_id) {
		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		memberBean.setM_signin_time(new java.util.Date());
		return memberBean;
	}

}
