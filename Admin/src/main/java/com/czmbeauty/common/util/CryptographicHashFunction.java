package com.czmbeauty.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class CryptographicHashFunction {

	/**
	 * 產生 salt
	 * 
	 * @return 隨機UUID字串
	 */
	public static String getSalt() {

		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	/**
	 * 製造雜湊密碼
	 * 
	 * @param password
	 *            String --> 密碼(原碼)
	 * @param salt
	 *            String --> 塩
	 * @return MD5雜湊密碼
	 */
	public static String getHashedPassword(String password, String salt) {

		return getMD5(salt.replaceAll("-", getMD5(password)));
	}

	/**
	 * 轉換為 MD5
	 * 
	 * @param str
	 *            String --> 原始字串
	 * @return str_MD5
	 */
	public static String getMD5(String str) {

		String str_MD5 = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] str_bytes = str.getBytes();
			md.update(str_bytes);
			byte[] digestBytes = md.digest();
			BigInteger digestInt = new BigInteger(1, digestBytes);
			str_MD5 = digestInt.toString(16);
			while (str_MD5.length() < 32) {
				str_MD5 = "0" + str_MD5;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return str_MD5;
	}

}
