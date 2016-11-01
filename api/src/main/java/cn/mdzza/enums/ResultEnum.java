package cn.mdzza.enums;

/**
 * api返回code/text枚举
 * Created by ydt on 2016/11/1.
 */
public enum ResultEnum {
	AUTH_ERROR(-6, "鉴权失败"),
	CLIENT_ERROR(-5, "客户端错误"),
	PARAM_ERROR(-4, "参数错误"),
	ACTIVITY_ERROR(-3, "活动无效"),
	ACTIVITY_NOT_START(-2, "活动未开始"),
	ACTIVITY_END(-1, "活动已结束"),
	SUCCESS(0, "OK"),
	TOKEN_INVALID(1, "用户未登录"),
	REPEAT_JOIN(2, "重复参加"),
	OTHER_ERROR(3, "其他错误"),
	EXCEPTION(4, "系统异常"),
	WEIXIN_ERROR(5, "获取微信信息失败，请重新授权");

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
