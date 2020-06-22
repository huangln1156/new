
package com.keeplive.my.retomedesktop;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtils {

	public enum SP_KEY{
		HOME_MENU_IS_SHOWING,

	}
	private static final String SP_NAME = "jsylc_sp";
	public static void put(Context context, String key, Object value) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		Editor edit = null;
		if (null != sp) {
			edit = sp.edit();
		}
		if (null != edit) {
			if (value instanceof String) {
				edit.putString(key, (String) value);
			} else if (value instanceof Integer) {
				edit.putInt(key, (Integer) value);
			} else if (value instanceof Boolean) {
				edit.putBoolean(key, (boolean) value);
			} else if (value instanceof Long){
				edit.putLong(key, (Long) value);
			}
			edit.apply();
		}
	}

	public static String getString(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		if (null != sp) {
			return sp.getString(key, "");
		} else {
			return "";
		}
	}

	public static int getInt(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		if (null != sp) {
			return sp.getInt(key, 0);
		} else {
			return 0;
		}
	}

	public static boolean getBoolean(Context context, String key, boolean strDefault) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

		if (null != sp) {
			return sp.getBoolean(key, strDefault);
		} else {
			return strDefault;
		}
	}

	public static long getLong(Context context, String key, long defaultValue) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		if (null != sp) {
			return sp.getLong(key, defaultValue);
		} else {
			return defaultValue;
		}
	}
	public static void clear(Context context, String key) {
		SharedPreferences sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		if (null != sp) {
			sp.edit().remove(key).commit();
		}
	}
}
