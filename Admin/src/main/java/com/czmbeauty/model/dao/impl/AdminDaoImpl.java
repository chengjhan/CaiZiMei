/*
 * CaiZiMei
 * File: AdminDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/14
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.AdminDao;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.entity.BaseBean;

/**
 * admin DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "adminDao")
public class AdminDaoImpl implements AdminDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有管理員 (分頁)
	 * 
	 * @param first
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectPagination(int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<AdminBean> result = (List<AdminBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						// count
						map.put("count", session.createQuery(HQL_SELECT_ALL_ADMIN).getResultList().size());

						// list
						List<BaseBean> list = session.createQuery(HQL_SELECT_ALL_ADMIN).setFirstResult(first)
								.setMaxResults(max).getResultList();

						return list;
					}
				});
		map.put("list", result);

		return map;
	}

	/**
	 * 搜尋所有管理員
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdminBean> selectAll() {

		return (List<AdminBean>) hibernateTemplate.find(HQL_SELECT_ALL_ADMIN);
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param ad_id
	 *            Integer --> 管理員流水號
	 * @return AdminBean
	 */
	@Override
	public AdminBean selectByAd_id(Integer ad_id) {

		return hibernateTemplate.get(AdminBean.class, ad_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @param ad_status
	 *            Integer --> 管理員狀態
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_username(String ad_username, Integer ad_status) {

		DetachedCriteria criteria = DetachedCriteria.forClass(AdminBean.class);

		criteria.add(Restrictions.eq("ad_username", ad_username));

		if (ad_status != null) {
			criteria.add(Restrictions.eq("ad_status", ad_status));
		}

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate.findByCriteria(criteria);

		return !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * 管理員信箱搜尋
	 * 
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @param ad_status
	 *            Integer --> 管理員狀態
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_email(String ad_email, Integer ad_status) {

		DetachedCriteria criteria = DetachedCriteria.forClass(AdminBean.class);

		criteria.add(Restrictions.eq("ad_email", ad_email));

		if (ad_status != null) {
			criteria.add(Restrictions.eq("ad_status", ad_status));
		}

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate.findByCriteria(criteria);

		return !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * 管理員信箱搜尋 (edit) (AJAX)
	 * 
	 * @param ad_id
	 *            Integer --> 管理員流水號
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public AdminBean selectByAd_email(Integer ad_id, String ad_email) {

		String[] paramNames = { "ad_email", "ad_id" };
		Object[] values = { ad_email, ad_id };

		List<AdminBean> list = (List<AdminBean>) hibernateTemplate
				.findByNamedParam(HQL_SELECT_ADMIN_BY_EMAIL_EXCEPT_MYSELF, paramNames, values);

		return !list.isEmpty() ? list.get(0) : null;
	}

	/**
	 * 新增管理員
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	public AdminBean insert(AdminBean adminBean) {

		hibernateTemplate.save(adminBean);

		return adminBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	public AdminBean update(AdminBean adminBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(adminBean);

		return adminBean;
	}

}
