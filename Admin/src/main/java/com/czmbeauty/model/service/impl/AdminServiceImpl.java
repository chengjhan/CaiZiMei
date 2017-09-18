/*
 * CaiZiMei
 * File: AdminServiceImpl.java
 * Author: 詹晟
 * Date: 2017/9/18
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czmbeauty.common.mail.SendMail;
import com.czmbeauty.common.util.CryptographicHashFunction;
import com.czmbeauty.model.dao.AdminDao;
import com.czmbeauty.model.entity.AdminBean;
import com.czmbeauty.model.service.AdminService;

/**
 * admin service implement
 * 
 * @author 詹晟
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

	/**
	 * 注入 AdminDao
	 */
	@Autowired
	private AdminDao adminDao;

	/**
	 * 注入 SendMail
	 */
	@Autowired
	private SendMail sendMail;

	/**
	 * 註冊
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean signUp(AdminBean adminBean) {

		AdminBean result = null;

		if (adminBean != null) {

			String ad_salt = CryptographicHashFunction.getSalt();

			adminBean.setAd_salt(ad_salt);
			adminBean.setAd_password(CryptographicHashFunction.getHashedPassword(adminBean.getAd_password(), ad_salt));
			adminBean.setAd_signup_time(new java.util.Date());
			adminBean.setAd_signin_number(0);
			adminBean.setAd_update_pwd_time(new java.util.Date());
			adminBean.setAd_update_info_time(new java.util.Date());
			adminBean.setAd_status(1);
			adminBean.setAd_status_time(new java.util.Date());

			result = adminDao.insert(adminBean);
		}
		return result;
	}

	/**
	 * 登入
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @param ad_password
	 *            String --> 管理員密碼(原碼)
	 * @return AdminBean
	 * @return null
	 * @return null
	 */
	@Override
	@Transactional
	public AdminBean signIn(String ad_username, String ad_password) {

		AdminBean adminBean = adminDao.selectByAd_username(ad_username);

		if (adminBean != null) {

			String ad_salt = adminBean.getAd_salt();

			if (CryptographicHashFunction.getHashedPassword(ad_password, ad_salt).equals(adminBean.getAd_password())) {

				// 帳號及密碼正確
				return adminBean;

			} else {

				// 密碼錯誤
				return null;
			}
		} else {

			// 帳號錯誤
			return null;
		}
	}

	/**
	 * 搜尋所有管理員
	 * 
	 * @return List<AdminBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<AdminBean> selectAll() {

		return adminDao.selectAll();
	}

	/**
	 * 管理員流水號搜尋
	 * 
	 * @param ad_id
	 *            Integer --> 管理員流水號
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_id(Integer ad_id) {

		return adminDao.selectByAd_id(ad_id);
	}

	/**
	 * 管理員帳號搜尋
	 * 
	 * @param ad_username
	 *            String --> 管理員帳號
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_username(String ad_username) {

		return adminDao.selectByAd_username(ad_username);
	}

	/**
	 * 管理員信箱搜尋
	 * 
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_email(String ad_email) {

		return adminDao.selectByAd_email(ad_email);
	}

	/**
	 * 管理員信箱搜尋 (edit) (AJAX)
	 * 
	 * @param ad_id
	 *            Integer --> 管理員流水號
	 * @param ad_email
	 *            String --> 管理員信箱
	 * @return AdminBean
	 */
	@Override
	@Transactional(readOnly = true)
	public AdminBean selectByAd_email(Integer ad_id, String ad_email) {

		return adminDao.selectByAd_email(ad_id, ad_email);
	}

	/**
	 * 修改資料
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean update(AdminBean adminBean) {

		adminBean.setAd_update_info_time(new java.util.Date());

		return adminDao.update(adminBean);
	}

	/**
	 * 修改密碼
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @param ad_password_old
	 *            String --> 舊密碼(原碼)
	 * @param ad_password_new
	 *            String --> 新密碼(原碼)
	 * @return AdminBean
	 * @return null
	 */
	@Override
	@Transactional
	public AdminBean updateAd_password(AdminBean adminBean, String ad_password_old, String ad_password_new) {

		String ad_salt = adminBean.getAd_salt();
		String oldHashedPassword = adminBean.getAd_password();
		String inputOldHashedPassword = CryptographicHashFunction.getHashedPassword(ad_password_old, ad_salt);

		if (oldHashedPassword.equals(inputOldHashedPassword)) {

			String newHashedPassword = CryptographicHashFunction.getHashedPassword(ad_password_new, ad_salt);

			adminBean.setAd_password(newHashedPassword);
			adminBean.setAd_update_pwd_time(new java.util.Date());

			// 變更成功
			return adminDao.update(adminBean);

		} else {

			// 變更失敗
			return null;
		}
	}

	/**
	 * 忘記密碼
	 * 
	 * @param adminBean
	 *            AdminBean
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean updateAd_password(AdminBean adminBean) {

		int random = (int) (Math.random() * 1000000);
		String ad_password_random = String.format("%06d", random);

		String ad_salt = adminBean.getAd_salt();
		String newHashedPassword = CryptographicHashFunction.getHashedPassword(ad_password_random, ad_salt);

		adminBean.setAd_password(newHashedPassword);
		adminBean.setAd_update_pwd_time(new java.util.Date());

		sendMail.forgetPasswordMail(adminBean, ad_password_random);

		return adminBean;
	}

	/**
	 * 切換狀態
	 * 
	 * @param ad_id
	 *            Integer --> 管理員流水號
	 * @return AdminBean
	 */
	@Override
	@Transactional
	public AdminBean updateAd_status(Integer ad_id) {

		// 在同一個 Session 中利用 get() 取出資料為持久化狀態 (Persistent)，物件的內容更新將直接反應至資料庫
		AdminBean adminBean = adminDao.selectByAd_id(ad_id);

		if (adminBean.getAd_status() == 1) {

			// 關閉帳號
			adminBean.setAd_status(0);
			adminBean.setAd_status_time(new java.util.Date());

		} else {

			// 開啟帳號
			adminBean.setAd_status(1);
			adminBean.setAd_status_time(new java.util.Date());
		}
		return adminBean;
	}

}
