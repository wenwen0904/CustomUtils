package tw.customer.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 系统显示相关工具类
 * @author: wenwen
 * @Date: 2016/3/21 17:38
 * @version: 1.0
 */
public class DisplayUtil {
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 *
	 * @param context 上下文
	 * @param dpValue 尺寸dip
	 * @return 像素值
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 *
	 * @param context 上下文
	 * @param pxValue 尺寸像素
	 * @return DIP值
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 sp
	 *
	 * @param context 上下文
	 * @param pxValue 尺寸像素
	 * @return SP值
	 */
	public static int px2sp(Context context, float pxValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 sp 的单位 转成为 px
	 *
	 * @param context 上下文
	 * @param spValue SP值
	 * @return 像素值
	 */
	public static int sp2px(Context context, float spValue) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取dialog宽度
	 *
	 * @param activity Activity
	 * @return Dialog宽度
	 */
	public static int getDialogW(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		dm = activity.getResources().getDisplayMetrics();
		int w = dm.widthPixels - 100;
		// int w = aty.getWindowManager().getDefaultDisplay().getWidth() - 100;
		return w;
	}

	/**
	 * 获取屏幕宽度
	 *
	 * @param activity Activity
	 * @return 屏幕宽度
	 */
	public static int getScreenWidth(Activity activity) {
		DisplayMetrics dm = activity.getResources().getDisplayMetrics();
		// int w = aty.getWindowManager().getDefaultDisplay().getWidth();
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 *
	 * @param activity Activity
	 * @return 屏幕高度
	 */
	public static int getScreenHeight(Activity activity) {
		DisplayMetrics dm = activity.getResources().getDisplayMetrics();
		// int h = aty.getWindowManager().getDefaultDisplay().getHeight();
		return dm.heightPixels;
	}
}
