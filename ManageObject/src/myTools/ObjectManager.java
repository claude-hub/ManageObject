package myTools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import entity.Clumn;

public class ObjectManager {
	  //获取所有字段注解
    public static ArrayList<String> getAnnotations(String objectName) {
        ArrayList<String> annotations = new ArrayList<String>();
        try {
            Class<?> clazz = Class.forName("entity." + objectName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                annotations.add(getPrompt(field));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return annotations;
    }
	// 获取注解的提示
	public static String getPrompt(Field field) {
		Clumn fieldLabel = field.getAnnotation(Clumn.class);
		return fieldLabel.value();
	}

    //获取所有字段注解名
    public ArrayList<String> getTitle(String objectName) {
        ArrayList<String> titles = new ArrayList<String>();
        try {
            Class<?> clazz = Class.forName("ViewModels." + objectName + "Entity");
            titles.addAll(getAnnotations(clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return titles;
    }
    public ArrayList<String> getAnnotations(Class<?> clazz) {

        ArrayList<String> annotations = new ArrayList<String>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            annotations.add("'" + getPrompt(field) + "'");
        }
        return annotations;
    }
  //获取类的所有字段
    public static ArrayList<String> getTips(String objectName) {
        ArrayList<String> tips = new ArrayList<String>();
        try {
            Class<?> clazz = Class.forName("entity." + objectName);
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                tips.add(field.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tips;
    }
 // 获取所有属性
    public static Object[] getFields(Object obj, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        Object[] values = new Object[fields.length];

        try {
            for (int i = 0; i < fields.length; i++) {
                String setMethod = "get" + fields[i].getName();
                for (Method method : methods) {
                    if (method.getName().toLowerCase()
                            .equals(setMethod.toLowerCase())) {

                        values[i] = method.invoke(obj);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }
}
