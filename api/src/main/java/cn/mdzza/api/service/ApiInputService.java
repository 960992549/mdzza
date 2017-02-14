package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInputDao;
import cn.mdzza.api.entity.ApiInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
public class ApiInputService {
	@Autowired
	private ApiInputDao apiInputDao;

	public List<ApiInput> get(Long apiId) {
		return apiInputDao.get(apiId);
	}
}
