/*
 * CaiZiMei
 * File: YoutubeDao.java
 * Author: 詹晟
 * Date: 2017/8/22
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;

import com.czmbeauty.model.entity.CategoryBean;
import com.czmbeauty.model.entity.YoutubeBean;

/**
 * youtube DAO interface
 * 
 * @author 詹晟
 */
public interface YoutubeDao {

	List<YoutubeBean> selectPagination(String hql, int first, int max);

	int selectCountByYo_Ca(CategoryBean yo_CategoryBean);

	YoutubeBean selectByYo_id(Integer yo_id);

	YoutubeBean insert(YoutubeBean youtubeBean);

	YoutubeBean update(YoutubeBean youtubeBean);

}
