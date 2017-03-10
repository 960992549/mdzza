package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInputValidatorDao;
import cn.mdzza.api.entity.ApiInputValidator;
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
public class ApiInputValidatorService extends BaseService {
	@Autowired
	private ApiInputValidatorDao apiInputValidatorDao;

	public List<ApiInputValidator> get(Long inputId) {
		return apiInputValidatorDao.get(inputId);
	}

	public Map<String, Object> list(String token, Long inputId) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiInputValidatorService.list");
		List<ApiInputValidator> list = apiInputValidatorDao.get(inputId);
		result.put("list", list);
		return result;
	}

	@Transactional
	public Map<String, Object> config(String token, Long inputId, String validators) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiInputValidatorService.config");
		apiInputValidatorDao.delete(inputId);

		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<ApiInputValidator> apiInputs = jsonMapper.fromJson(validators,
				jsonMapper.createCollectionType(List.class, ApiInputValidator.class));
		if(apiInputs != null && apiInputs.size() > 0) {
			for(ApiInputValidator apiInputValidator : apiInputs) {
				apiInputValidator.setInputId(inputId);
			}
			apiInputValidatorDao.add(apiInputs);
		}
		return result;
	}
}
