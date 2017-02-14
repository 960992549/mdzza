package cn.mdzza.controller;

import cn.mdzza.api.entity.ApiInfo;
import cn.mdzza.api.entity.ApiInput;
import cn.mdzza.api.entity.ApiInputValidator;
import cn.mdzza.api.service.*;
import cn.mdzza.dto.Result;
import cn.mdzza.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

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

			}
		}
		//调用service
		Object result = invokeMethod(SpringContextHolder.getBean(apiInfo.getInvokeMethod().split(".")[0]),
				apiInfo.getInvokeMethod().split(".")[1], args, argsClassName);
		//获取出参
		//获取出参转换器
		return null;
	}

	private Object invokeMethod(Object owner, String methodName, Object[] args, String[] argsClassName) {
		try {
			Class[] argsClass = new Class[argsClassName.length];
			for (int i = 0, j = argsClassName.length; i < j; i++) {
				argsClass[i] = Class.forName(argsClassName[i]);
			}
			Method method = owner.getClass().getMethod(methodName, argsClass);
			return method.invoke(owner, args);
		} catch(Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}
}
