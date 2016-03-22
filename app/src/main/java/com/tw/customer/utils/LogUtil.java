package com.tw.customer.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * @author: wenwen
 * @Date: 2016/3/21 17:32
 * @version: 1.0
 */
public class LogUtil {
	// ***********************设置是否打印日志**********************************
	public static boolean isPrint = true;

	public static void i(Object obj) {
		if (isPrint) {
			String tag = getClassName();
			String message = obj != null ? obj.toString() : "obj == null";
			android.util.Log.i(tag, message);
		}
	}

	public static void d(Object obj) {
		if (isPrint) {
			String tag = getClassName();
			String message = obj != null ? obj.toString() : "obj == null";
			android.util.Log.d(tag, message);
		}
	}

	public static void w(Object obj) {
		if (isPrint) {
			String tag = getClassName();
			String message = obj != null ? obj.toString() : "obj == null";
			android.util.Log.w(tag, message);
		}
	}

	public static void e(Object obj) {
		if (isPrint) {
			String tag = getClassName();
			String message = obj != null ? obj.toString() : "obj == null";
			android.util.Log.e(tag, message);
		}
	}

	public static void i(String tag, String message) {
		if (isPrint) {
			if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
				android.util.Log.i(tag, message);
			}
		}
	}

	public static void d(String tag, String message) {
		if (isPrint) {
			if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
				android.util.Log.d(tag, message);
			}
		}
	}

	public static void e(String tag, String message) {
		if (isPrint) {
			if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
				android.util.Log.e(tag, message);
			}
		}
	}

	public static void w(String tag, String message) {
		if (isPrint) {
			if (!TextUtils.isEmpty(tag) && !TextUtils.isEmpty(message)) {
				android.util.Log.w(tag, message);
			}
		}
	}

	public static void e(Exception e) {
		if (isPrint) {
			if (e != null) {
				e.printStackTrace();
			}
		}
	}

	public static void showToast(Context context, String content) {
		if (isPrint) {
			if (context != null && content != null)
				Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
		}
	}

	private static String getClassName() {
		String result = "";
		StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
		result = thisMethodStack.getClassName();
		return result;
	}
}
