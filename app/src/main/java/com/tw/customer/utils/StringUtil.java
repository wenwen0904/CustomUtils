package com.tw.customer.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 字符串工具
 *
 * @author: wenwen
 * @Date: 2016/3/21 18:34
 * @version: 1.0
 */
public class StringUtil {

	public static boolean isEmpty(CharSequence str) {
		return TextUtils.isEmpty(str);
	}

	/**
	 * 将null转换成指定字符，不为null原字符串返回
	 *
	 * @param s
	 * @param defalt
	 * @return
	 */
	public static String getUnNullString(String s, String defalt) {
		return TextUtils.isEmpty(s) ? defalt : s;
	}

	public static String getUnNullString(String s) {
		return TextUtils.isEmpty(s) ? "" : s;
	}

	/**
	 * 将指定的字符串替换成※
	 *
	 * @param text
	 * @param start
	 * @param length
	 * @return
	 */
	public static String getSafePhoneText(String text, int start, int length) {
		if (text == null || "".equals(text) || text.length() < start + length) {
			return "";
		}
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < text.length(); i++) {
			if (i > start && i <= start + length) {
				sb.append("*");
			} else {
				sb.append(text.charAt(i));
			}
		}
		return sb.toString();

	}

	public static String getSafeEmailText(String text, int start) {
		if (text == null || "".equals(text)) {
			return "";
		}
		int index = text.lastIndexOf("@");

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			if (i > start && i < index) {
				sb.append("*");
			} else {
				sb.append(text.charAt(i));
			}
		}
		return sb.toString();
	}

	/**
	 * 字符串转整数
	 *
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 字符串转整数
	 *
	 * @param str
	 * @return 转换异常返回 0
	 */
	public static int toInt(String str) {
		return toInt(str, 0);
	}

	/**
	 * 字符串转整数
	 *
	 * @param str
	 * @return defValue
	 */
	public static long toLong(String str, long defValue) {
		try {
			return Long.parseLong(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 *
	 * @param str
	 * @return 转换异常返回 0
	 */
	public static long toLong(String str) {
		return toLong(str, 0);
	}

	/**
	 * 字符串转布尔值
	 *
	 * @param str
	 * @return defValue
	 */
	public static boolean toBool(String str, boolean defValue) {
		try {
			return Boolean.parseBoolean(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 字符串转布尔值
	 *
	 * @param str
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String str) {
		return toBool(str, false);
	}

	/**
	 * 将给定的字符串中所有给定的关键字标红
	 *
	 * @param sourceString 给定的字符串
	 * @param keyword      给定的关键字
	 * @return 返回的是带Html标签的字符串，在使用时要通过Html.fromHtml()转换为Spanned对象再传递给TextView对象
	 */
	public static String keywordMadeColor(String sourceString, String keyword, String color) {
		if (isEmpty(sourceString))
			return "";
		if (isEmpty(keyword)) {
			return sourceString;
		}
		if (isEmpty(color)) {
			return sourceString;
		}
		return sourceString.replaceAll(keyword, "<font color=\"" + color + "\">" + keyword + "</font>");
	}

	/**
	 * 为给定的字符串添加HTML颜色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
	 *
	 * @param string 给定的字符串
	 * @return
	 */
	public static String addHtmlColorFlag(String string, String color) {
		if (isEmpty(string)) {
			return "";
		}
		if (isEmpty(color)) {
			return string;
		}
		return "<font color=\"" + color + "\">" + string + "</font>";
	}

	/**
	 * 将一个InputStream流转换成字符串
	 *
	 * @param is
	 * @return
	 */
	public static String toConvertString(InputStream is) {
		StringBuffer res = new StringBuffer();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader read = new BufferedReader(isr);
		try {
			String line;
			line = read.readLine();
			while (line != null) {
				res.append(line);
				line = read.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != isr) {
					isr.close();
					isr.close();
				}
				if (null != read) {
					read.close();
					read = null;
				}
				if (null != is) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return res.toString();
	}
}
