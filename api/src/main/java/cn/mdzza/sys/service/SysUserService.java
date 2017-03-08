package cn.mdzza.sys.service;

import cn.mdzza.common.ServiceException;
import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.service.BaseService;
import cn.mdzza.sys.dao.SysUserDao;
import cn.mdzza.sys.entity.SysUser;
import cn.mdzza.util.SyncUtil;
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
@Service
@Transactional(readOnly = true)
public class SysUserService extends BaseService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserDao sysUserDao;

	public Map<String, Object> get(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long userId = handleToken(token, result, "sysUserService.get");
		SysUser user = sysUserDao.get(userId, null, null);
		result.put("username", user.getUsername());
		return result;
	}

	public Map<String, Object> login(String username, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		SysUser user = sysUserDao.get(null, username, password);
		if(user == null) {
			throw new ServiceException(ResultEnum.SERVICE_FAILED, "用户名或密码错误");
		}
		Date now = new Date();
		String token = Jwts.builder().setIssuer(ProjectConstant.JWT_ISSUER)
				.setSubject(user.getId().toString()).setAudience("sysUser")
				.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
				.setIssuedAt(now).setNotBefore(now)
				.setId(UUID.randomUUID().toString()).signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY)
				.compact();
		result.put("token", token);
		return result;
	}

	@Transactional
	public Map<String, Object> add(String token, String username, String password, String name, String mobile) {
		synchronized (SyncUtil.getLock(username)) {
			Map<String, Object> result = new HashMap<String, Object>();
			Long userId = handleToken(token, result, "sysUserService.add");
			SysUser user = sysUserDao.get(null, username, null);
			if (user != null) {
				throw new ServiceException(ResultEnum.SERVICE_FAILED, "用户名已存在");
			}
			sysUserDao.add(username, password, name, mobile, userId);
			return result;
		}
	}
}
