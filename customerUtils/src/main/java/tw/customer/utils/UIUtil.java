package tw.customer.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * UI操作，包括键盘
 * @author: wenwen
 * @Date: 2016/3/21 18:14
 * @version: 1.0
 */
public class UIUtil {

	private static Toast toast;

	public static Toast getToast() {
		return toast;
	}

	public static void setToast(Toast toast) {
		UIUtil.toast = toast;
	}

	/**
	 * 唯一Toast
	 */
//	public static void showToast(Context context, String string) {
//		if (toast == null) {
//			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
//		} else {
//			toast.setText(string);
//		}
//		toast.show();
//	}

	/**
	 * 唯一Toast
	 */
//	public static void showToast(String string) {
//		if (toast == null) {
//			return;
//		}
//		toast.setText(string);
//		toast.show();
//	}

	/**
	 * 唯一Toast
	 */
	public static void showToast(Application context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.setText(string);
		toast.show();
	}

	public static void hideInputMethod(Activity context) {
		InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (context.getWindow() != null) {
			Window win = context.getWindow();
			if (win.getCurrentFocus() != null) {
				inputMethodManager.hideSoftInputFromWindow(win.getCurrentFocus().getWindowToken(), 0);
			} else {
				win.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
			}
		}
	}

	/**
	 * 弹出键盘
	 */
	public static void showPan(EditText editText) {
		InputMethodManager manager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		manager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}

	/**
	 * 隐藏键盘
	 */
	public static void hidePan(Context mcontext, View v) {
		InputMethodManager imm = (InputMethodManager) mcontext.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}

	public static void disableView(View view) {
		setViewState(view, false);
	}

	public static void enableView(View view) {
		setViewState(view, true);
	}

	private static void setViewState(View view, boolean isEnabled) {
		view.setEnabled(isEnabled);
		view.setFocusable(isEnabled);
		view.setClickable(isEnabled);
	}

}
