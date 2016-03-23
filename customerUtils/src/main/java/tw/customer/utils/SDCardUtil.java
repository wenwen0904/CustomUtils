package tw.customer.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * SD卡工具类
 *
 * @author: wenwen
 * @Date: 2016/3/22 14:39
 * @version: 1.0
 */
public class SDCardUtil {
	/**
	 * 检测sd卡是否存在
	 *
	 * @return 是否存在SDCard
	 */
	public static boolean isSDCardAvailable() {
		return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取一个可用的存储地址
	 *
	 * @return
	 */
	public static String getSavePath(Context context) {
		String path = getSDCardPath();
		// sd卡不存在时,就返回内存的地址
		if (path.equals("")) {
			path = getLocalPath(context);
		}
		path = path + File.separator;
		return path;
	}

	/**
	 * getAbsolutePath 获取SD卡绝对路径
	 *
	 * @return sd卡路径，sd卡不可利用返回“”字符串
	 */
	public static String getSDCardPath() {
		if (isSDCardAvailable()) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return "";
	}

	/**
	 * getPath 获取系统内存的根路径
	 *
	 * @param c
	 * @return 系统内存的根路径
	 */
	public static String getLocalPath(Context c) {
		return c.getFilesDir().getAbsolutePath();
	}
}
