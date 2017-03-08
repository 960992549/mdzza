package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiOutputDao;
import cn.mdzza.api.entity.ApiOutput;
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
public class ApiOutputService extends BaseService {
	@Autowired
	private ApiOutputDao apiOutputDao;

	public List<ApiOutput> get(Long apiId) {
		return apiOutputDao.get(apiId);
	}

	public Map<String, Object> list(String token, Long apiId) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiOutputService.list");
		List<ApiOutput> list = apiOutputDao.get(apiId);
		result.put("list", list);
		return result;
	}

	@Transactional
	public Map<String, Object> save(String token, Long apiId, String outputs) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiOutputService.save");
		apiOutputDao.delete(apiId);

		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<ApiOutput> apiOutputs = jsonMapper.fromJson(outputs,
				jsonMapper.createCollectionType(List.class, ApiOutput.class));
		if(apiOutputs != null && apiOutputs.size() > 0) {
			for(ApiOutput apiOutput : apiOutputs) {
				apiOutput.setApiId(apiId);
			}
			apiOutputDao.add(apiOutputs);
		}
		return result;
	}
}
