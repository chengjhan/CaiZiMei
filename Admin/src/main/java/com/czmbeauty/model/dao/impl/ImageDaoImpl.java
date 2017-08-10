/*
 * CaiZiMei
 * File: ImageDaoImpl.java
 * Author: 詹晟
 * Date: 2017/8/10
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

import com.czmbeauty.model.dao.ImageDao;
import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.ImageBean;

/**
 * image DAO implement
 * 
 * @author 詹晟
 */
@Repository(value = "imageDao")
public class ImageDaoImpl implements ImageDao {

	/**
	 * 注入 HibernateTemplate
	 */
	@Autowired
	private HibernateTemplate hibernateTemplate;

	/**
	 * 搜尋特定類別的所有圖片 (分頁)
	 * 
	 * @param hql
	 *            String
	 * @param first
	 *            int --> 起始筆數
	 * @param max
	 *            int --> 最大筆數
	 * @return List<ImageBean>
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<ImageBean> selectAllImagePagination(String hql, int first, int max) {

		// outer method
		List<ImageBean> result = (List<ImageBean>) hibernateTemplate.execute(

				// inner class
				new HibernateCallback() {

					// inner method
					public Object doInHibernate(Session session) throws HibernateException {
						List<ImageBean> list = session.createQuery(hql).setFirstResult(first).setMaxResults(max)
								.getResultList();
						return list;
					}
				});
		return result;
	}

	/**
	 * 搜尋特定類別的所有圖片筆數 (分頁)
	 * 
	 * @param im_CategoryBean
	 *            CategoryBean
	 * @return int
	 */
	@Override
	public int selectAllImageCount(CategoryBean im_CategoryBean) {

		DetachedCriteria criteria = DetachedCriteria.forClass(ImageBean.class);

		criteria.add(Restrictions.eq("im_CategoryBean", im_CategoryBean));

		return hibernateTemplate.findByCriteria(criteria).size();
	}

	/**
	 * 圖片流水號搜尋
	 * 
	 * @param im_id
	 *            Integer --> 圖片流水號
	 * @return ImageBean
	 */
	@Override
	public ImageBean selectByIm_id(Integer im_id) {

		return hibernateTemplate.get(ImageBean.class, im_id);
	}

	/**
	 * 新增圖片
	 * 
	 * @param imageBean
	 *            ImageBean
	 * @return ImageBean
	 */
	@Override
	public ImageBean insert(ImageBean imageBean) {

		hibernateTemplate.save(imageBean);

		return imageBean;
	}

	/**
	 * 修改資料
	 * 
	 * @param imageBean
	 *            ImageBean
	 * @return ImageBean
	 */
	@Override
	public ImageBean update(ImageBean imageBean) {

		hibernateTemplate.clear();
		hibernateTemplate.update(imageBean);

		return imageBean;
	}

}
