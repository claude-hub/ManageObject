package entity;

public class teacher {
	@Clumn(value = "教师号",isNull = false, maxlength = 11, minlength = 11, isAllNumber = true,isPrimaryKey = true, inputType="text",isSearch=true)
	private String no;
	@Clumn(value = "姓名",isNull = false, maxlength = 4, minlength = 1, isAllNumber = false, inputType="text",isSearch=true)
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
}
