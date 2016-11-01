package cn.mdzza.service;

import cn.mdzza.dao.UserDao;
import cn.mdzza.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ydt on 2016/11/1.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserDao userDao;

	public User get(String mobile, String email) {
		return userDao.get(mobile, email);
	}
}
