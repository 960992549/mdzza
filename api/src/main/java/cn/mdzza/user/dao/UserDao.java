package cn.mdzza.user.dao;

import cn.mdzza.user.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ydt on 2017/2/16.
 */
public interface UserDao {
	void register(User user);

	User get(@Param("username") String username, @Param("password") String password);
}
