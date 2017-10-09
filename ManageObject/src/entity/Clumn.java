package entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.FIELD})//类、变量可以标注
@Retention(RetentionPolicy.RUNTIME)
public @interface Clumn {
	public String value() default "";
	public boolean isNull() default false;
	public int maxlength() default -1; 
	public int minlength() default -1; //输入长度
    public boolean isAllNumber() default false; //是否全为数字
    public boolean isPrimaryKey() default false; //是否是主键
    public String inputType() default ""; //判断输入框类型
    public String inputValue() default "";//输入框的默认值
    public boolean isSearch() default false; //判断是否设置为搜索框
}
