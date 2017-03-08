package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiInputValidatorDao;
import cn.mdzza.api.entity.ApiInputValidator;
import cn.mdzza.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
