package cn.mdzza.dto;

import cn.mdzza.enums.ResultEnum;

/**
 * api返回接口DTO
 * Created by ydt on 2016/11/1.
 */
public class Result<T> {
	private int code = ResultEnum.SUCCESS.getCode();
	private String text = ResultEnum.SUCCESS.getText();
	private String message;
	private T data;

	public Result(ResultEnum resultEnum) {
		code = resultEnum.getCode();
		text = resultEnum.getText();
	}

	public Result(ResultEnum resultEnum, String message) {
		code = resultEnum.getCode();
		text = resultEnum.getText();
		this.message = message;
	}

	public Result(T data) {
		this.data = data;
	}

	public Result(ResultEnum resultEnum, T data) {
		code = resultEnum.getCode();
		text = resultEnum.getText();
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
