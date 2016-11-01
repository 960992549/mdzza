package cn.mdzza.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ydt on 2016/11/1.
 */
public class EmailUtil {
	public static boolean isEmail(String email) {
		return StringUtils.isNotBlank(email) && email.indexOf("@") >= 0;
	}
}
