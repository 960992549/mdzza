package cn.mdzza.service;

import cn.mdzza.common.ServiceException;
import cn.mdzza.dao.UserDao;
import cn.mdzza.entity.User;
import cn.mdzza.enums.RegisterTypeEnum;
import cn.mdzza.enums.ResultEnum;
import cn.mdzza.util.SyncUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ydt on 2016/11/1.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

	@Autowired
	private UserDao userDao;

	public User get(String mobile, String email) {
		return userDao.get(mobile, email);
	}

	/**
	 * 用户注册
	 * @param registerType
	 * @param sign
	 * @param password
	 * @return
	 */
	@Transactional
	public Map<String,Object> register(RegisterTypeEnum registerType, String sign, String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		if(registerType == null || StringUtils.isEmpty(sign) || StringUtils.isEmpty(password)) {
			throw new ServiceException(ResultEnum.EXCEPTION);
		}
		String type = registerType.getType();
		synchronized (SyncUtil.getLock(type + sign, User.class.getSimpleName())) {
			User user = userDao.get(type, sign);
			if(user != null) {
				result.put("isSuccess", false);
				result.put("message", registerType.getDescription() + "已注册，请重新填写");
				return result;
			}
			userDao.register(type, sign, password);
			result.put("isSuccess", true);
			return result;
		}
	}
}
