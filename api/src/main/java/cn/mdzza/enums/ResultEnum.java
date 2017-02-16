package cn.mdzza.enums;

/**
 * api返回code/text枚举
 * Created by ydt on 2016/11/1.
 */
public enum ResultEnum {
	AUTH_ERROR(-6, "鉴权失败"),
	PARAM_ERROR(-4, "参数错误"),
	SUCCESS(0, "OK"),
	TOKEN_INVALID(1, "用户未登录"),
	OTHER_ERROR(3, "其他错误"),
	EXCEPTION(4, "系统异常"),
	SERVICE_FAILED(5, "操作失败"),
	PERMISSION_REJECT(6, "权限不足"),
	;

	private int code;
	private String text;

	ResultEnum(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public static ResultEnum codeOf(int code) {
		for(ResultEnum result : values()) {
			if(result.getCode() == code) {
				return result;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "{\"code\":"+ code + ",\"text\":\"" + text +"\"}";
	}
}
