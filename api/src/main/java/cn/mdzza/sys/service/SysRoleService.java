package cn.mdzza.sys.service;

import cn.mdzza.sys.entity.SysRole;
import cn.mdzza.service.BaseService;
import cn.mdzza.sys.dao.SysRoleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ydt on 2017/3/9.
 */
@Service
@Transactional(readOnly = true)
public class SysRoleService extends BaseService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysRoleDao sysRoleDao;

	public Map<String, Object> list(String token) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "sysRoleService.list");
		List<SysRole> list = sysRoleDao.list();
		result.put("list", list);
		return result;
	}

	public Map<String, Object> get(String token, Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "sysRoleService.get");
		SysRole role = sysRoleDao.get(id);
		result.put("detail", role);
		return result;
	}

	@Transactional
	public Map<String, Object> save(String token, Long id, String nameEn, String nameCn, String description) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "sysRoleService.save");
		SysRole role = new SysRole();
		role.setNameEn(nameEn);
		role.setNameCn(nameCn);
		role.setDescription(description);
		if(id != null && id.longValue() > 0) {
			role.setId(id);
			sysRoleDao.update(role);
		} else {
			sysRoleDao.add(role);
		}
		return result;
	}

	@Transactional
	public Map<String, Object> delete(String token, Long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		handleToken(token, result, "sysRoleService.delete");
		sysRoleDao.delete(id);
		return result;
	}
}
