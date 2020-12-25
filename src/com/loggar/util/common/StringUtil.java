package com.loggar.util.common;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtil {
	public static final String SYSTEM_LINE_SEPARATER = System.getProperty("line.separator");
	private static final String default_array_delimiter = ",";

	private StringUtil() {
		throw new AssertionError();
	}

	public static String toString(String[] arr_s) {
		return toString(arr_s, ",");
	}

	public static String toString(String[] arr_s, String delimiter) {
		if (isEmpty(delimiter))
			delimiter = default_array_delimiter;

		StringBuffer sb = new StringBuffer();
		sb.setLength(0);

		int length = arr_s.length;

		for (int i = 0; i < length; i++) {
			sb.append(arr_s[i]).append(delimiter);
		}

		String result = sb.toString();

		return result.substring(0, result.length() - delimiter.length());
	}

	public static boolean isContained(String target, String[] arr) {
		boolean result = false;

		for (String s : arr) {
			if (target.equals(s)) {
				result = true;
				break;
			}
		}

		return result;
	}

	public static boolean isEmpty(String target) {
		if (target == null)
			return true;
		if (target.trim().length() == 0)
			return true;
		return false;
	}

	public static boolean isEmpty(Object t) {
		String target;
		try {
			target = (String) t;
		} catch (Exception e) {
			return true;
		}
		if (target == null)
			return true;
		if (target.trim().length() == 0)
			return true;
		return false;
	}

	public static boolean isNotEmpty(String target) {
		if (target == null)
			return false;
		if (target.trim().length() < 1)
			return false;
		return true;
	}

	public static String getEmptyIfNull(String s) {
		if (s == null) {
			return "";
		}

		return s;
	}

	public static boolean isEmptyOrStrNull(String s) {
		if (isEmpty(s))
			return true;
		if ("null".equals(s.trim().toLowerCase()))
			return true;
		return false;
	}

	public static <K, V> String debugMap(Map<K, V> map) {
		if (map == null || map.size() < 1) {
			return "{}";
		}

		Set<K> keySet = map.keySet();

		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (K key : keySet) {
			sb.append(key).append("=").append(map.get(key)).append(", ");
		}

		String result = sb.toString();
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2) + "}";
		}

		return "{}";
	}

	public static <T> String debugList(List<T> list) {
		if (list == null || list.size() < 1) {
			return "[]";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (T e : list) {
			sb.append(e.toString());
		}

		String result = sb.toString();
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2) + "]";
		}

		return "[]";
	}

	public static <T> String debugList(List<T> list, boolean newLine) {
		if (list == null || list.size() < 1) {
			return "[]";
		}

		StringBuffer sb = new StringBuffer();
		sb.append("[");

		for (T e : list) {
			sb.append(e.toString());
			if (newLine)
				sb.append(SYSTEM_LINE_SEPARATER);
		}

		String result = sb.toString();
		if (result.length() > 2) {
			return result.substring(0, result.length() - 2) + "]";
		}

		return "[]";
	}

	public static String join(String delimiter, String[] a) {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		for (String s : a) {
			if (sb.length() > 0)
				sb.append(delimiter);
			sb.append(s);
		}
		return sb.toString();
	}

	public static String join(String delimiter, List<String> a) {
		StringBuilder sb = new StringBuilder();
		sb.setLength(0);
		for (String s : a) {
			if (sb.length() > 0)
				sb.append(delimiter);
			sb.append(s);
		}
		return sb.toString();
	}

	public static char charLowerCase(char c) {
		if (c >= 'A' && c <= 'Z') {
			return (char) (c + 32);
		}

		return c;
	}

	public static char charUpperCase(char c) {
		if (c >= 'a' && c <= 'z') {
			return (char) (c - 32);
		}

		return c;
	}
}
