/*
 * CaiZiMei
 * File: ClinicServiceImpl.java
 * Author: 詹晟
 * Date: 2017/3/26
 * Version: 1.0
 * Since: JDK 1.8
 */
package com.caizimei.model.service.impl;

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

import com.caizimei.model.dao.CityDAO;
import com.caizimei.model.dao.ClinicDAO;
import com.caizimei.model.entity.ClinicBean;
import com.caizimei.model.service.ClinicService;

/**
 * clinic service implement
 * 
 * @author 詹晟
 */
@Service(value = "clinicService")
@Transactional
public class ClinicServiceImpl implements ClinicService {

	/**
	 * 注入 ClinicDAO
	 */
	@Autowired
	private ClinicDAO clinicDAO;

	/**
	 * 注入 CityDAO
	 */
	@Autowired
	private CityDAO cityDAO;

	/**
	 * 搜尋全部診所
	 * 
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> select() {

		return clinicDAO.select();
	}

	/**
	 * 城市名搜尋
	 * 
	 * @param ci_name-->城市名
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectByCi_name(String ci_name) {

		return clinicDAO.selectByC_ci_id(cityDAO.selectByCi_name(ci_name).get(0).getCi_id());
	}

	/**
	 * 診所名搜尋
	 * 
	 * @param c_name-->診所名
	 * @return result-->ClinicBean
	 */
	@Override
	@Transactional(readOnly = true)
	public ClinicBean selectByC_name(String c_name) {

		ClinicBean result = null;

		if (c_name != null) {

			result = clinicDAO.selectByC_name(c_name).get(0);
		}
		return result;
	}

	/**
	 * 條件搜尋
	 * 
	 * @param c_name-->診所名
	 * @param c_telephone-->診所電話
	 * @return List<ClinicBean>
	 */
	@Override
	@Transactional(readOnly = true)
	public List<ClinicBean> selectByConditions(String c_name, String c_telephone) {

		return clinicDAO.selectByConditions(c_name, c_telephone);
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

			result = clinicDAO.insert(clinicBean);
		}
		return result;
	}

	/**
	 * 修改診所資料
	 * 
	 * @param clinicBean-->ClinicBean
	 * @return ClinicBean
	 */
	@Override
	@Transactional
	public ClinicBean update(ClinicBean clinicBean) {

		return clinicDAO.update(clinicBean);
	}

	/**
	 * 刪除診所
	 * 
	 * @param c_id-->診所流水號
	 * @return true-->成功
	 */
	@Override
	@Transactional
	public Boolean delete(Integer c_id) {

		return clinicDAO.delete(c_id);
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
		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";
		System.out.println("URL : " + api);
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
				return new Double[] { latitude, longitude };
			} else {
				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		ClinicService clinicService = new ClinicServiceImpl();
		Double latLng[] = clinicService.addressToLatLng("台北市大安區信義路四段339號2樓");
		System.out.println("Latitude: " + latLng[0] + " and Longitude: " + latLng[1]);
	}

}
