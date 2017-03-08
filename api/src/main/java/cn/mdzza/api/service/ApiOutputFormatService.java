package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiOutputFormatDao;
import cn.mdzza.api.entity.ApiOutputFormat;
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
public class ApiOutputFormatService extends BaseService {
	@Autowired
	private ApiOutputFormatDao apiOutputFormatDao;

	public List<ApiOutputFormat> get(Long outputId) {
		return apiOutputFormatDao.get(outputId);
	}

	public Map<String, Object> list(String token, Long outputId) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiOutputFormatService.list");
		List<ApiOutputFormat> list = apiOutputFormatDao.get(outputId);
		result.put("list", list);
		return result;
	}

	@Transactional
	public Map<String, Object> save(String token, Long outputId, String formats) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "apiOutputFormatService.save");
		apiOutputFormatDao.delete(outputId);

		JsonMapper jsonMapper = JsonMapper.getInstance();
		List<ApiOutputFormat> apiInputs = jsonMapper.fromJson(formats,
				jsonMapper.createCollectionType(List.class, ApiOutputFormat.class));
		if(apiInputs != null) {
			for(ApiOutputFormat apiInputValidator : apiInputs) {
				apiInputValidator.setOutputId(outputId);
			}
			apiOutputFormatDao.add(apiInputs);
		}
		return result;
	}
}
