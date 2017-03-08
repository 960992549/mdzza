package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiOutput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiOutputDao {
	List<ApiOutput> get(@Param("apiId") Long apiId);

	void delete(@Param("apiId") Long apiId);

	void add(@Param("apiOutputs") List<ApiOutput> apiOutputs);
}
