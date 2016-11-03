package cn.mdzza.application;

import cn.mdzza.common.ServiceException;
import cn.mdzza.constant.ProjectConstant;
import cn.mdzza.dto.Token;
import cn.mdzza.enums.ResultEnum;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Parameter;
import java.util.Date;
import java.util.Map;

/**
 * 前端api拦截器，用来校验活动、校验client、token、异常情况
 * Created by ydt on 2016/6/22.
 */
public class AppInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 校验活动、处理client、token
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			response.reset();
			response.setContentType("application/json");
			response.setCharacterEncoding("utf-8");
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			for(Parameter parameter : ((HandlerMethod) handler).getMethod().getParameters()) {
				if(parameter.getType().equals(Token.class)) {
					try {
						String oldToken = request.getParameter("token");
						Claims claims = Jwts.parser().setSigningKey(ProjectConstant.JWT_SIGN_KEY)
								.parseClaimsJws(oldToken).getBody();
						Date iat = claims.getIssuedAt();
						Date exp = claims.getExpiration();
						Date nbf = claims.getNotBefore();
						String sub = claims.getSubject();

						Date now = new Date();
						if(now.getTime() < nbf.getTime() || now.getTime() > exp.getTime()) {
							throw new ServiceException(ResultEnum.TOKEN_INVALID);
						}
						String newToken = oldToken;
						if(now.getTime() > iat.getTime() + ProjectConstant.JWT_INTERVAL_SECOND * 1000) {
							newToken = Jwts.builder().setClaims(claims.setIssuedAt(now)
									.setExpiration(new Date(now.getTime() + ProjectConstant.JWT_EXPIRE_SECOND * 1000))).compact();
						}
						request.setAttribute("newToken", newToken);
						Token token = new Token(sub);
						request.setAttribute("token", token);
					} catch (Exception e) {
						throw new ServiceException(ResultEnum.TOKEN_INVALID);
					}
					break;
				}
			}
		} catch(Exception e) {
			handlerException(response, e);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	/**
	 * 处理异常情况
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		if(ex != null) {
			handlerException(response, ex);
			return;
		}
		try {
			String newToken = (String) request.getAttribute("newToken");
			if (StringUtils.isNotEmpty(newToken)) {
				PrintWriter writer = response.getWriter();
				Map<String, Object> result = JSON.parseObject(writer.toString(), Map.class);
				result.put("token", newToken);
				writer.print(JSON.toJSON(result));
				writer.close();
			}
			return;
		} catch (Exception e) {
			handlerException(response, e);
		}
	}

	private void handlerException(HttpServletResponse response, Exception ex) throws IOException {
		logger.error(ex.getMessage(), ex);
		ResultEnum resultEnum;
		if(ex instanceof ServiceException) {
			resultEnum = ((ServiceException)ex).getResultEnum();
			if(resultEnum == null) {
				resultEnum = ResultEnum.EXCEPTION;
			}
			logger.info("catch service exception, code : " + ((ServiceException)ex).getResultEnum().getCode());
		} else {
			resultEnum = ResultEnum.EXCEPTION;
			logger.error("catch other exception, message: " + ex.getMessage());
		}
		PrintWriter writer = response.getWriter();
		writer.print(resultEnum.toString());
		writer.close();
	}
}
