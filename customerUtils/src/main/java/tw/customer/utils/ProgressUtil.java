package tw.customer.utils;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;

/**
 * @author: wenwen
 * @Date: 2016/4/1 15:03
 * @version: 1.0
 * 版权:   版权所有(C) 2016
 * 公司:   拓维信息系统股份有限公司
 */
public class ProgressUtil {

	private static MaterialDialog dialog = null;
	private static int widgetColorRes = android.R.color.holo_red_light;
	private static int contentColorRes = android.R.color.black;

	public static MaterialDialog show(Context context, String msg) {
		return show(context, msg, widgetColorRes, contentColorRes);
	}

	public static MaterialDialog show(Context context, String msg, int widgetColorRes, int contentColor) {
		if (dialog != null && dialog.isShowing()) {
			dialog.setContent(msg);
		} else {
			dialog = buildDialog(context, msg, widgetColorRes, contentColor).show();
			dialog.setCanceledOnTouchOutside(false);
		}
		return dialog;
	}

	public static MaterialDialog.Builder buildDialog(Context context, String msg, int widgetColorRes, int contentColor) {
		return new MaterialDialog.Builder(context)
				.content(msg)
				.progress(true, 0)
				.widgetColorRes(widgetColorRes)
				.contentColorRes(contentColor)
				.progressIndeterminateStyle(false);
	}

	public static void hide() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
