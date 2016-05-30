/**
 * 董再兴 RandomGUIDUtil.java 2013年7月3日
 */
package com.ttgis.education.utils;

/**
 * 产生唯一的随机字符串
 * @author 董再兴
 *
 */
public class RandomGUIDUtil {
	/**
	 * 产生唯一的随机字符串
	 * 
	 * @return
	 */
	public static String generateKey() {
		return new RandomGUID(true).toString();
	}
}