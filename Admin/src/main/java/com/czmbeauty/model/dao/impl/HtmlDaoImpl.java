/*
 * CaiZiMei
 * File: HtmlDaoImpl.java
 * Author: 詹晟
 * Date: 2017/10/23
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.HtmlDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.HtmlBean;

/**
 * html DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "htmlDao")
public class HtmlDaoImpl implements HtmlDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋特定類別的所有 html (分頁)
	 * 
	 * @param ht_ca_id
	 *            Integer --> 類別流水號
	 * @param first
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return List<HtmlBean>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<HtmlBean> selectPagination(Integer ht_ca_id, int first, int max) {

		// outer method
		List<HtmlBean> result = (List<HtmlBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {
						List<HtmlBean> list = session.createQuery(HQL_SELECT_HTML_BY_CATEGORY)
								.setParameter("ht_ca_id", ht_ca_id).setFirstResult(first).setMaxResults(max)
								.getResultList();
						return list;
					}
				});
		return result;
	}

	/**
	 * 搜尋特定類別的所有 html 筆數 (分頁)
	 * 
	 * @param ht_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	public int selectCountByHt_Ca(CategoryBean ht_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(HtmlBean.class);

		criteria.add(Restrictions.eq("ht_CategoryBean", ht_CategoryBean));

		return hibernateTemplate.findByCriteria(criteria).size();
	}

	/**
	 * html 流水號搜尋
	 * 
	 * @param ht_id
	 *            Integer --> html 流水號
	 * @return HtmlBean
	 */
	@Override
	public HtmlBean selectByHt_id(Integer ht_id) {

		return hibernateTemplate.get(HtmlBean.class, ht_id);
	}

	/**
	 * html 狀態搜尋
	 * 
	 * @param ht_ca_id
	 *            Integer --> 類別流水號
	 * @return List<HtmlBean>
	 * @return null
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<HtmlBean> selectByHt_status(Integer ht_ca_id) {

		List<HtmlBean> list = (List<HtmlBean>) hibernateTemplate.findByNamedParam(HQL_SELECT_OPEN_HTML, "ht_ca_id",
				ht_ca_id);

		return !list.isEmpty() ? list : null;
	}

	/**
	 * 新增 html
	 * 
	 * @param htmlBean
	 *            HtmlBean
	 * @return HtmlBean
	 */
	@Override
	public HtmlBean insert(HtmlBean htmlBean) {

		hibernateTemplate.save(htmlBean);

		return htmlBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param htmlBean
	 *            HtmlBean
	 * @return HtmlBean
	 */
	@Override
	public HtmlBean update(HtmlBean htmlBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(htmlBean);

		return htmlBean;
	}

}
