/*
 * CaiZiMei
 * File: VideoDao.java
 * Author: 詹晟
 * Date: 2017/12/15
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.dao;

import java.util.List;
import java.util.Map;

import com.czmbeauty.common.constants.HqlConstants;
import com.czmbeauty.model.entity.VideoBean;

/**
 * video DAO interface
 * 
 * @author 詹晟
 */
public interface VideoDao extends HqlConstants {

	/**
	 * @see com.czmbeauty.model.dao.impl.VideoDaoImpl#selectPagination(Integer, int,
	 *      int)
	 */
	Map<String, Object> selectPagination(Integer vi_ca_id, int first, int max);

	/**
	 * @see com.czmbeauty.model.dao.impl.VideoDaoImpl#selectByVi_id(Integer)
	 */
	VideoBean selectByVi_id(Integer vi_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.VideoDaoImpl#selectByVi_status(Integer)
	 */
	List<VideoBean> selectByVi_status(Integer vi_ca_id);

	/**
	 * @see com.czmbeauty.model.dao.impl.VideoDaoImpl#insert(VideoBean)
	 */
	VideoBean insert(VideoBean videoBean);

	/**
	 * @see com.czmbeauty.model.dao.impl.VideoDaoImpl#update(VideoBean)
	 */
	VideoBean update(VideoBean videoBean);

}
