/*
 * CaiZiMei
 * File: AdminLogDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/13
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.AdminLogDao;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.AdminLogBean;
import com.czmbeauty.model.entity.AdminPathBean;

/**
 * admin_log DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminLogDao")
public class AdminLogDaoImpl implements AdminLogDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 條件搜尋 (分頁)
	 * 
	 * @param startDate
	 *            Date --> 開始日期
	 * @param endDate
	 *            Date --> 結束日期
	 * @param al_AdminBean
	 *            AdminBean
	 * @param al_AdminPathBean
	 *            AdminPathBean
	 * @param first
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectByConditions(Date startDate, Date endDate, AdminBean al_AdminBean,
			AdminPathBean al_AdminPathBean, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<AdminLogBean> result = (List<AdminLogBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
						CriteriaQuery<AdminLogBean> criteriaQuery = criteriaBuilder.createQuery(AdminLogBean.class);

						// from
						Root<AdminLogBean> root = criteriaQuery.from(AdminLogBean.class);

						// select
						criteriaQuery.select(root);

						// where
						List<Predicate> predicates = new ArrayList<Predicate>();
						if (startDate != null) {
							predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("al_insert_time"), startDate));
						}
						if (endDate != null) {
							predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("al_insert_time"), endDate));
						}
						if (al_AdminBean != null) {
							predicates.add(criteriaBuilder.equal(root.get("al_AdminBean"), al_AdminBean));
						}
						if (al_AdminPathBean != null) {
							predicates.add(criteriaBuilder.equal(root.get("al_AdminPathBean"), al_AdminPathBean));
						}
						if (!predicates.isEmpty()) {
							criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
						}

						// order by
						criteriaQuery.orderBy(criteriaBuilder.desc(root.get("al_insert_time")));

						TypedQuery<AdminLogBean> typedQuery = session.createQuery(criteriaQuery);

						// count
						map.put("count", typedQuery.getResultList().size());

						// limit
						typedQuery.setFirstResult(first);
						typedQuery.setMaxResults(max);

						return typedQuery.getResultList();
					}
				});
		map.put("list", result);

		return map;
	}

	/**
	 * 新增管理員日誌
	 * 
	 * @param adminLogBean
	 *            AdminLogBean
	 * @return AdminLogBean
	 */
	@Override
	public AdminLogBean insert(AdminLogBean adminLogBean) {

		hibernateTemplate.save(adminLogBean);

		return adminLogBean;
	}

}
