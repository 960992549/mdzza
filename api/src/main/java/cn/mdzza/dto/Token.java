package cn.mdzza.dto;

/**
 * Created by ydt on 2016/11/2.
 */
public class Token {
	private Long id;
	private String tk;

	public Token(Long id, String tk) {
		this.id = id;
		this.tk = tk;
	}

	public Token(String id, String tk) {
		this.id = Long.parseLong(id);
		this.tk = tk;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTk() {
		return tk;
	}

	public void setTk(String tk) {
		this.tk = tk;
	}
}
