package cn.mdzza.dto;

import cn.mdzza.enums.ResultEnum;

/**
 * api返回接口DTO
 * Created by ydt on 2016/11/1.
 */
public class Result<T> {
	private int code = ResultEnum.SUCCESS.getCode();
	private String text = ResultEnum.SUCCESS.getText();
	private String token = "";
	private T data;

	public Result() {
	}

	public Result(ResultEnum resultEnum) {
		code = resultEnum.getCode();
		text = resultEnum.getText();
	}

	public Result(String token) {
		this.token = token;
	}

	public Result(String token, T data) {
		this.token = token;
		this.data = data;
	}

	public Result(ResultEnum result, T data) {
		code = result.getCode();
		text = result.getText();
		this.data = data;
	}

	public Result(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public Result(int code, String text, T data) {
		this.code = code;
		this.text = text;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
