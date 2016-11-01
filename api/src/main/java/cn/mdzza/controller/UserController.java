package cn.mdzza.controller;

import cn.mdzza.dto.Result;
import cn.mdzza.entity.User;
import cn.mdzza.enums.RegisterTypeEnum;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.service.UserService;
import cn.mdzza.util.EmailUtil;
import cn.mdzza.util.MobileUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

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
		RegisterTypeEnum registerType;
		if(StringUtils.isNotEmpty(mobile) && StringUtils.isEmpty(email) && MobileUtil.isMobile(mobile)) {
			registerType = RegisterTypeEnum.MOBILE;
		} else if(StringUtils.isNotEmpty(email) && StringUtils.isEmpty(mobile) && EmailUtil.isEmail(email)) {
			registerType = RegisterTypeEnum.EMAIL;
		} else {
			return new Result<>(ResultEnum.PARAM_ERROR);
		}
		Map<String, Object> result = userService.register(registerType, mobile, password);
		if(MapUtils.getBoolean(result, "isSuccess")) {
			return new Result<>();
		} else {
			return new Result<>(ResultEnum.OTHER_ERROR.getCode(), MapUtils.getString(result, "message"));
		}
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
