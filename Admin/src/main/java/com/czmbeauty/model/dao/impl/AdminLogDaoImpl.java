/*
 * CaiZiMei
 * File: AdminLogDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/8
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
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
	 * @param adminBean
	 *            AdminBean
	 * @param adminPathBean
	 *            AdminPathBean
	 * @param first
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectByConditions(Date startDate, Date endDate, AdminBean adminBean,
			AdminPathBean adminPathBean, int first, int max) {

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
						if (adminBean != null) {
							predicates.add(criteriaBuilder.equal(root.get("al_AdminBean"), adminBean));
						}
						if (adminPathBean != null) {
							predicates.add(criteriaBuilder.equal(root.get("al_AdminPathBean"), adminPathBean));
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
	 * 條件搜尋筆數 (分頁)
	 * 
	 * @param startDate
	 *            Date --> 開始日期
	 * @param endDate
	 *            Date --> 結束日期
	 * @param adminBean
	 *            AdminBean
	 * @param adminPathBean
	 *            AdminPathBean
	 * @return int
	 */
	@Override
	public int selectCount(Date startDate, Date endDate, AdminBean adminBean, AdminPathBean adminPathBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(AdminLogBean.class);

		if (startDate != null) {
			criteria.add(Restrictions.ge("al_insert_time", startDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("al_insert_time", endDate));
		}
		if (adminBean != null) {
			criteria.add(Restrictions.eq("al_AdminBean", adminBean));
		}
		if (adminPathBean != null) {
			criteria.add(Restrictions.eq("al_AdminPathBean", adminPathBean));
		}

		return hibernateTemplate.findByCriteria(criteria).size();
	}

	/**
	 * 條件搜尋
	 * 
	 * @param startDate
	 *            Date --> 開始日期
	 * @param endDate
	 *            Date --> 結束日期
	 * @param adminBean
	 *            AdminBean
	 * @param adminPathBean
	 *            AdminPathBean
	 * @return List<AdminLogBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminLogBean> selectByConditions(Date startDate, Date endDate, AdminBean adminBean,
			AdminPathBean adminPathBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(AdminLogBean.class);

		if (startDate != null) {
			criteria.add(Restrictions.ge("al_insert_time", startDate));
		}
		if (endDate != null) {
			criteria.add(Restrictions.le("al_insert_time", endDate));
		}
		if (adminBean != null) {
			criteria.add(Restrictions.eq("al_AdminBean", adminBean));
		}
		if (adminPathBean != null) {
			criteria.add(Restrictions.eq("al_AdminPathBean", adminPathBean));
		}
		criteria.addOrder(Order.desc("al_insert_time"));

		return (List<AdminLogBean>) hibernateTemplate.findByCriteria(criteria);
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
