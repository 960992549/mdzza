package cn.mdzza.sys.dao;

import cn.mdzza.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ydt on 2017/2/14.
 */
public interface SysUserDao {
	SysUser get(@Param("id") Long id, @Param("username") String username, @Param("password") String password);

	Integer add(@Param("username") String username, @Param("password") String password,
				@Param("name") String name, @Param("mobile") String mobile, @Param("createUserId") Long createUserId);

	Boolean checkPermission(@Param("userId") Long userId, @Param("invokeMethod") String invokeMethod);
}
