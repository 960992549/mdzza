package cn.mdzza.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ydt on 2016/9/23.
 */
public class DateUtil {
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static Date parseDate(String str) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(str);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	/**
	 * 过去时间
	 * @param date
	 * @return
	 */
	public static String  pastTime(Date date) {
		long t = new Date().getTime() - date.getTime();
		long d = 1000*24*60*60;//一天的毫秒数
		long h = 1000*60*60;//一小时的毫秒数
		long m = 1000*60;//一分钟的毫秒数
		long s = 1000;//一秒钟的毫秒数
		String pastTime = "";
		if(t >= d){
			pastTime = t/d + "天前";
		}else if(t >= h){
			pastTime = t/h + "小时前";
		}else if(t >= m){
			pastTime = t/m + "分钟前";
		}else if(t >= s){
			pastTime = t/s + "秒前";
		}
		return pastTime;
	}
}
