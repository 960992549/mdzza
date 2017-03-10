package cn.mdzza.api.dao;

import cn.mdzza.api.entity.Api;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiDao {
	Api get(@Param("id") Long id, @Param("module") String module, @Param("resource") String resource, @Param("method") String method);

	void add(Api api);

	void edit(Api api);

	List<Api> list();

	void delete(@Param("id") Long id);
}
