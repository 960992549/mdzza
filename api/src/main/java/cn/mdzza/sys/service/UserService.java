package cn.mdzza.sys.service;

import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.sys.dao.UserDao;
import cn.mdzza.sys.entity.User;
import cn.mdzza.util.SyncUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ydt on 2017/2/14.
 */
@Service("sysUserService")
@Transactional(readOnly = true)
public class UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserDao userDao;

	public Map<String, Object> login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		User user = userDao.get(username, password);
		if(user == null) {
			result.put("isSuccess", false);
			result.put("message", "用户名或密码错误");
			return result;
		}
		Date now = new Date();
		String token = Jwts.builder().setIssuer(ProjectConstant.JWT_ISSUER)
				.setSubject(user.getId().toString()).setAudience("user")
				.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
				.setIssuedAt(now).setNotBefore(now)
				.setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY)
				.compact();
		result.put("isSuccess", true);
		result.put("message", "操作成功");
		result.put("token", token);
		return result;
	}

	@Transactional
	public Map<String, Object> add(String token, String username, String password, String name, String mobile) {
		synchronized (SyncUtil.getLock(username)) {
			Map<String, Object> result = new HashMap<String, Object>();
			User user = userDao.get(username, null);
			if (user != null) {
				result.put("isSuccess", false);
				result.put("message", "用户名已存在");
				return result;
			}
			userDao.add(username, password, name, mobile);
			result.put("isSuccess", true);
			result.put("message", "操作成功");
			result.put("token", handleToken(token).get("token"));
			return result;
		}
	}

	private Map<String, Object> handleToken(String token) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			Claims claims = Jwts.parser().setSigningKey(ProjectConstant.JWT_SIGN_KEY)
					.parseClaimsJws(token).getBody();
			Date iat = claims.getIssuedAt();
			Long userId = Long.parseLong(claims.getSubject());

			Date now = new Date();
			String newToken = token;
			if(now.getTime() > iat.getTime() + ProjectConstant.JWT_INTERVAL_SECOND * 1000) {
				newToken = Jwts.builder().setClaims(claims.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
						.setIssuedAt(now).setNotBefore(now).setId(UUID.randomUUID().toString()))
						.signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY).compact();
			}
			data.put("token", newToken);
			data.put("userId", userId);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return data;
	}
}
