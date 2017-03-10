package cn.mdzza.sys.dao;

import cn.mdzza.sys.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/3/9.
 */
public interface SysRoleDao {
	List<SysRole> list();

	SysRole get(@Param("id") Long id);

	void add(SysRole role);

	void update(SysRole role);

	void delete(@Param("id") Long id);
}
