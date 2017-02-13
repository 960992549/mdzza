package cn.mdzza.user.enums;

/**
 * Created by ydt on 2016/11/1.
 */
public enum RegisterTypeEnum {
	MOBILE("mobile", "手机号"),
	EMAIL("mobile", "邮箱");

	private String type;
	private String description;

	RegisterTypeEnum(String type, String description) {
		this.type = type;
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
