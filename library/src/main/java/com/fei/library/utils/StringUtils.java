package com.fei.library.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String method 2014.12.16
 * 
 * @author whiskeyfei
 * 
 */

public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * 
	 * @param strs
	 * @return true - 全为空， false - 有一个不为空
	 */
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
	
	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * @param strs
	 * @return
	 */
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

	/**
	 * 检查是否是邮箱地址
	 * 
	 * @param mail
	 * @return
	 */
	public static boolean isMailAddress(String mail) {
		if (!isEmpty(mail)) {
			Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
			Matcher m = p.matcher(mail);
			return m.matches();
		}
		return false;
	}

}
