package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiInfoDao {
	ApiInfo get(@Param("id") Long id, @Param("module") String module, @Param("resource") String resource, @Param("method") String method);

	void add(ApiInfo apiInfo);

	void edit(ApiInfo apiInfo);

	List<ApiInfo> list();
}
