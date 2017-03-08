package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiOutputFormatDao;
import cn.mdzza.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
@Transactional(readOnly = true)
public class ApiOutputFormatService extends BaseService {
	@Autowired
	private ApiOutputFormatDao apiOutputFormatDao;
}
