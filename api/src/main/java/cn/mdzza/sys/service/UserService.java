package cn.mdzza.sys.service;

import cn.mdzza.sys.dao.UserDao;
import cn.mdzza.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ydt on 2017/2/14.
 */
@Service("sysUserService")
public class UserService {
	@Autowired
	private UserDao userDao;

	public User login(String username, String password) {
		return userDao.get(username, password);
	}
}
