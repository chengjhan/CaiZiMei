/*
 * CaiZiMei
 * File: BaseDaoImpl.java
 * Author: 詹晟
 * Date: 2017/9/25
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

import com.czmbeauty.model.dao.BaseDao;
import com.czmbeauty.model.entity.BaseBean;
import com.czmbeauty.model.entity.CategoryBean;

/**
 * base DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "baseDao")
public class BaseDaoImpl implements BaseDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋特定類別的所有據點 (分頁)
	 * 
	 * @param ba_ca_id
	 *            Integer --> 類別流水號
	 * @param first
	 *            int --> 起始筆數
	 * @param max
	 *            int --> 最大筆數
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<BaseBean> selectPagination(Integer ba_ca_id, int first, int max) {

		// outer method
		List<BaseBean> result = (List<BaseBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {
						List<BaseBean> list = session.createQuery(HQL_SELECT_BASE_BY_CATEGORY)
								.setParameter("ba_ca_id", ba_ca_id).setFirstResult(first).setMaxResults(max)
								.getResultList();
						return list;
					}
				});
		return result;
	}

	/**
	 * 搜尋特定類別的所有據點筆數 (分頁)
	 * 
	 * @param ba_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	public int selectCountByBa_Ca(CategoryBean ba_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(BaseBean.class);

		criteria.add(Restrictions.eq("ba_CategoryBean", ba_CategoryBean));

		return hibernateTemplate.findByCriteria(criteria).size();
	}

	/**
	 * 據點流水號搜尋
	 * 
	 * @param ba_id
	 *            Integer --> 據點流水號
	 * @return BaseBean
	 */
	@Override
	public BaseBean selectByBa_id(Integer ba_id) {

		return hibernateTemplate.get(BaseBean.class, ba_id);
	}

	/**
	 * 新增據點
	 * 
	 * @param baseBean
	 *            BaseBean
	 * @return BaseBean
	 */
	@Override
	public BaseBean insert(BaseBean baseBean) {

		hibernateTemplate.save(baseBean);

		return baseBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param newBaseBean
	 *            BaseBean
	 * @return BaseBean
	 */
	@Override
	public BaseBean update(BaseBean newBaseBean) {

		BaseBean baseBean = hibernateTemplate.get(BaseBean.class, newBaseBean.getBa_id());

		baseBean.setBa_name(newBaseBean.getBa_name());
		baseBean.setBa_eng_name(newBaseBean.getBa_eng_name());
		baseBean.setBa_tel_code(newBaseBean.getBa_tel_code());
		baseBean.setBa_tel(newBaseBean.getBa_tel());
		baseBean.setBa_CountryBean(newBaseBean.getBa_CountryBean());
		baseBean.setBa_StateBean(newBaseBean.getBa_StateBean());
		baseBean.setBa_CityBean(newBaseBean.getBa_CityBean());
		baseBean.setBa_address(newBaseBean.getBa_address());
		baseBean.setBa_latitude(newBaseBean.getBa_latitude());
		baseBean.setBa_longitude(newBaseBean.getBa_longitude());
		baseBean.setBa_url(newBaseBean.getBa_url());
		baseBean.setBa_update_time(newBaseBean.getBa_update_time());

		return baseBean;
	}

}
