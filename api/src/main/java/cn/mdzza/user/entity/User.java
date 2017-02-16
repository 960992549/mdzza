package cn.mdzza.user.entity;

import java.util.Date;

/**
 * Created by ydt on 2017/2/16.
 */
public class User {
	private Long id;
	private String username;
	private String password;
	private String name;
	private String gender;
	private Date birthday;
	private String mobile;
	private String email;
	private String nickname;
	private String status;
	private Integer level;
	private Integer experience;
	private Integer topicNumber;
	private Integer fansNumber;
	private Integer concernNumber;
	private String LabelIds;
	private Date registerDatetime;
	private String registerIp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getTopicNumber() {
		return topicNumber;
	}

	public void setTopicNumber(Integer topicNumber) {
		this.topicNumber = topicNumber;
	}

	public Integer getFansNumber() {
		return fansNumber;
	}

	public void setFansNumber(Integer fansNumber) {
		this.fansNumber = fansNumber;
	}

	public Integer getConcernNumber() {
		return concernNumber;
	}

	public void setConcernNumber(Integer concernNumber) {
		this.concernNumber = concernNumber;
	}

	public String getLabelIds() {
		return LabelIds;
	}

	public void setLabelIds(String labelIds) {
		LabelIds = labelIds;
	}

	public Date getRegisterDatetime() {
		return registerDatetime;
	}

	public void setRegisterDatetime(Date registerDatetime) {
		this.registerDatetime = registerDatetime;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
}
