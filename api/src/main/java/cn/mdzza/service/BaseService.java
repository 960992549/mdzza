package cn.mdzza.service;

import cn.mdzza.common.ServiceException;
import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.sys.dao.SysUserDao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ydt on 2017/2/16.
 */
public abstract class BaseService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserDao sysUserDao;

	protected Long handleToken(String token, Map<String, Object> result, String invokedMethod) {
		try {
			Claims claims = Jwts.parser().setSigningKey(ProjectConstant.JWT_SIGN_KEY)
					.parseClaimsJws(token).getBody();
			Date iat = claims.getIssuedAt();
			Long userId = Long.parseLong(claims.getSubject());
			if(!sysUserDao.checkPermission(userId, invokedMethod)) {
				throw new ServiceException(ResultEnum.PERMISSION_REJECT);
			}
			Date now = new Date();
			String newToken = token;
			if(now.getTime() > iat.getTime() + ProjectConstant.JWT_INTERVAL_SECOND * 1000) {
				newToken = Jwts.builder()
						.setClaims(claims.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))
								.setIssuedAt(now).setNotBefore(now).setId(UUID.randomUUID().toString()))
						.signWith(SignatureAlgorithm.HS256, ProjectConstant.JWT_SIGN_KEY).compact();
			}
			result.put("token", newToken);
			return userId;
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new ServiceException(ResultEnum.TOKEN_INVALID);
		}
	}
}
