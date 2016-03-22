package com.tw.customer.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验类
 *
 * @author: wenwen
 * @Date: 2016/3/21 17:57
 * @version: 1.0
 */
public class RegUtil {
	// 电子邮件
	private static String V_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	// 手机
	private static String V_MOBILE = "(\\+\\d+)?1[3458]\\d{9}$";
	// 固定电话
	private static String V_PHONE = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$";
	// 中文
	private static String V_CHINESE = "[\u0391-\uFFE5]";
	// 身份证
	private static String V_CARD = "^[0-9]{17}[0-9xX]$";

	/**
	 * 邮箱检测
	 *
	 * @param email 可能是Email的字符串
	 * @return 是否是Email
	 */
	public static boolean isEmail(String email) {
		if (isEmpty(email))
			return false;
		return Pattern.matches(V_EMAIL, email);
	}

	/**
	 * 非空验证
	 *
	 * @param data 源字符串
	 * @return 是否为空
	 */
	public static boolean isEmpty(String data) {
		return TextUtils.isEmpty(data);
	}

	/**
	 * 判断是不是一个合法的手机号
	 * 支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港）
	 * 移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）
	 * 150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）
	 * 联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）
	 * 电信的号段：133、153、180（未启用）、189
	 *
	 * @param mobile
	 * @return
	 */
	public static boolean isMobile(String mobile) {
		if (isEmpty(mobile))
			return false;
		return Pattern.matches(V_MOBILE, mobile);
	}

	/**
	 * 验证固定电话号码
	 *
	 * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8602085588447
	 *              国家（地区） 代码 ：标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，
	 *              数字之后是空格分隔的国家（地区）代码。
	 *              区号（城市代码）：这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——
	 *              对不使用地区或城市代码的国家（地区），则省略该组件。
	 *              电话号码：这包含从 0 到 9 的一个或多个数字
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean isPhone(String phone) {
		if (isEmpty(phone))
			return false;
		return Pattern.matches(V_PHONE, phone);
	}

	/**
	 * 只含字母和数字
	 *
	 * @param data 可能只包含字母和数字的字符串
	 * @return 是否只包含字母和数字
	 */
	public static boolean isNumberLetter(String data) {
		if (isEmpty(data))
			return false;
		return Pattern.matches("^[A-Za-z0-9]+$", data);
	}

	/**
	 * 只含数字
	 *
	 * @param data 可能只包含数字的字符串
	 * @return 是否只包含数字
	 */
	public static boolean isNumber(String data) {
		if (isEmpty(data))
			return false;
		return Pattern.matches("^[0-9]+$", data);
	}

	/**
	 * 只含字母
	 *
	 * @param data 可能只包含字母的字符串
	 * @return 是否只包含字母
	 */
	public static boolean isLetter(String data) {
		if (isEmpty(data))
			return false;
		return Pattern.matches("^[A-Za-z]+$", data);
	}

	/**
	 * 只是中文
	 *
	 * @param data 可能是中文的字符串
	 * @return 是否只是中文
	 */
	public static boolean isChinese(String data) {
		if (isEmpty(data))
			return false;
		return Pattern.matches(V_CHINESE, data);
	}

	/**
	 * 包含中文
	 *
	 * @param data 可能包含中文的字符串
	 * @return 是否包含中文
	 */
	public static boolean isContainChinese(String data) {
		if (isEmpty(data)) {
			for (int i = 0; i < data.length(); i++) {
				String temp = data.substring(i, i + 1);
				boolean flag = temp.matches(V_CHINESE);
				if (flag) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 小数点位数
	 *
	 * @param data   可能包含小数点的字符串
	 * @param length 小数点后的长度
	 * @return 是否小数点后有length位数字
	 */
	public static boolean isDianWeiShu(String data, int length) {
		if (isEmpty(data))
			return false;
		return Pattern.matches("^[1-9][0-9]+\\\\.[0-9]{" + length + "}$", data);
	}

	/**
	 * 身份证号码验证
	 *
	 * @param data 可能是身份证号码的字符串
	 * @return 是否是身份证号码
	 */
	public static boolean isCard(String data) {
		if (isEmpty(data))
			return false;
		return Pattern.matches(V_CARD, data);
	}

	/**
	 * 功能：身份证的有效验证
	 *
	 * @param IDStr 身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	public static String idCardValidate(String IDStr) {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
		String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位.";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (!isNumber(Ai)) {
			errorInfo = "身份证15位号码都应为数字;18位号码除最后一位外,都应为数字.";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (!isDataFormat(strYear + "-" + strMonth + "-" + strDay)) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
					|| (gc.getTime().getTime() - s.parse(
					strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
				errorInfo = "身份证生日不在有效范围。";
				return errorInfo;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "解析失败";
		} catch (ParseException e) {
			e.printStackTrace();
			return "解析失败";
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;
		System.out.println("Ai:" + Ai);
		if (IDStr.length() == 18) {
			if (!Ai.equals(IDStr)) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}


	/**
	 * 邮政编码验证
	 *
	 * @param data 可能包含邮政编码的字符串
	 * @return 是否是邮政编码
	 */
	public static boolean isPostCode(String data) {
		if (isEmpty(data))
			return false;
		return Pattern.matches("^[0-9]{6,10}", data);
	}

	/**
	 * 长度验证
	 *
	 * @param data   源字符串
	 * @param length 期望长度
	 * @return 是否是期望长度
	 */
	public static boolean isLength(String data, int length) {
		return data != null && data.length() == length;
	}

	/**
	 * 验证日期字符串是否是YYYY-MM-DD格式
	 *
	 * @param str
	 * @return
	 */
	public static boolean isDataFormat(String str) {
		boolean flag = false;
		// String
		// regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
		String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1 = Pattern.compile(regxStr);
		Matcher isNo = pattern1.matcher(str);
		if (isNo.matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 功能：设置地区编码
	 *
	 * @return Hashtable 对象
	 */
	private static Hashtable<String, String> GetAreaCode() {
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}
}
