package tw.customer.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author: wenwen
 * @Date: 2016/3/22 15:06
 * @version: 1.0
 * 版权:   版权所有(C) 2016
 * 公司:   拓维信息系统股份有限公司
 */
public class AppUtil {
	/**
	 * 得到软件版本号
	 * @param context 上下文
	 * @return 当前版本Code
	 */
	public static int getVersionCode(Context context) {
		int verCode = -1;
		try {
			String packageName = context.getPackageName();
			verCode = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 得到软件显示版本信息
	 * @param context 上下文
	 * @return 当前版本信息
	 */
	public static String getVersionName(Context context) {
		String verName = "";
		try {
			String packageName = context.getPackageName();
			verName = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return verName;
	}

	/**
	 * 取得应用元数据（AndroidManifest.xml中配置的meta-data数据）
	 * @param context
	 * @param key
	 * @return String
	 */
	public static String getMetaDataByKey(Context context, String key) {
		if (TextUtils.isEmpty(key)) {
			try {
				ApplicationInfo appInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
				key = appInfo.metaData.getString(key);
			} catch (PackageManager.NameNotFoundException e) {
				e.printStackTrace();
			}
		}
		return key;
	}

	/**
	 * 根据包名检查app是否已被安装
	 * @param context
	 * @param packageName
	 */
	public static boolean checkAPP(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	/**
	 * 根据包名查看其它应用的信息
	 * @param context
	 * @param packageName
	 */
	public static PackageInfo getOtherPackageInfo(Context context, String packageName) {
		PackageInfo info = null;
		try {
			info = context.getApplicationContext().getPackageManager().getPackageInfo(packageName, 0);
		} catch (PackageManager.NameNotFoundException e) {
			return null;
		}
		return info;
	}

	/**
	 * 判断应用是否在后台
	 * @param context
	 */
	public static boolean isBackgroundRunning(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
		if (tasks != null && !tasks.isEmpty()) {
			ComponentName topActivity = tasks.get(0).topActivity;
			if (topActivity != null && topActivity.getPackageName().equals(context.getPackageName())) {
				return false;// 前台
			} else {
				return true;
			}
		}
		return true;
	}

	/**
	 * 获取应用签名
	 * @param context 上下文
	 * @param pkgName 包名
	 */
	public static String getSign(Context context, String pkgName) {
		try {
			PackageInfo pis = context.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);
			return hexdigest(pis.signatures[0].toByteArray());
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将签名字符串转换成需要的32位签名
	 * @param paramArrayOfByte 签名byte数组
	 * @return 32位签名字符串
	 */
	private static String hexdigest(byte[] paramArrayOfByte) {
		final char[] hexDigits = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramArrayOfByte);
			byte[] arrayOfByte = localMessageDigest.digest();
			char[] arrayOfChar = new char[32];
			for (int i = 0, j = 0; ; i++, j++) {
				if (i >= 16) {
					return new String(arrayOfChar);
				}
				int k = arrayOfByte[i];
				arrayOfChar[j] = hexDigits[(0xF & k >>> 4)];
				arrayOfChar[++j] = hexDigits[(k & 0xF)];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取设备的可用内存大小
	 * @param context 应用上下文对象context
	 * @return 当前内存大小
	 */
	public static int getDeviceUsableMemory(Context context) {
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
		am.getMemoryInfo(mi);
		// 返回当前系统的可用内存
		return (int) (mi.availMem / (1024 * 1024));
	}

	/**
	 * 获取系统中所有的应用
	 * @param context 上下文
	 * @return 应用信息List
	 */
	public static List<PackageInfo> getAllApps(Context context) {

		List<PackageInfo> apps = new ArrayList<PackageInfo>();
		PackageManager pManager = context.getPackageManager();
		List<PackageInfo> paklist = pManager.getInstalledPackages(0);
		for (int i = 0; i < paklist.size(); i++) {
			PackageInfo pak = paklist.get(i);
			if ((pak.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0) {
				// customs applications
				apps.add(pak);
			}
		}
		return apps;
	}

	/**
	 * 获取手机系统SDK版本
	 * @return 如API 17 则返回 17
	 */
	public static int getSDKVersion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 是否ART模式
	 * @return 结果
	 */
	public static boolean isART() {
		String currentRuntime = getCurrentRuntimeValue();
		return "ART".equals(currentRuntime) || "ART debug build".equals(currentRuntime);
	}

	/**
	 * 获取手机当前的Runtime
	 * @return 正常情况下可能取值Dalvik, ART, ART debug build;
	 */
	public static String getCurrentRuntimeValue() {
		try {
			Class<?> systemProperties = Class.forName("android.os.SystemProperties");
			try {
				Method get = systemProperties.getMethod("get",
						String.class, String.class);
				if (get == null) {
					return "WTF?!";
				}
				try {
					final String value = (String) get.invoke(systemProperties, "persist.sys.dalvik.vm.lib",/* Assuming default is */"Dalvik");
					if ("libdvm.so".equals(value)) {
						return "Dalvik";
					} else if ("libart.so".equals(value)) {
						return "ART";
					} else if ("libartd.so".equals(value)) {
						return "ART debug build";
					}

					return value;
				} catch (IllegalAccessException e) {
					return "IllegalAccessException";
				} catch (IllegalArgumentException e) {
					return "IllegalArgumentException";
				} catch (InvocationTargetException e) {
					return "InvocationTargetException";
				}
			} catch (NoSuchMethodException e) {
				return "SystemProperties.get(String key, String def) method is not found";
			}
		} catch (ClassNotFoundException e) {
			return "SystemProperties class is not found";
		}
	}

	/**
	 * 获取设备唯一标识
	 * @param context
	 */
	public static String getUUID(Context context) {
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		String uniqueId = deviceUuid.toString();
		return uniqueId;
	}

	public static String getIMEI(Context context) {
		try {
			TelephonyManager telephoneMngr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			return telephoneMngr.getDeviceId();
		} catch (Exception e) {
			return "000000000000000";
		}
	}

	/**
	 * 检测服务是否运行
	 * @param context   上下文
	 * @param className 类名
	 * @return 是否运行的状态
	 */
	public static boolean isServiceRunning(Context context, String className) {
		boolean isRunning = false;
		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> servicesList = activityManager.getRunningServices(Integer.MAX_VALUE);
		for (ActivityManager.RunningServiceInfo si : servicesList) {
			if (className.equals(si.service.getClassName())) {
				isRunning = true;
			}
		}
		return isRunning;
	}

	/**
	 * 停止运行服务
	 * @param context   上下文
	 * @param className 类名
	 * @return 是否执行成功
	 */
	public static boolean stopRunningService(Context context, String className) {
		Intent intent_service = null;
		boolean ret = false;
		try {
			intent_service = new Intent(context, Class.forName(className));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (intent_service != null) {
			ret = context.stopService(intent_service);
		}
		return ret;
	}
}
