package service;

public class Param {

	private Integer limit;

	private Integer offset;
	
	private String objectName;
	
	private String search;
	
	public Param() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Param(Integer limit, Integer offset) {
		super();
		this.limit = limit;
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	@Override
	public String toString() {
		return "Param [limit=" + limit + ", offset=" + offset + "]";
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

}
