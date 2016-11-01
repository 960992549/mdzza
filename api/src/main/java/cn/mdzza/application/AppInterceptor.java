package cn.mdzza.application;

import cn.mdzza.common.ServiceException;
import cn.mdzza.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
			/*for(Parameter parameter : ((HandlerMethod) handler).getMethod().getParameters()) {
				if(parameter.getType().equals(Token.class)) {
					Token token = TokenUtil.getToken(request);
					if (token == null) {
						throw new ServiceException(ResultEnum.TOKEN_INVALID);
					} else {
						request.setAttribute("token", token);
					}
					break;
				}
			}*/
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
		handlerException(response, ex);
	}

	private void handlerException(HttpServletResponse response, Exception ex) throws IOException {
		if(ex != null) {
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
}
