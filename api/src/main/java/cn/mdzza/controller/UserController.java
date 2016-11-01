package cn.mdzza.controller;

import cn.mdzza.dto.Result;
import cn.mdzza.entity.User;
import cn.mdzza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户控制器
 * Created by ydt on 2016/11/1.
 */
@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用户注册
	 * @param mobile
	 * @param email
	 * @param password
	 * @return
	 */
	@RequestMapping("register")
	public Result<Object> register(String mobile, String email, String password) {
		User user = userService.get(mobile, email);
		return null;
	}

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	public Result<Object> login(String username, String password) {
		return null;
	}
}
