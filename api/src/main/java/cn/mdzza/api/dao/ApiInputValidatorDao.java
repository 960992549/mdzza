package cn.mdzza.api.dao;

import cn.mdzza.api.entity.ApiInputValidator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by ydt on 2017/2/14.
 */
public interface ApiInputValidatorDao {
	List<ApiInputValidator> get(@Param("inputId") Long inputId);

	void delete(@Param("inputId") Long inputId);

	void add(@Param("apiInputValidators") List<ApiInputValidator> apiInputValidators);
}
