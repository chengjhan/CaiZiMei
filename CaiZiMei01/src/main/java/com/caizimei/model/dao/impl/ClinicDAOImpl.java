package com.caizimei.model.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.caizimei.model.ClinicBean;
import com.caizimei.model.dao.ClinicDAO;

@Repository(value = "clinicDAO")
public class ClinicDAOImpl implements ClinicDAO {

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> select() {
		return (List<ClinicBean>) hibernateTemplate.find("from ClinicBean");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ClinicBean> selectByConditions(String c_name, String c_telephone) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ClinicBean.class);
		if (c_name != null && !c_name.trim().isEmpty()) {
			criteria.add(Restrictions.like("c_name", "%" + c_name + "%"));
		}
		if (c_telephone != null && !c_telephone.trim().isEmpty()) {
			criteria.add(Restrictions.like("c_telephone", "%" + c_telephone + "%"));
		}
		return (List<ClinicBean>) hibernateTemplate.findByCriteria(criteria);
	}

	@Override
	public ClinicBean insert(ClinicBean clinicBean) {
		hibernateTemplate.save(clinicBean);
		return clinicBean;
	}

	@Override
	public ClinicBean update(ClinicBean clinicBean) {
		hibernateTemplate.clear();
		hibernateTemplate.update(clinicBean);
		return clinicBean;
	}

	@Override
	public Boolean delete(Integer c_id) {
		hibernateTemplate.delete(hibernateTemplate.get(ClinicBean.class, c_id));
		return true;
	}

}
