package tw.customer.utils;

import android.app.Application;

/**
 * @author: wenwen
 * @Date: 2016/3/21 18:20
 * @version: 1.0
 * 版权:   版权所有(C) 2016
 * 公司:   拓维信息系统股份有限公司
 */
public class UtilsInit {
	public static Application context;

	public static void init(Application context) {
		if (UtilsInit.context == null) {
			UtilsInit.context = context;
		}
	}
}
