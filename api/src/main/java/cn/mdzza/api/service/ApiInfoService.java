package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInfoDao;
import cn.mdzza.api.entity.ApiInfo;
import cn.mdzza.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
@Transactional(readOnly = true)
public class ApiInfoService extends BaseService {
	@Autowired
	private ApiInfoDao apiInfoDao;

	public ApiInfo get(String module, String resource, String method) {
		return apiInfoDao.get(module, resource, method);
	}

	@Transactional
	public Map<String, Object> add(String token, String name, String module, String resource,
								   String method, String invokeMethod, String description) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long userId = handleToken(token, result, "apiInfoService.add");
		ApiInfo apiInfo = new ApiInfo();
		apiInfo.setName(name);
		apiInfo.setModule(module);
		apiInfo.setResource(resource);
		apiInfo.setMethod(method);
		apiInfo.setInvokeMethod(invokeMethod);
		apiInfo.setDescription(description);
		apiInfo.setCreateUserId(userId);
		apiInfo.setCreateDatetime(new Date());
		apiInfo.setLastChangeUserId(userId);
		apiInfo.setLastChangeDatetime(new Date());
		apiInfoDao.add(apiInfo);
		return result;
	}
}
