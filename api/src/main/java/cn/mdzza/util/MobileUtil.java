package cn.mdzza.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by ydt on 2016/11/1.
 */
public class MobileUtil {
	public static boolean isMobile(String mobile) {
		return StringUtils.isNotBlank(mobile) && mobile.length() == 11;
	}
}
