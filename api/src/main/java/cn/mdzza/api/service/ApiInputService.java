package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInputDao;
import cn.mdzza.api.entity.ApiInput;
import cn.mdzza.service.BaseService;
import cn.mdzza.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
@Transactional(readOnly = true)
public class ApiInputService extends BaseService {
	@Autowired
	private ApiInputDao apiInputDao;

	public List<ApiInput> get(Long apiId) {
		return apiInputDao.get(apiId);
	}

	public Map<String, Object> list(String token, Long apiId) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiInputService.list");
		List<ApiInput> list = apiInputDao.get(apiId);
		result.put("list", list);
		return result;
	}

	@Transactional
	public Map<String, Object> config(String token, Long apiId, String inputs) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiInputService.config");
		apiInputDao.delete(apiId);

		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<ApiInput> apiInputs = jsonMapper.fromJson(inputs,
				jsonMapper.createCollectionType(List.class, ApiInput.class));
		if(apiInputs != null && apiInputs.size() > 0) {
			for(ApiInput apiInput : apiInputs) {
				apiInput.setApiId(apiId);
			}
			apiInputDao.add(apiInputs);
		}
		return result;
	}
}
