package cn.mdzza.api.entity;

import java.util.Date;

/**
 * Created by ydt on 2017/2/14.
 */
public class ApiInfo {
	private Long id;
	private String name;
	private String module;
	private String resource;
	private String method;
	private String invokeMethod;
	private String description;
	private Long createUserId;
	private Date createDatetime;
	private Long lastChangeUserId;
	private Date lastChangeDatetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getInvokeMethod() {
		return invokeMethod;
	}

	public void setInvokeMethod(String invokeMethod) {
		this.invokeMethod = invokeMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Long getLastChangeUserId() {
		return lastChangeUserId;
	}

	public void setLastChangeUserId(Long lastChangeUserId) {
		this.lastChangeUserId = lastChangeUserId;
	}

	public Date getLastChangeDatetime() {
		return lastChangeDatetime;
	}

	public void setLastChangeDatetime(Date lastChangeDatetime) {
		this.lastChangeDatetime = lastChangeDatetime;
	}
}
