package cn.mdzza.sys.dao;

import cn.mdzza.sys.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ydt on 2017/2/14.
 */
public interface UserDao {
	User get(@Param("username") String username, @Param("password") String password);
}
