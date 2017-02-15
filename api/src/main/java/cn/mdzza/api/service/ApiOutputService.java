package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiOutputDao;
import cn.mdzza.api.entity.ApiOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
public class ApiOutputService {
	@Autowired
	private ApiOutputDao apiOutputDao;

	public List<ApiOutput> get(Long apiId) {
		return apiOutputDao.get(apiId);
	}
}
