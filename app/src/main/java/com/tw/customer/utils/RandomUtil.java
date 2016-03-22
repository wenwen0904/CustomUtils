package com.tw.customer.utils;

import android.text.TextUtils;

import java.util.Random;

/**
 * 随机数工具
 *
 * @author: wenwen
 * @Date: 2016/3/21 17:46
 * @version: 1.0
 */
public class RandomUtil {
	public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERS = "0123456789";
	public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String CAPITAL_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 获取任意长度数字+大小写字母的随机字符串
	 *
	 * @param length 长度
	 * @return 随机字符串
	 */
	public static String getRandomNumbersAndLetters(int length) {
		return getRandom(NUMBERS_AND_LETTERS, length);
	}

	/**
	 * 获取任意长度数字随机字符串
	 *
	 * @param length 长度
	 * @return 随机数字符串
	 */
	public static String getRandomNumbers(int length) {
		return getRandom(NUMBERS, length);
	}

	/**
	 * 获取任意长度大小写字母随机字符串
	 *
	 * @param length 长度
	 * @return 随机字母字符串
	 */
	public static String getRandomLetters(int length) {
		return getRandom(LETTERS, length);
	}

	/**
	 * 获取任意长度大写字母随机字符串
	 *
	 * @param length 长度
	 * @return 随机字符串 包括大写字母
	 */
	public static String getRandomCapitalLetters(int length) {
		return getRandom(CAPITAL_LETTERS, length);
	}

	/**
	 * 获取任意长度小写字母随机字符串
	 *
	 * @param length 长度
	 * @return 随机字符串 包括小写字母
	 */
	public static String getRandomLowerCaseLetters(int length) {
		return getRandom(LOWER_CASE_LETTERS, length);
	}

	/**
	 * 随机字符串
	 *
	 * @param source 源字符串
	 * @param length 长度
	 * @return 随机字符串
	 */
	public static String getRandom(String source, int length) {
		return TextUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
	}

	/**
	 * 随机字符串
	 *
	 * @param sourceChar 源
	 * @param length     长度
	 * @return 随机字符串
	 */
	public static String getRandom(char[] sourceChar, int length) {
		if (sourceChar == null || sourceChar.length == 0 || length < 0) {
			return null;
		}

		StringBuilder str = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			str.append(sourceChar[random.nextInt(sourceChar.length)]);
		}
		return str.toString();
	}

	/**
	 * 0 - max 之间的随机数
	 *
	 * @param max 最大随机数
	 * @return 0 - max 之间的随机数
	 */
	public static int getRandom(int max) {
		return getRandom(0, max);
	}

	/**
	 * min - max 之间的随机数
	 *
	 * @param min 最小随机数
	 * @param max 最大随机数
	 * @return min - max 之间的随机数
	 */
	public static int getRandom(int min, int max) {
		if (min > max) {
			return 0;
		}
		if (min == max) {
			return min;
		}
		return min + new Random().nextInt(max - min);
	}

	/**
	 * 打乱数组(按数组长度次数)
	 *
	 * @param objArray
	 * @return 打乱是否成功
	 */
	public static boolean shuffle(Object[] objArray) {
		if (objArray == null) {
			return false;
		}

		return shuffle(objArray, getRandom(objArray.length));
	}

	/**
	 * 打乱数组(指定次数,不可大于数组长度)
	 *
	 * @param objArray
	 * @param shuffleCount
	 * @return 打乱是否成功
	 */
	public static boolean shuffle(Object[] objArray, int shuffleCount) {
		int length;
		if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
			return false;
		}

		for (int i = 1; i <= shuffleCount; i++) {
			int random = getRandom(length - i);
			Object temp = objArray[length - i];
			objArray[length - i] = objArray[random];
			objArray[random] = temp;
		}
		return true;
	}

	/**
	 * 打乱数组(按数组长度次数)
	 *
	 * @param intArray
	 * @return 打乱是否成功
	 */
	public static int[] shuffle(int[] intArray) {
		if (intArray == null) {
			return null;
		}

		return shuffle(intArray, getRandom(intArray.length));
	}

	/**
	 * 打乱数组(指定次数,不可大于数组长度)
	 *
	 * @param intArray
	 * @param shuffleCount
	 * @return 打乱是否成功
	 */
	public static int[] shuffle(int[] intArray, int shuffleCount) {
		int length;
		if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
			return null;
		}

		int[] out = new int[shuffleCount];
		for (int i = 1; i <= shuffleCount; i++) {
			int random = getRandom(length - i);
			out[i - 1] = intArray[random];
			int temp = intArray[length - i];
			intArray[length - i] = intArray[random];
			intArray[random] = temp;
		}
		return out;
	}
}
