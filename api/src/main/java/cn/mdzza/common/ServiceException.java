package cn.mdzza.common;

import cn.mdzza.enums.ResultEnum;

/**
 * 业务异常类
 * Created by ydt on 2016/6/23.
 */
public class ServiceException extends RuntimeException {
	private ResultEnum resultEnum;	//异常代码

	public ServiceException(ResultEnum resultEnum, String message) {
		super(message);
		this.resultEnum = resultEnum;
	}

	public ServiceException(ResultEnum resultEnum) {
		this.resultEnum = resultEnum;
	}

	public ResultEnum getResultEnum() {
		return resultEnum;
	}

	public void setResultEnum(ResultEnum resultEnum) {
		this.resultEnum = resultEnum;
	}

	public String toString() {
		return resultEnum + ",\"message\":\"" + getMessage();
	}
}
