package com.lauren.simplenews.utils;

/**
 * String method 2014.12.16
 * 
 * @author whiskeyfei
 * 
 */

public class StringUtils {
	public static boolean isEmpty(String... strs) {
		if (strs == null) {
			return true;
		}

		for (String str : strs) {
			if ((str != null) && !str.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean hasEmpty(String strs) {
		if (isEmpty(strs)) {
			return true;
		}

		for (int i = 0; i < strs.length(); i++) {
			char c = strs.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

}
