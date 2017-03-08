package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiOutputFormat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiOutputFormatDao {
	List<ApiOutputFormat> get(@Param("outputId") Long outputId);

	void delete(@Param("outputId") Long outputId);

	void add(@Param("apiOutputFormats") List<ApiOutputFormat> apiOutputFormats);
}
