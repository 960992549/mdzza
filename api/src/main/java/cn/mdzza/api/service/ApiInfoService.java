package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInfoDao;
import cn.mdzza.api.entity.ApiInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
public class ApiInfoService {
	@Autowired
	private ApiInfoDao apiInfoDao;

	public ApiInfo get(String module, String resource, String method) {
		return apiInfoDao.get(module, resource, method);
	}
}
