package cn.mdzza.controller;

import cn.mdzza.dto.Result;
import cn.mdzza.dto.Token;
import cn.mdzza.entity.User;
import cn.mdzza.enums.RegisterTypeEnum;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.service.UserService;
import cn.mdzza.util.EmailUtil;
import cn.mdzza.util.MobileUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 用户控制器
 * Created by ydt on 2016/11/1.
 */
@Controller
@RequestMapping(value = "user", method = RequestMethod.POST)
public class UserController {

	private static String JWT_ISSUER = "mdzza";
	private static long JWT_EXPIRE_SECOND = 3600;
	private static String JWT_SIGN_KEY = "mdzza";

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
	@ResponseBody
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
	 * @param loginName
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public Result<Object> login(String loginName, String password, HttpServletRequest request) {
		User user = userService.getByLoginName(loginName, password);
		if(user == null) {
			return new Result<>(ResultEnum.OTHER_ERROR.getCode(), "用户名或密码不正确");
		}
		Date now = new Date();
		String token = Jwts.builder().setIssuer(JWT_ISSUER)
				.setSubject(user.getId().toString()).setAudience("user")
				.setExpiration(new Date(now.getTime() + JWT_EXPIRE_SECOND * 1000)).setIssuedAt(now)
				.setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256, JWT_SIGN_KEY)
				.compact();
		request.setAttribute("newToken", token);
		return new Result<>();
	}

	@RequestMapping("getInfo")
	@ResponseBody
	public Result<Object> getInfo(Token token) {
		User user = userService.get(token.getId());
		return new Result<>(JSON.toJSON(user));
	}
}
