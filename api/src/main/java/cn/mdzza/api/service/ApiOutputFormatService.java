package cn.mdzza.api.service;

import cn.mdzza.api.dao.ApiOutputFormatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ydt on 2017/2/14.
 */
@Service
public class ApiOutputFormatService {
	@Autowired
	private ApiOutputFormatDao apiOutputFormatDao;
}
