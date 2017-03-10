package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiDao;
import cn.mdzza.api.entity.Api;
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
public class ApiService extends BaseService {
	@Autowired
	private ApiDao apiDao;

	public Api get(String module, String resource, String method) {
		return apiDao.get(null, module, resource, method);
	}

	public Map<String, Object> get(String token, Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiService.get");
		Api api = apiDao.get(id, null, null, null);
		result.put("detail", api);
		return result;
	}

	public Map<String, Object> list(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiService.list");
		List<Api> list = apiDao.list();
		result.put("list", list);
		return result;
	}

	@Transactional
	public Map<String, Object> save(String token, Long id, String name, String module, String resource,
								   String method, String invokeMethod, String description) {
		Map<String, Object> result = new HashMap<String, Object>();
		Long userId = handleToken(token, result, "apiService.save");
		Api api = new Api();
		api.setId(id);
		api.setName(name);
		api.setModule(module);
		api.setResource(resource);
		api.setMethod(method);
		api.setInvokeMethod(invokeMethod);
		api.setDescription(description);
		api.setLastChangeUserId(userId);
		api.setLastChangeDatetime(new Date());
		if(id == null || id.longValue() < 1) {
			api.setCreateUserId(userId);
			api.setCreateDatetime(new Date());
			apiDao.add(api);
		} else {
			apiDao.edit(api);
		}
		return result;
	}

	@Transactional
	public Map<String, Object> delete(String token, Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiService.delete");
		apiDao.delete(id);
		return result;
	}
}
