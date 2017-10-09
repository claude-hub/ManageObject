package entity;

public class student {
	@Clumn(value = "学号",isNull = false, maxlength = 11, minlength = 11, isAllNumber = true,isPrimaryKey = true, inputType="text",isSearch=true)
	private String no;
	@Clumn(value = "姓名",isNull = false, maxlength = 4, minlength = 1, isAllNumber = false, inputType="text",isSearch=true)
	private String name;
	@Clumn(value = "年龄",isNull = true, maxlength = 3, minlength = 1, isAllNumber = true, inputType="text",isSearch=true)
	private String age;
	@Clumn(value = "电话",isNull = true, isAllNumber = true, inputType="text",isSearch=true)
	private String phoneNumber;
	@Clumn(value = "性别",isNull = true, maxlength = 1, minlength = 1, isAllNumber = false, inputType="radio", inputValue = "男,女")
	private String sex;
	@Override
	public String toString() {
		return "student [no=" + no + ", name=" + name + ", age=" + age
				+ ", sex=" + sex + ", phoneNumber=" + phoneNumber + "]";
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
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
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
