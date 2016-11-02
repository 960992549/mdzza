package cn.mdzza.dto;

/**
 * Created by ydt on 2016/11/2.
 */
public class Token {
	private Long id;

	public Token(Long id) {
		this.id = id;
	}

	public Token(String id) {
		this.id = Long.parseLong(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
