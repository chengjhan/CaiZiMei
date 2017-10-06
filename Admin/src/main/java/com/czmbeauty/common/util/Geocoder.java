package com.czmbeauty.common.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

public class Geocoder {

	private static final Logger logger = Logger.getLogger(Geocoder.class);

	/**
	 * 地址轉換經緯度
	 * 
	 * @param address
	 *            String --> 地址
	 * @return return [緯度, 經度]
	 * @return null
	 */
	public static Double[] addressToLatLng(String address) throws Exception {

		logger.info("輸入地址: " + address);

		int responseCode = 0;
		String api = "http://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(address, "UTF-8")
				+ "&sensor=true";

		logger.info("URL: " + api);

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

				logger.info("緯度: " + latitude + ", 經度: " + longitude);

				return new Double[] { latitude, longitude };
				
			} else {

				throw new Exception("Error from the API - response status: " + status);
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Double latLng[] = Geocoder.addressToLatLng("台北市信義區信義路五段7號");
		System.out.println("Latitude: " + latLng[0] + ", Longitude: " + latLng[1]);
	}

}
