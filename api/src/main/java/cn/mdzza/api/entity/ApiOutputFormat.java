package cn.mdzza.api.entity;

/**
 * Created by ydt on 2017/2/14.
 */
public class ApiOutputFormat {
	private Long id;
	private Long outputId;
	private String format;
	private String description;
	private Integer sort;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOutputId() {
		return outputId;
	}

	public void setOutputId(Long outputId) {
		this.outputId = outputId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
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
}
