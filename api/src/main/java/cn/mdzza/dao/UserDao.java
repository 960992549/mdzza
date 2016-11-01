package cn.mdzza.dao;

import cn.mdzza.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ydt on 2016/11/1.
 */
public interface UserDao {
	User get(@Param("mobile") String mobile, @Param("email") String email);
}
