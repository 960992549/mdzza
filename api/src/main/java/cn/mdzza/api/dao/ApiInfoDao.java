package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiInfoDao {
	ApiInfo get(@Param("module") String module, @Param("resource") String resource, @Param("method") String method);

	void add(ApiInfo apiInfo);
}
