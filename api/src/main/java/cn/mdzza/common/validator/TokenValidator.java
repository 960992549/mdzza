package cn.mdzza.common.validator;

import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.dto.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by ydt on 2017/2/15.
 */
@Component
public class TokenValidator implements Validator {
	private static Logger logger = LoggerFactory.getLogger(RequiredValidator.class);

	@Override
	public boolean validate(Object value, String rule) {
		try {
			String token = value.toString();
			Claims claims = Jwts.parser().setSigningKey(ProjectConstant.JWT_SIGN_KEY)
					.parseClaimsJws(token).getBody();
			Date exp = claims.getExpiration();
			Date nbf = claims.getNotBefore();

			Date now = new Date();
			return now.getTime() >= nbf.getTime() && now.getTime() <= exp.getTime();
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}
}
