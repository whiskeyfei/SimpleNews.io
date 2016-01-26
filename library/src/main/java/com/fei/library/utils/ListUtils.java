package com.fei.library.utils;

import android.util.SparseArray;

import java.util.List;
import java.util.Map;

/**
 * ListUtils 2014.12.16
 * 
 * @author whiskeyfei
 * 
 */
public class ListUtils {
	/**
	 * check list is null
	 * 
	 * @param list
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		return (list == null) || list.isEmpty();
	}

	/**
	 * check map is null
	 * 
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return (map == null) || map.isEmpty();
	}
	
	public static boolean isEmpty(SparseArray<?> map){
        return (map == null) || (map.size() == 0);
	}

	/**
	 * get list count
	 * 
	 * @param list
	 * @return
	 */
	public static int getCount(List<?> list) {
		return isEmpty(list) ? 0 : list.size();
	}
}