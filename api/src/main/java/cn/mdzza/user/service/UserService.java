package cn.mdzza.user.service;

import cn.mdzza.common.ServiceException;
import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.user.dao.UserDao;
import cn.mdzza.user.entity.User;
import cn.mdzza.util.DateUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ydt on 2017/2/16.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
	@Autowired
	private UserDao userDao;

	@Transactional
	public Map<String, Object> register(String username, String password, String name, String gender,
										String birthday, String mobile, String email, String nickname) {
		Map<String, Object> result = new HashMap<String, Object>();

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		user.setGender(gender);
		user.setBirthday(DateUtil.parseDate(birthday));
		user.setMobile(mobile);
		user.setEmail(email);
		user.setNickname(nickname);
		userDao.register(user);

		Date now = new Date();
		String token = Jwts.builder().setIssuer(ProjectConstant.JWT_ISSUER)
				.setSubject(user.getId().toString()).setAudience("user")
				.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
				.setIssuedAt(now).setNotBefore(now)
				.setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY)
				.compact();
		result.put("token", token);
		return result;
	}

	public Map<String, Object> login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userDao.get(username, password);
		if(user == null) {
			throw new ServiceException(ResultEnum.SERVICE_FAILED, "用户名或密码错误");
		}
		Date now = new Date();
		String token = Jwts.builder().setIssuer(ProjectConstant.JWT_ISSUER)
				.setSubject(user.getId().toString()).setAudience("user")
				.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
				.setIssuedAt(now).setNotBefore(now)
				.setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY)
				.compact();
		result.put("token", token);
		return result;
	}
}
