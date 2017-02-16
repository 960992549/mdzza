package cn.mdzza.controller;

import cn.mdzza.api.entity.ApiInfo;
import cn.mdzza.api.entity.ApiInput;
import cn.mdzza.api.entity.ApiInputValidator;
import cn.mdzza.api.entity.ApiOutput;
import cn.mdzza.api.service.*;
import cn.mdzza.common.ServiceException;
import cn.mdzza.dto.Result;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ydt on 2017/2/14.
 */
@RestController
@RequestMapping(value = "api", method = RequestMethod.POST)
public class ApiController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApiInfoService apiInfoService;
	@Autowired
	private ApiInputService apiInputService;
	@Autowired
	private ApiOutputService apiOutputService;
	@Autowired
	private ApiInputValidatorService apiInputValidatorService;
	@Autowired
	private ApiOutputFormatService apiOutputFormatService;

	@RequestMapping("{module}/{resource}/{method}")
	public Result api(@PathVariable String module, @PathVariable String resource, @PathVariable String method, HttpServletRequest request) {
		//获取apiInfo
		ApiInfo apiInfo = apiInfoService.get(module, resource, method);
		//获取入参
		List<ApiInput> inputs = apiInputService.get(apiInfo.getId());
		Object[] args = new Object[inputs.size()];
		String[] argsClassName = new String[inputs.size()];
		int i = 0;
		//获取入参校验规则
		for(ApiInput input : inputs) {
			Object inputValue = request.getParameter(input.getName());
			args[i] = inputValue;
			argsClassName[i++] = input.getDataType();
			List<ApiInputValidator> validators = apiInputValidatorService.get(input.getId());
			for(ApiInputValidator validator : validators) {
				//校验入参
				boolean isValid = validator.validate(inputValue);
				if(!isValid) {
					return new Result(ResultEnum.PARAM_ERROR, validator.getMessage());
				}
			}
		}
		//调用service
		try {
			Map<String, Object> result = (Map<String, Object>) invokeMethod(
					SpringContextHolder.getBean(apiInfo.getInvokeMethod().split("\\.")[0]),
					apiInfo.getInvokeMethod().split("\\.")[1], args, argsClassName);
			//获取出参
			List<ApiOutput> outputs = apiOutputService.get(apiInfo.getId());
			//获取出参转换器
			Map<String, Object> data = new HashMap<String, Object>();
			for(ApiOutput output : outputs) {
				data.put(output.getName(), result.get(output.getName()));
			}
			return new Result(data);
		} catch (ServiceException e) {
			logger.warn(e.toString());
			return new Result(e.getResultEnum(), e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new Result(ResultEnum.EXCEPTION);
		}
	}

	private Object invokeMethod(Object owner, String methodName, Object[] args, String[] argsClassName) throws Exception {
		try {
			Class[] argsClass = new Class[argsClassName.length];
			for (int i = 0, j = argsClassName.length; i < j; i++) {
				argsClass[i] = Class.forName(argsClassName[i]);
				if(Long.class.isAssignableFrom(argsClass[i])) {
					args[i] = Long.parseLong((String)args[i]);
				} else if(Integer.class.isAssignableFrom(argsClass[i])) {
					args[i] = Integer.parseInt((String)args[i]);
				}
			}
			Method method = owner.getClass().getMethod(methodName, argsClass);
			return method.invoke(owner, args);
		} catch (InvocationTargetException e) {
			if(e.getTargetException() instanceof ServiceException) {
				throw (ServiceException)e.getTargetException();
			} else {
				throw e;
			}
		}
	}
}
