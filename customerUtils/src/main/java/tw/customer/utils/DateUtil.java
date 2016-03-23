package tw.customer.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 日期操作类
 * @author:   wenwen
 * @version:  1.0  
 * @Date:     2015-9-16 下午4:26:13  
 */
public class DateUtil {
	/**
	 * 日期类型
	 */
	public static final String yyyyMM = "yyyy-MM";
	public static final String yyyyMMdd = "yyyy-MM-dd";
	public static final String yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
	public static final String HHmmss = "HH:mm:ss";
	public static final String HHmm = "HH:mm";
	public static final String yyyyMMddHHmmssZn = "yyyy年M月d日 HH:mm:ss";
	public static final String yyyyMMddHHmmZn = "yyyy年M月d日 HH:mm";

	private static Map<String, ThreadLocal<SimpleDateFormat>> cache = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	public static SimpleDateFormat getSimpleDateFormat(final String formatStr) {
		if (!cache.containsKey(formatStr)) {
			ThreadLocal<SimpleDateFormat> simpleDateFormat = new ThreadLocal<SimpleDateFormat>() {
				@Override
				protected SimpleDateFormat initialValue() {
					return new SimpleDateFormat(formatStr, Locale.getDefault());
				}
			};
			cache.put(formatStr, simpleDateFormat);
		}
		return cache.get(formatStr).get();
	}

	/**
	 * 日期转字符串
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String dateToString(Date date) {
		return getSimpleDateFormat(yyyyMMddHHmmss).format(date);
	}

	/**
	 * 日期转字符串
	 * 手动传入格式
	 */
	public static String dateToString(Date date, String pattern) {
		return getSimpleDateFormat(pattern).format(date);
	}

	/**
	 * 字符串转日期
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static Date stringToDate(String dateStr) {
		try {
			return getSimpleDateFormat(yyyyMMddHHmmss).parse(dateStr);
		} catch (ParseException e) {
			return new Date(0);
		}
	}

	/**
	 * 字符串转日期
	 * 手动传入格式
	 */
	public static Date stringToDate(String dateStr, String pattern) {
		try {
			return getSimpleDateFormat(pattern).parse(dateStr);
		} catch (ParseException e) {
			return new Date(0);
		}
	}

	/**
	 * 返回当前时间
	 * 格式:yyyy-MM-dd HH:mm:ss
	 * @return string
	 */
	public static String getNowTime() {
		return getSimpleDateFormat(yyyyMMddHHmmss).format(new Date());
	}

	/**
	 * 得到年
	 * @param date Date对象
	 * @return 年
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 得到月
	 * @param date Date对象
	 * @return 月
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;

	}

	/**
	 * 得到日
	 * @param date Date对象
	 * @return 日
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 *  指定的日期字符，返回当前是星期几
	 * @param date
	 * @return
	 */
	public static String getWeek(String date) {
		Date mDate = null;
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		try {
			mDate = getSimpleDateFormat(yyyyMMdd).parse(date);
			Calendar cal = Calendar.getInstance();
			cal.setTime(mDate);
			int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
			if (week_index < 0) {
				week_index = 0;
			}
			return weeks[week_index];
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

	}

	/**
	 *  返回当前是星期几
	 * @return
	 */
	public static String getCurrentWeek() {
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

		Calendar cal = Calendar.getInstance();
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 转换日期 将日期转为今天, 昨天, 前天, XXXX-XX-XX, ...
	 *
	 * @param time 时间
	 * @return 当前日期转换为更容易理解的方式
	 */
	public static String translateDate(Long time) {
		long oneDay = 24 * 60 * 60 * 1000;
		Calendar current = Calendar.getInstance();
		Calendar today = Calendar.getInstance(); // 今天

		today.set(Calendar.YEAR, current.get(Calendar.YEAR));
		today.set(Calendar.MONTH, current.get(Calendar.MONTH));
		today.set(Calendar.DAY_OF_MONTH, current.get(Calendar.DAY_OF_MONTH));
		// Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);

		long todayStartTime = today.getTimeInMillis();

		if (time >= todayStartTime && time < todayStartTime + oneDay) {
			return "今天";
		} else if (time >= todayStartTime - oneDay && time < todayStartTime) {
			return "昨天";
		} else if (time >= todayStartTime - oneDay * 2 && time < todayStartTime - oneDay) {
			return "前天";
		} else {
			Date date = new Date(time);
			return getSimpleDateFormat(yyyyMMdd).format(date);
		}
	}

	/**
	 * 转换日期 转换为更为人性化的时间
	 *
	 * @param time 时间
	 * @return
	 */
	public static String translateDate(long time, long curTime) {
		long oneDay = 24 * 60 * 60;
		Calendar today = Calendar.getInstance(); // 今天
		today.setTimeInMillis(curTime * 1000);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE, 0);
		today.set(Calendar.SECOND, 0);
		long todayStartTime = today.getTimeInMillis() / 1000;
		if (time >= todayStartTime) {
			long d = curTime - time;
			if (d <= 60) {
				return "1分钟前";
			} else if (d <= 60 * 60) {
				long m = d / 60;
				if (m <= 0) {
					m = 1;
				}
				return m + "分钟前";
			} else {
				Date date = new Date(time * 1000);
				String dateStr = getSimpleDateFormat("今天 HH:mm").format(date);
				if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
					dateStr = dateStr.replace(" 0", " ");
				}
				return dateStr;
			}
		} else {
			if (time < todayStartTime && time > todayStartTime - oneDay) {
				Date date = new Date(time * 1000);
				String dateStr = getSimpleDateFormat("昨天 HH:mm").format(date);
				if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
					dateStr = dateStr.replace(" 0", " ");
				}
				return dateStr;
			} else if (time < todayStartTime - oneDay && time > todayStartTime - 2 * oneDay) {
				Date date = new Date(time * 1000);
				String dateStr = getSimpleDateFormat("前天 HH:mm").format(date);
				if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
					dateStr = dateStr.replace(" 0", " ");
				}
				return dateStr;
			} else {
				Date date = new Date(time * 1000);
				String dateStr = getSimpleDateFormat(yyyyMMddHHmm).format(date);
				if (!TextUtils.isEmpty(dateStr) && dateStr.contains(" 0")) {
					dateStr = dateStr.replace(" 0", " ");
				}
				return dateStr;
			}
		}
	}
}
