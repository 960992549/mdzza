package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiOutput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiOutputDao {
	List<ApiOutput> get(@Param("apiId") Long apiId);
}
