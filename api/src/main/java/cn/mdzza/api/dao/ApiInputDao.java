package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiInput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiInputDao {
	List<ApiInput> get(@Param("apiId") Long apiId);
}
