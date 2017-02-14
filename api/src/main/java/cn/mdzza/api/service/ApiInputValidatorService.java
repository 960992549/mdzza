package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInputValidatorDao;
import cn.mdzza.api.entity.ApiInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
public class ApiInputValidatorService {
	@Autowired
	private ApiInputValidatorDao apiInputValidatorDao;

	public List<ApiInputValidator> get(Long inputId) {
		return apiInputValidatorDao.get(inputId);
	}
}
