package cn.mdzza.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 同步工具类
 * Created by ydt on 2016/7/21.
 */
public class SyncUtil {
	private static final String JOIN_MARK = "&";

	static Logger logger = LoggerFactory.getLogger(SyncUtil.class);

	/**
	 * 获取要同步锁定的对象，锁定的是：同步块的类名 + 同步的方法名 + key
	 * @param key
	 * @return
	 */
	public static String getLock(String key) {
		logger.info("get lock start");
		String lock;
		StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
		if(stacks != null && stacks.length > 2) {
			lock = (stacks[2].getClassName() + JOIN_MARK + stacks[2].getMethodName() + JOIN_MARK + key).intern();
		} else {
			lock = key.intern();
		}
		logger.info("lock string:" + lock);
		return lock;
	}

	/**
	 * 获取要同步锁定的对象，锁定的是：tableNames + key
	 * @param key
	 * @param tableNames	需要同步的表名，例如：方法中会产生行级锁的表名
	 * @return
	 */
	public static String getLock(String key, String... tableNames) {
		logger.info("get lock start");
		Arrays.sort(tableNames);
		StringBuffer sb = new StringBuffer();
		for(String tableName : tableNames) {
			sb.append(tableName).append(JOIN_MARK);
		}
		sb.append(key);
		String lock = sb.toString().intern();
		logger.info("lock string:" + lock);
		return lock;
	}
}
