/*
 * CaiZiMei
 * File: FranchiseeServiceImpl.java
 * Author: 詹晟
 * Date: 2017/7/19
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

import com.czmbeauty.model.dao.FranchiseeDao;
import com.czmbeauty.model.entity.FranchiseeBean;
import com.czmbeauty.model.service.FranchiseeService;

/**
 * franchisee service implement
 * 
 * @author 詹晟
 */
@Service(value = "franchiseeService")
public class FranchiseeServiceImpl implements FranchiseeService {

	/**
	 * 注入 FranchiseeDao
	 */
	@Autowired
	private FranchiseeDao franchiseeDao;

	/**
	 * 搜尋所有加盟店
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FranchiseeBean> selectAll() {

		return franchiseeDao.selectAll();
	}

	/**
	 * 加盟店流水號搜尋
	 * 
	 * @param fr_id-->加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional(readOnly = true)
	public FranchiseeBean selectByFr_id(Integer fr_id) {

		return franchiseeDao.selectByFr_id(fr_id);
	}

	/**
	 * 狀態搜尋
	 * 
	 * @return List<FranchiseeBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<FranchiseeBean> selectByFr_status() {

		return franchiseeDao.selectByFr_status();
	}

	/**
	 * 新增加盟店
	 * 
	 * @param franchiseeBean-->FranchiseeBean
	 * @return result-->FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean insert(FranchiseeBean franchiseeBean) {

		FranchiseeBean result = null;

		if (franchiseeBean != null) {

			result = franchiseeDao.insert(franchiseeBean);
		}
		return result;
	}

	/**
	 * 修改資料
	 * 
	 * @param franchiseeBean-->FranchiseeBean
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean update(FranchiseeBean franchiseeBean) {

		return franchiseeDao.update(franchiseeBean);
	}

	/**
	 * 切換狀態
	 * 
	 * @param fr_id-->加盟店流水號
	 * @return FranchiseeBean
	 */
	@Override
	@Transactional
	public FranchiseeBean updateFr_status(Integer fr_id) {

		return franchiseeDao.updateFr_status(fr_id);
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
		FranchiseeService franchiseeService = new FranchiseeServiceImpl();
		Double latLng[] = franchiseeService.addressToLatLng("台北市信義區信義路五段7號");
		System.out.println("Latitude: " + latLng[0] + " and Longitude: " + latLng[1]);
	}

}
