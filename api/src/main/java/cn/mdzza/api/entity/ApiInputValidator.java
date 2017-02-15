package cn.mdzza.api.entity;

import cn.mdzza.common.validator.Validator;
import cn.mdzza.util.SpringContextHolder;

/**
 * Created by ydt on 2017/2/14.
 */
public class ApiInputValidator {
	private Long id;
	private Long inputId;
	private String rule;
	private String message;
	private String description;
	private Integer sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getInputId() {
		return inputId;
	}

	public void setInputId(Long inputId) {
		this.inputId = inputId;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public boolean validate(Object value) {
		String rule = this.rule.replace(" ", "");
		rule = rule.indexOf("=") != -1 ? rule.substring(0, rule.indexOf("=")) : rule;
		Validator validator = SpringContextHolder.getBean(rule + "Validator");
		return validator.validate(value, this.rule.replace(" ", ""));
	}
}
