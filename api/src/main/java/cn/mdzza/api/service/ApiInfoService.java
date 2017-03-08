package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInfoDao;
import cn.mdzza.api.entity.ApiInfo;
import cn.mdzza.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
		return apiInfoDao.get(null, module, resource, method);
	}

	public Map<String, Object> get(String token, Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiInfoService.get");
		ApiInfo apiInfo = apiInfoDao.get(id, null, null, null);
		result.put("detail", apiInfo);
		return result;
	}

	public Map<String, Object> list(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiInfoService.list");
		List<ApiInfo> list = apiInfoDao.list();
		result.put("list", list);
		return result;
	}

	@Transactional
	public Map<String, Object> save(String token, Long id, String name, String module, String resource,
								   String method, String invokeMethod, String description) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long userId = handleToken(token, result, "apiInfoService.save");
		ApiInfo apiInfo = new ApiInfo();
		apiInfo.setId(id);
		apiInfo.setName(name);
		apiInfo.setModule(module);
		apiInfo.setResource(resource);
		apiInfo.setMethod(method);
		apiInfo.setInvokeMethod(invokeMethod);
		apiInfo.setDescription(description);
		apiInfo.setLastChangeUserId(userId);
		apiInfo.setLastChangeDatetime(new Date());
		if(id == null || id.longValue() < 1) {
			apiInfo.setCreateUserId(userId);
			apiInfo.setCreateDatetime(new Date());
			apiInfoDao.add(apiInfo);
		} else {
			apiInfoDao.edit(apiInfo);
		}
		return result;
	}
}
