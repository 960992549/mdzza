package cn.mdzza.entity;

/**
 * Created by ydt on 2016/11/1.
 */
public class User {
	private Long id;
	private String username;
	private String password;
	private String name;
	private String sale;
	private String dateOfBirth;
	private String mobile;
	private String email;
	private String nickname;
	private Integer level;
	private Integer experience;
	private Integer topicNumber;
	private Integer fansNumber;
	private Integer concernNumber;
	private String labelIds;

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

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
		return labelIds;
	}

	public void setLabelIds(String labelIds) {
		this.labelIds = labelIds;
	}
}
