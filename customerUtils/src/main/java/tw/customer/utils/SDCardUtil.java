package tw.customer.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * SD卡工具类
 * @author: wenwen
 * @Date: 2016/3/22 14:39
 * @version: 1.0
 */
public class SDCardUtil {
	/**
	 * 检测sd卡是否存在
	 * @return 是否存在SDCard
	 */
	public static boolean isSDCardAvailable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	/**
	 * 获取一个应用的缓存地址
	 */
	public static String getSavePath(Context context) {
		File result;
		if (isSDCardAvailable()) {
			File cacheDir = context.getExternalFilesDir(null);
			if (cacheDir == null) {
				result = new File(Environment.getExternalStorageDirectory(),
						"Android/data/" + context.getPackageName() + "/files");
			} else {
				result = new File(cacheDir.getAbsolutePath());
			}
		} else {
			result = context.getFilesDir();
		}
		if (!result.exists()) {
			result.mkdirs();
		}
		String path = result.getAbsolutePath();
		if (!path.endsWith("/")) {
			path = path + File.separator;
		}
		return path;
	}

	/**
	 * getAbsolutePath 获取SD卡绝对路径
	 * @return sd卡路径，sd卡不可利用返回“”字符串
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	/**
	 * getPath 获取系统内存的根路径
	 * @param c
	 * @return 系统内存的根路径
	 */
	public static String getLocalPath(Context c) {
		return c.getFilesDir().getAbsolutePath();
	}
}
