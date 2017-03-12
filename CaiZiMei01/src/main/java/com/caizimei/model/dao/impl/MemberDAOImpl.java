package com.caizimei.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.MemberBean;
import com.caizimei.model.dao.MemberDAO;

@Repository(value = "mamberDAO")
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<MemberBean> select() {
		return (List<MemberBean>) hibernateTemplate.find("from MemberBean");
	}

	@Override
	public MemberBean selectByM_id(Integer m_id) {
		return hibernateTemplate.get(MemberBean.class, m_id);
	}

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

	@Override
	public MemberBean insert(MemberBean memberBean) {
		hibernateTemplate.save(memberBean);
		return memberBean;
	}

	@Override
	public MemberBean update(MemberBean memberBean) {
		hibernateTemplate.clear();
		hibernateTemplate.update(memberBean);
		return memberBean;
	}

	@Override
	public MemberBean updateM_password(Integer m_id, String m_password) {
		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		memberBean.setM_password(m_password);
		return memberBean;
	}

	@Override
	public MemberBean updateM_signin_time(Integer m_id) {
		MemberBean memberBean = hibernateTemplate.get(MemberBean.class, m_id);
		memberBean.setM_signin_time(new java.util.Date());
		return memberBean;
	}

}
