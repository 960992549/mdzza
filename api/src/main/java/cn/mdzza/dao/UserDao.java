package cn.mdzza.dao;

import cn.mdzza.entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ydt on 2016/11/1.
 */
public interface UserDao {

	User get(@Param("type") String type, @Param("sign") String sign);

	User getByLoginName(@Param("loginName") String loginName, @Param("password") String password);

	void register(@Param("type") String type, @Param("sign") String sign, @Param("password") String password);
}
