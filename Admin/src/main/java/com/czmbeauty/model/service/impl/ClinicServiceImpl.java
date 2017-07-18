/*
 * CaiZiMei
 * File: ClinicServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/17
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.czmbeauty.model.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;

import com.czmbeauty.model.dao.ClinicDao;
import com.czmbeauty.model.entity.ClinicBean;
import com.czmbeauty.model.service.ClinicService;

/**
 * clinic service implement
 * 
 * @author 詹晟
 */
@Service(value = "clinicService")
public class ClinicServiceImpl implements ClinicService {

	/**
	 * 注入 ClinicDao
	 */
	@Autowired
	private ClinicDao clinicDao;

	/**
	 * 搜尋所有診所
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectAll() {

		return clinicDao.selectAll();
	}

	/**
	 * 診所流水號搜尋
	 * 
	 * @param cl_id-->診所流水號
	 * @return ClinicBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ClinicBean selectByCl_id(Integer cl_id) {

		return clinicDao.selectByCl_id(cl_id);
	}

	/**
	 * 城市流水號搜尋可顯示的診所
	 * 
	 * @param cl_ci_id-->城市流水號
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectByCl_ci_id(Integer cl_ci_id) {

		return clinicDao.selectByCl_ci_id(cl_ci_id);
	}

	/**
	 * 新增診所
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return result-->ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean insert(ClinicBean clinicBean) {

		ClinicBean result = null;

		if (clinicBean != null) {

			result = clinicDao.insert(clinicBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean update(ClinicBean clinicBean) {

		return clinicDao.update(clinicBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param cl_id-->診所流水號
	 * @return ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean updateCl_status(Integer cl_id) {

		return clinicDao.updateCl_status(cl_id);
	}

	/**
	 * 地址轉換經緯度
	 * 
	 * @param address-->地址
	 * @return return [緯度, 經度]
	 * @return null
	 */
	@Override
	public Double[] addressToLatLng(String address) throws Exception {
		System.out.println("Address: " + address);
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";
		System.out.println("URL: " + api);
		URL url = new URL(api);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		httpConnection.connect();
		responseCode = httpConnection.getResponseCode();
		if (responseCode == 200) {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(httpConnection.getInputStream());
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile("/GeocodeResponse/status");
			String status = (String) expr.evaluate(document, XPathConstants.STRING);
			if (status.equals("OK")) {
				expr = xpath.compile("//geometry/location/lat");
				String str_latitude = (String) expr.evaluate(document, XPathConstants.STRING);
				double latitude = 0;
				if (str_latitude != null && str_latitude.length() != 0) {
					try {
						latitude = Double.parseDouble(str_latitude);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				expr = xpath.compile("//geometry/location/lng");
				String str_longitude = (String) expr.evaluate(document, XPathConstants.STRING);
				double longitude = 0;
				if (str_longitude != null && str_longitude.length() != 0) {
					try {
						longitude = Double.parseDouble(str_longitude);
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				System.out.println("Latitude: " + latitude + " and Longitude: " + longitude);
				return new Double[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		ClinicService clinicService = new ClinicServiceImpl();
		Double latLng[] = clinicService.addressToLatLng("台北市信義區信義路五段7號");
		System.out.println("Latitude: " + latLng[0] + " and Longitude: " + latLng[1]);
	}

}
