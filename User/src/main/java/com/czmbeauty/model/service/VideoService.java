/*
 * CaiZiMei/User
 * File: VideoService.java
 * Author: 詹晟
 * Date: 2017/9/8
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service;

import java.util.List;

import com.czmbeauty.model.entity.VideoBean;

/**
 * video service interface
 * 
 * @author 詹晟
 */
public interface VideoService {

	/**
	 * @see com.czmbeauty.model.service.impl.VideoServiceImpl#selectOpenVideo(String)
	 */
	List<VideoBean> selectOpenVideo(String ca_directory);

}
