package tw.customer.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * @author: wenwen
 * @Date: 2016/5/5 16:00
 * @version: 1.0
 */
public class DialogUtil {

	/**
	 * 确认框
	 */
	public static Dialog confirm(Context context, String title, String content,
								 String positiveButtonTxt, final OnBtnClickListener positiveClickListener) {
		return confirm(context, title, content, positiveButtonTxt, positiveClickListener, null, null, null, null);
	}

	/**
	 * 确认框
	 */
	public static Dialog confirm(Context context, String title, String content,
								 String positiveButtonTxt, final OnBtnClickListener positiveClickListener,
								 String negativeButtonTxt, final OnBtnClickListener negativeClickListener) {
		return confirm(context, title, content, positiveButtonTxt, positiveClickListener, negativeButtonTxt, negativeClickListener, null, null);
	}

	/**
	 * 确认框
	 */
	public static Dialog confirm(Context context, String title, String content,
								 String positiveButtonTxt, final OnBtnClickListener positiveClickListener,
								 String negativeButtonTxt, final OnBtnClickListener negativeClickListener,
								 String neutralButtonTxt, final OnBtnClickListener neutralClickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title).setMessage(content);
		if (positiveButtonTxt != null) {
			builder.setPositiveButton(positiveButtonTxt, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (positiveClickListener != null) {
						positiveClickListener.onClick(dialog, which);
					}
				}
			});
		}
		if (negativeButtonTxt != null) {
			builder.setNegativeButton(negativeButtonTxt, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (negativeClickListener != null) {
						negativeClickListener.onClick(dialog, which);
					}
				}
			});
		}
		if (neutralButtonTxt != null) {
			builder.setNeutralButton(neutralButtonTxt, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (neutralClickListener != null) {
						neutralClickListener.onClick(dialog, which);
					}
				}
			});
		}
		Dialog alertDialog = builder.show();
		alertDialog.setCanceledOnTouchOutside(false);
		return alertDialog;
	}

	/**
	 * 选择框
	 */
	public static Dialog select(Context context, String title, String[] items, final OnBtnClickListener itemClickListener) {
		return select(context, title, items, itemClickListener, null, null);
	}

	/**
	 * 选择框
	 */
	public static Dialog select(Context context, String title, String[] items, final OnBtnClickListener itemClickListener,
								String negativeButtonTxt, final OnBtnClickListener negativeClickListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title).setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				if (itemClickListener != null) {
					itemClickListener.onClick(dialog, which);
				}
			}
		}).setNegativeButton(negativeButtonTxt, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (negativeClickListener != null) {
					negativeClickListener.onClick(dialog, which);
				}
			}
		});
		Dialog alertDialog = builder.show();
		alertDialog.setCanceledOnTouchOutside(false);
		return alertDialog;
	}


	public interface OnBtnClickListener {
		void onClick(DialogInterface dialog, int which);
	}
}
