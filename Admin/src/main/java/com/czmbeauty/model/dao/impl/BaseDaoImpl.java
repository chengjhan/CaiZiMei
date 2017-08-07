/*
 * CaiZiMei
 * File: BaseDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/7
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	 * 注入 SessionFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋所有辦事處
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseBean> selectAllOffice() {

		return (List<BaseBean>) hibernateTemplate
				.find("from BaseBean where ba_ca_id=1 order by ba_status desc, ba_id asc");
	}

	/**
	 * 搜尋所有加盟店
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseBean> selectAllFranchisee() {

		return (List<BaseBean>) hibernateTemplate
				.find("from BaseBean where ba_ca_id=2 order by ba_status desc, ba_id asc");
	}

	/**
	 * 搜尋所有診所
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseBean> selectAllClinic() {

		return (List<BaseBean>) hibernateTemplate
				.find("from BaseBean where ba_ca_id=3 order by ba_status desc, ba_id asc");
	}

	/**
	 * 搜尋所有診所 (分頁)
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	public List<BaseBean> selectAllClinic(Integer first, Integer max) {

		CategoryBean categoryBean = new CategoryBean();
		categoryBean.setCa_id(3);

		EntityManager entityManager = sessionFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BaseBean> criteriaQuery = criteriaBuilder.createQuery(BaseBean.class);

		Root<BaseBean> root = criteriaQuery.from(BaseBean.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("ba_CategoryBean"), categoryBean));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("ba_status")), criteriaBuilder.asc(root.get("ba_id")));

		return entityManager.createQuery(criteriaQuery).setFirstResult(first).setMaxResults(max).getResultList();
	}

	/**
	 * 搜尋開啟的診所
	 * 
	 * @return List<BaseBean>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<BaseBean> selectOpenClinic() {

		return (List<BaseBean>) hibernateTemplate
				.find("from BaseBean where ba_ca_id=3 and ba_status=1 order by ba_id asc");
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
		baseBean.setBa_localphone(newBaseBean.getBa_localphone());
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
