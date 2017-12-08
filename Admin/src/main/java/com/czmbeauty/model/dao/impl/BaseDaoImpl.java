/*
 * CaiZiMei
 * File: BaseDaoImpl.java
 * Author: 詹晟
 * Date: 2017/12/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.czmbeauty.model.dao.BaseDao;
import com.czmbeauty.model.entity.BaseBean;

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
	 *            int --> 當頁起始筆數
	 * @param max
	 *            int --> 每頁最大筆數
	 * @return Map<String, Object>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> selectPagination(Integer ba_ca_id, int first, int max) {

		Map<String, Object> map = new HashMap<String, Object>();

		// outer method
		List<BaseBean> result = (List<BaseBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {

						// count
						map.put("count", session.createQuery(HQL_SELECT_BASE_BY_CATEGORY)
								.setParameter("ba_ca_id", ba_ca_id).getResultList().size());

						// list
						List<BaseBean> list = session.createQuery(HQL_SELECT_BASE_BY_CATEGORY)
								.setParameter("ba_ca_id", ba_ca_id).setFirstResult(first).setMaxResults(max)
								.getResultList();

						return list;
					}
				});
		map.put("list", result);

		return map;
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
