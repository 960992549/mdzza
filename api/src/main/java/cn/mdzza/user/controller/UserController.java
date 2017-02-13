package cn.mdzza.user.controller;

import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.dto.Result;
import cn.mdzza.dto.Token;
import cn.mdzza.user.enums.RegisterTypeEnum;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.user.entity.User;
import cn.mdzza.user.service.UserService;
import cn.mdzza.util.EmailUtil;
import cn.mdzza.util.MobileUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void preHandler(HttpServletRequest request, Model model) {
		if(request.getAttribute("token") != null) {
			model.addAttribute("token", request.getAttribute("token"));
		}
	}

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
		String token = Jwts.builder().setIssuer(ProjectConstant.JWT_ISSUER)
				.setSubject(user.getId().toString()).setAudience("user")
				.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
				.setIssuedAt(now).setNotBefore(now)
				.setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY)
				.compact();
		return new Result<>(token);
	}

	@RequestMapping("getInfo")
	@ResponseBody
	public Result<Object> getInfo(Token token) {
		User user = userService.get(token.getId());
		return new Result<>(token.getTk(), JSON.toJSON(user));
	}
}
